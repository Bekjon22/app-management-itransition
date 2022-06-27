package com.itransition.payload;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Bekjon Bakhromov
 * @created 10.06.2022-11:01 PM
 */

@Getter
@Setter
public class UserIdsDto {
    private List<Integer> ids;
}
