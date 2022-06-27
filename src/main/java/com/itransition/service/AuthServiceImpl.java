package com.itransition.service;

import com.itransition.entity.User;
import com.itransition.exception.RestException;
import com.itransition.payload.ApiResult;
import com.itransition.payload.LoginDto;
import com.itransition.payload.RegisterDto;
import com.itransition.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;


/**
 * @author Bekjon Bakhromov
 * @created 02.06.2022-6:49 PM
 */

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;

    @Override
    public ApiResult<?> register(RegisterDto registerDto) {
        boolean existsByEmail = userRepository.existsByEmail(registerDto.getEmail());
        if (existsByEmail){
            throw  RestException.badRequest("This email already exist!");
        }
        User user = new User();
        user.setName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(registerDto.getPassword());
        userRepository.save(user);
        return ApiResult.successResponse("Successfully registered");
    }

    @Override
    public ApiResult<?> login(LoginDto dto) {
        Optional<User> byEmailAndPassword = userRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());
        if (!byEmailAndPassword.isPresent()){
            throw  RestException.badRequest("This email or password incorrect!" );
        }
        User user = byEmailAndPassword.get();
        if (!user.isStatus()) {
            throw  RestException.badRequest("Your account blocked!");

        }
        user.  setLastLoginTime(new Timestamp(System.currentTimeMillis()));
        userRepository.save(user);
        return ApiResult.successResponse(user.getId(),"Successfully login");
    }


}
