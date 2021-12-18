package com.fei.api.web.dto.user;

import com.fei.api.domain.user.User;
import lombok.Getter;

import java.util.List;

@Getter
public class UserListResponseDto {

    private List<User> list;
    private Long totalCount;
    private int pageNumber;

    public UserListResponseDto(List<User> list, Long totalCount, int pageNumber) {
        this.totalCount = totalCount;
        this.list = list;
        this.pageNumber = pageNumber;
    }
}
