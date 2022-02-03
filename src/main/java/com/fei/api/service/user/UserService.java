package com.fei.api.service.user;

import com.fei.api.domain.user.User;
import com.fei.api.domain.user.UserRepository;
import com.fei.api.security.TokenProvider;
import com.fei.api.web.dto.user.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    private final TokenProvider tokenProvider;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public UserResponseDto signup(UserSaveRequestDto userSaveRequestDto) {
        if (userSaveRequestDto == null || "".equals(userSaveRequestDto.getUserId())) {
            throw new RuntimeException("Invalid argument");
        }
        String userId = userSaveRequestDto.getUserId();
        if(userRepository.existsByUserId(userId)){
            log.warn("ID already exists", userId);
            throw new RuntimeException("ID already exists");
        }
        String encodedPassword = passwordEncoder.encode(userSaveRequestDto.getPw());
        User saveUser = userRepository.save(userSaveRequestDto.toEntity());
        String token = tokenProvider.create(saveUser);
        return new UserResponseDto(
                saveUser,
                token
        );
    }
    public UserResponseDto login(UserLoginRequestDto userLoginRequestDto) {
        User user = getByCredentials(userLoginRequestDto.getLoginType(), userLoginRequestDto.getOauthId(), passwordEncoder);

        if (user != null) {
            String token = tokenProvider.create(user);
            return new UserResponseDto(user, token);
        } else {
            throw new RuntimeException("해당 아이디가 존재하지 않습니다.");
        }
    }

    @Transactional
    public UserResponseDto getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 사용자가 없습니다.id="+id)
        );
        return new UserResponseDto(user,null);
    }

    @Transactional
    public UserResponseWithoutTokenDto getBlogUser(String userId) {
        User user = userRepository.findByUserId(userId);
        if(null == user) {
            throw new RuntimeException("해당 아이디가 존재하지 않습니다.");
        }

        return new UserResponseWithoutTokenDto(user);
    }

    public User getByCredentials(String loginType, String oauthId, PasswordEncoder encoder) {
        User originUser = null;
        if ("KAKAO".equals(loginType)) {
            originUser = userRepository.findByOauthId(oauthId);
        }

        if (originUser == null) {
            throw new RuntimeException("해당 아이디가 없습니다.");
        }
//        if (!encoder.matches(pw, originUser.getPw())) {
//            throw new RuntimeException("비밀번호가 맞지 않습니다.");
//        }
        if (originUser != null) {
            return originUser;
        }
        return null;
    }

    @Transactional
    public UserListResponseDto getUsers(Pageable pageable) {
        Page<User> pageData = userRepository.findUserList(pageable);
        return new UserListResponseDto(
                pageData.getContent(),
                pageData.getTotalElements(),
                pageData.getPageable().getPageNumber()
        );
    }

    @Transactional
    public UserResponseWithoutTokenDto update(Long id, UserUpdateRequestDto requestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id="+id));

        return new UserResponseWithoutTokenDto(user.update(requestDto.toEntity()));
    }
}
