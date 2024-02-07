package com.project.technicaltest.service;


import com.project.technicaltest.dto.UserLoginRequest;
import com.project.technicaltest.dto.UserRegisterRequest;
import com.project.technicaltest.dto.UserResponse;
import com.project.technicaltest.model.Users;

public interface UsersService {

   public UserResponse register(UserRegisterRequest request);

   Users findByKtp(String ktp);

   UserResponse login(UserLoginRequest request);
}
