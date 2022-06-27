package com.itransition.payload;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * @author Bekjon Bakhromov
 * @created 05.06.2022-11:23 AM
 */
@Getter
@Setter
public class UserDto {
    private Integer id;

    private String name;

    private String email;

    private Timestamp registrationTime;

    private Timestamp lastLoginTime;

    private boolean status;
}
