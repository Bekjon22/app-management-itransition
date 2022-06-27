package com.itransition.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * @author Bekjon Bakhromov
 * @created 02.06.2022-4:24 PM
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
@Where(clause = "deleted=false")
@SQLDelete(sql = "update users set deleted=true,email =' ' where id=?")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;


    @Column()
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false,updatable = false)
    @CreationTimestamp
    private Timestamp registrationTime;

    @Column()
    private Timestamp lastLoginTime;

    private boolean status=true;

    private boolean deleted=false;



}
