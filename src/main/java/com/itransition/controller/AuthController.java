package com.itransition.controller;

import com.itransition.payload.ApiResult;
import com.itransition.payload.LoginDto;
import com.itransition.payload.RegisterDto;
import com.itransition.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Bekjon Bakhromov
 * @created 02.06.2022-6:20 PM
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ApiResult<?> register(@RequestBody @Valid RegisterDto registerDto) {
        return authService.register(registerDto);
    }

    @PostMapping("/login")
    public ApiResult<?> login(@RequestBody @Valid LoginDto dto) {
        return authService.login(dto);
    }

}
