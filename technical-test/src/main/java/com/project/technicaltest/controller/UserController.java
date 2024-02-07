package com.project.technicaltest.controller;


import com.project.technicaltest.dto.UserRegisterRequest;
import com.project.technicaltest.dto.UserResponse;
import com.project.technicaltest.dto.WebResponse;
import com.project.technicaltest.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/register")
    public WebResponse<UserResponse> register(@RequestBody UserRegisterRequest request){
        UserResponse response = usersService.register(request);
        return WebResponse.<UserResponse>builder()
                .data(response).build();
    }
}
