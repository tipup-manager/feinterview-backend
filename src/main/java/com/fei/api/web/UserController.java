package com.fei.api.web;

import com.fei.api.service.user.UserService;
import com.fei.api.web.dto.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/api/v1/user/{id}")
    public UserResponseDto getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping("/api/v1/blog/{userId}")
    public UserResponseWithoutTokenDto getBlogUser(@PathVariable String userId) {
        return userService.getBlogUser(userId);
    }

    @PostMapping("/api/v1/user/signup")
    public UserResponseDto signup(@RequestBody UserSaveRequestDto requestDto) {
        return userService.signup(requestDto);
    }

    @PostMapping("/api/v1/user/login")
    public UserResponseDto login(@RequestBody UserLoginRequestDto requestDto) {
        return userService.login(requestDto);
    }

    @GetMapping("/api/v1/user/list")
    public UserListResponseDto getUsers(@RequestParam(defaultValue = "0") int page) {
        Pageable sortedByModifiedDateDesc = PageRequest.of(page, 10, Sort.by("CREATED_DATE").descending());
        return userService.getUsers(sortedByModifiedDateDesc);
    }

    @PutMapping("/api/v1/user/{id}")
    public UserResponseWithoutTokenDto update(@PathVariable Long id, @RequestBody @Valid UserUpdateRequestDto requestDto) {
        return userService.update(id, requestDto);
    }
}
