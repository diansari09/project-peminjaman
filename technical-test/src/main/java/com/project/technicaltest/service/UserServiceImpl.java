package com.project.technicaltest.service;


import com.project.technicaltest.dto.UserLoginRequest;
import com.project.technicaltest.dto.UserRegisterRequest;
import com.project.technicaltest.dto.UserResponse;
import com.project.technicaltest.model.Users;
import com.project.technicaltest.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UsersService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    @Override
    public UserResponse register(UserRegisterRequest request) {
        validationService.validate(request);

        Users users = this.findByKtp(request.getKtp());
        if(users != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "KTP already exist");
        }

        Users newData = new Users();
        newData.setKtp(request.getKtp());
        newData.setEmail(request.getEmail());
        newData.setNoHp(request.getNoHp());
        newData.setCounter(0);
        newData.setPassword(this.generateCommonLangPassword());
        userRepository.save(newData);
        return UserResponse.builder()
                .email(newData.getEmail())
                .noHp(newData.getNoHp())
                .ktp(newData.getKtp()).build();
    }

    @Override
    public Users findByKtp(String ktp) {
        return userRepository.findByKtp(ktp);
    }

    @Override
    public UserResponse login(UserLoginRequest request) {
        validationService.validate(request);

        Users users = userRepository.findByEmail(request.getEmail());
        if(users != null){
            if(!users.getPassword().equals(request.getPassword())){
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "email & password wrong");
            }
        }

        return UserResponse.builder()
                .ktp(users.getKtp())
                .email(users.getEmail())
                .noHp(users.getNoHp()).build();
    }

    private String generateCommonLangPassword() {
        String upperCaseLetters = RandomStringUtils.random(2, 65, 90, true, true);
        String lowerCaseLetters = RandomStringUtils.random(2, 97, 122, true, true);
        String numbers = RandomStringUtils.randomNumeric(2);
        String specialChar = RandomStringUtils.random(2, 33, 47, false, false);
        String totalChars = RandomStringUtils.randomAlphanumeric(2);
        String combinedChars = upperCaseLetters.concat(lowerCaseLetters)
                .concat(numbers)
                .concat(specialChar)
                .concat(totalChars);
        List<Character> pwdChars = combinedChars.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());
        Collections.shuffle(pwdChars);
        String password = pwdChars.stream()
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
        return password;
    }
}
