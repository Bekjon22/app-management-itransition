package com.itransition.service;

import com.itransition.entity.User;
import com.itransition.exception.RestException;
import com.itransition.payload.ApiResult;
import com.itransition.payload.UserDto;
import com.itransition.payload.UserIdsDto;
import com.itransition.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Bekjon Bakhromov
 * @created 05.06.2022-11:30 AM
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public ApiResult<List<UserDto>> getList(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) throw RestException.badRequest("Account deleted");

        User user1 = optionalUser.get();
        if (!user1.isStatus()) throw RestException.badRequest("Account blocked");


        List<User> allFamily = userRepository.findAll();
        if (allFamily.isEmpty()) {
            throw RestException.notFound("users not found!");
        }
        List<UserDto> userDto = new ArrayList<>();

        for (User user : allFamily) {
            UserDto userDto1 = new UserDto();
            userDto1.setId(user.getId());
            userDto1.setName(user.getName());
            userDto1.setEmail(user.getEmail());
            userDto1.setLastLoginTime(user.getLastLoginTime());
            userDto1.setRegistrationTime(user.getRegistrationTime());
            userDto1.setStatus(user.isStatus());
            userDto.add(userDto1);
        }
        return ApiResult.successResponse(userDto);
    }


    @Override
    public ApiResult<?> block(UserIdsDto ids, Integer currentId) {
//        ApiResult<?> apiResult = checkCurrentUser(currentId);
//        if (!apiResult.isSuccess()) {
//            return apiResult;
//        }

        Optional<User> byId = userRepository.findById(currentId);
        if (!byId.isPresent()) {
            throw  RestException.badRequest("You can't delete users, your account deleted");
        }

        if (!byId.get().isStatus()) {
            throw RestException.badRequest("You can't delete users, your account blocked");
        }
        List<User> allUsers = userRepository.findAll();
        for (Integer id : ids.getIds()) {
            for (User allUser : allUsers) {
                if (allUser.getId().equals(id)) {
                    allUser.setStatus(false);
                    userRepository.save(allUser);
                }
            }
        }
        return ApiResult.successResponse("blocked");
    }

    @Override
    public ApiResult<?> unblock(UserIdsDto ids, Integer currentId) {

        ApiResult<?> apiResult = checkCurrentUser(currentId);
        if (!apiResult.isSuccess()) {
            return apiResult;
        }

        List<User> allUsers = userRepository.findAll();
        for (Integer id : ids.getIds()) {
            for (User allUser : allUsers) {
                if (allUser.getId().equals(id)) {
                    allUser.setStatus(true);
                    userRepository.save(allUser);
                }
            }
        }
        return ApiResult.successResponse("Unblocked");
    }

    @Override
    public ApiResult<?> delete(UserIdsDto ids, Integer currentId) {
        ApiResult<?> apiResult = checkCurrentUser(currentId);
        if (!apiResult.isSuccess()) {
            return apiResult;
        }

        for (Integer idi : ids.getIds()) {
            Optional<User> user = userRepository.findById(idi);
            User user1 = user.get();
            user1.setEmail(null);
            userRepository.delete(user1);
        }
        return ApiResult.successResponse("deleted");
    }

    @Override
    public ApiResult<?> checkCurrentUser(Integer id) {

        Optional<User> byId = userRepository.findById(id);
        if (!byId.isPresent()) {
            throw  RestException.badRequest("You can't delete users, your account deleted");
        }

        if (!byId.get().isStatus()) {
            throw RestException.badRequest("You can't delete users, your account blocked");
        }

        return ApiResult.successResponse("success");

    }
}
