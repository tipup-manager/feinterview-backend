package com.fei.api.service.user;

import com.fei.api.domain.user.User;
import com.fei.api.domain.user.UserRepository;
import com.fei.api.security.TokenProvider;
import com.fei.api.web.dto.user.UserListResponseDto;
import com.fei.api.web.dto.user.UserLoginRequestDto;
import com.fei.api.web.dto.user.UserResponseDto;
import com.fei.api.web.dto.user.UserSaveRequestDto;
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
        return new UserResponseDto(
                userRepository.save(new UserSaveRequestDto(
                    userSaveRequestDto.getName(),
                    userSaveRequestDto.getUserId(),
                    userSaveRequestDto.getAge(),
                    userSaveRequestDto.getGender(),
                    encodedPassword,
                    userSaveRequestDto.getRole()
                ).toEntity()),
                null
        );
    }
    public UserResponseDto login(UserLoginRequestDto userLoginRequestDto) {
        User user = getByCredentials(userLoginRequestDto.getUserId(), userLoginRequestDto.getPw(), passwordEncoder);

        if (user != null) {
            final String token = tokenProvider.create(user);
            return new UserResponseDto(user, token);
        } else {
            throw new RuntimeException("해당 아이디가 없습니다.");
        }
    }

    @Transactional
    public UserResponseDto getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 사용자가 없습니다.id="+id)
        );
        return new UserResponseDto(user,null);
    }

    public User getByCredentials(final String userId, final String pw, final PasswordEncoder encoder) {
        User originUser = userRepository.findByUserId(userId);

        if (!encoder.matches(pw, originUser.getPw())) {
            throw new RuntimeException("비밀번호가 맞지 않습니다.");
        }
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
}
