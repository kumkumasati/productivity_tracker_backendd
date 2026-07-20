package com.productivitytracker.tracker.service.impl;

import com.productivitytracker.tracker.dto.UserDto;
import com.productivitytracker.tracker.entity.User;
import com.productivitytracker.tracker.exception.ResourceNotFoundException;
import com.productivitytracker.tracker.mapper.UserMapper;
import com.productivitytracker.tracker.repository.UserRepository;
import com.productivitytracker.tracker.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user= UserMapper.mapToUser(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User saveduser = userRepository.save(user);
        return UserMapper.mapToUserDto(saveduser);
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user= userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User does not exist"+ userId));
        return UserMapper.mapToUserDto(user);
    }


    @Override
    public List<UserDto> getAllUser() {
        List<User> users= userRepository.findAll();
        return users.stream()
                .map((user)-> UserMapper.mapToUserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(Long userId, UserDto updatedUser) {
        User user =  userRepository.findById(userId).orElseThrow(
                ()-> new ResourceNotFoundException("User does not exist with give id" +userId)
        );
//        user.setUserId(updatedUser.getUserId());
        user.setUsername(updatedUser.getUsername());
        user.setEmail(updatedUser.getEmail());
        // Only re-hash/overwrite the password if the caller actually sent a new one;
        // otherwise passwordEncoder.encode(null) throws and every profile-only edit
        // (e.g. changing just the username) would fail.
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }
        User updatedUserObj = userRepository.save(user);
        return UserMapper.mapToUserDto(updatedUserObj);
    }

    @Override
    public void deleteUser(Long userId) {
        User user =  userRepository.findById(userId).orElseThrow(
                ()-> new ResourceNotFoundException("User does not exist with give id" +userId)
        );
        userRepository.deleteById(userId);
    }
}
