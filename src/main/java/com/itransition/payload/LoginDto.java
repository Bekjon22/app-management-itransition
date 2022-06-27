package com.itransition.payload;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author Bekjon Bakhromov
 * @created 05.06.2022-1:04 AM
 */
@Getter
@Setter
public class LoginDto {
    @Email(message = "Valid email required")
    @NotBlank(message = "Email required")
    private String email;

    @NotBlank(message = "Password required")
    private String password;


}
