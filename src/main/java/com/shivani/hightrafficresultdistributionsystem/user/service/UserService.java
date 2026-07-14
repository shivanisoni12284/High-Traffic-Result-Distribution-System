package com.shivani.hightrafficresultdistributionsystem.user.service;

import com.shivani.hightrafficresultdistributionsystem.common.exception.UserNotFoundException;
import com.shivani.hightrafficresultdistributionsystem.user.dto.UserResponseDto;
import com.shivani.hightrafficresultdistributionsystem.user.mapper.UserMapper;
import com.shivani.hightrafficresultdistributionsystem.user.repository.UserRepository;
import com.shivani.hightrafficresultdistributionsystem.user.schema.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class UserService {

   // create user = register User
   // get user by username = userDetailImpl->loadByUserName
    private final UserMapper userMapper;
    private final UserRepository userRepository;


    //
    public List<UserResponseDto> getAllUsers(){
        List<User> users = userRepository.findAll();

        return users.stream().map(userMapper::toResponseDto).toList();

    }

    // get user by username
     public  UserResponseDto getUserByUsername(String username){
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        return userMapper.toResponseDto(user);

     }

     //get user by id;
    public UserResponseDto getUserById(long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("user not found with id: "+userId));
        return userMapper.toResponseDto(user);

    }

}
