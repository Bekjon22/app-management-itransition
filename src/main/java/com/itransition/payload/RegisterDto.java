package com.itransition.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author Bekjon Bakhromov
 * @created 02.06.2022-6:34 PM
 */
@Getter
@Setter
public class RegisterDto {

    @NotBlank(message = "Name cannot be empty!")
    private String name;

    @Email(message = "Valid email required!")
    @NotBlank(message = "Email cannot be empty!")
    private String email;

    @NotBlank(message = "Password cannot be empty!")
    private String password;



}
