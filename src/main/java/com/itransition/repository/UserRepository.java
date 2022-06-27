package com.itransition.repository;

import com.itransition.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Bekjon Bakhromov
 * @created 02.06.2022-7:13 PM
 */

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByEmail(String email);

     Optional<User>findByEmailAndPassword(String email, String password);

    @Query(nativeQuery = true, value = " SELECT * FROM users u " +
            "                 LIMIT :size OFFSET :start ")
    List<User> findAll(Integer start, Integer size);




}
