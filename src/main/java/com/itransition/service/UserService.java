package com.itransition.service;

import com.itransition.payload.ApiResult;
import com.itransition.payload.UserDto;
import com.itransition.payload.UserIdsDto;

import java.util.List;

/**
 * @author Bekjon Bakhromov
 * @created 05.06.2022-11:28 AM
 */

public interface UserService {


    ApiResult<List<UserDto>> getList(Integer id);

    ApiResult<?> block(UserIdsDto ids,Integer currentId);

    ApiResult<?> unblock(UserIdsDto ids, Integer currentId);

    ApiResult<?> delete(UserIdsDto ids,Integer currenId);

    ApiResult<?> checkCurrentUser(Integer id);

}
