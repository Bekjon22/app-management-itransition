package com.itransition.service;

import com.itransition.payload.ApiResult;
import com.itransition.payload.LoginDto;
import com.itransition.payload.RegisterDto;
import org.springframework.http.HttpEntity;

/**
 * @author Bekjon Bakhromov
 * @created 02.06.2022-6:42 PM
 */
public interface AuthService {

    ApiResult<?> register(RegisterDto registerDto);

    ApiResult<?> login(LoginDto dto);
}
