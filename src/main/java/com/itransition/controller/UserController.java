package com.itransition.controller;

import com.itransition.payload.ApiResult;
import com.itransition.payload.UserDto;
import com.itransition.payload.UserIdsDto;
import com.itransition.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bekjon Bakhromov
 * @created 05.06.2022-2:32 AM
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/get/all")
    public ApiResult<List<UserDto>> getList(@RequestParam Integer id) {
        return userService.getList(id);
    }

    @PutMapping("/block")
    public ApiResult<?> blockUsers(@RequestBody UserIdsDto ids,
                                   @RequestParam Integer currentId) {
        return userService.block(ids, currentId);
    }

    @PutMapping("/unblock")
    public ApiResult<?> unblockUsers(@RequestBody UserIdsDto ids,
                                     @RequestParam Integer currentId) {
        return userService.unblock(ids, currentId);
    }

    @DeleteMapping("/delete")
    public ApiResult<?> delete(@RequestBody UserIdsDto ids,
                               @RequestParam Integer currentId) {
        return userService.delete(ids, currentId);
    }
}
