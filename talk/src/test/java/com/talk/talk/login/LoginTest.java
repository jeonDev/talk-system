package com.talk.talk.login;

import com.talk.talk.domain.user.User;
import com.talk.talk.domain.user.UserRepository;
import com.talk.talk.service.UserService;
import com.talk.talk.vo.login.login.LoginReqDto;
import com.talk.talk.vo.login.login.LoginResDto;
import com.talk.talk.vo.login.signUp.SignUpReqDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.assertTrue;


import java.util.Optional;

@SpringBootTest
public class LoginTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    // TODO: 회원가입 한 고객 삭제 안되게 처리 해야 함.
    @Test
    void 회원가입_정상() {
        // given
        SignUpReqDto request = new SignUpReqDto();
        request.setId("testUser");
        request.setPassword("testPassword");
        request.setName("테스트1");
        request.setNickname("테스트맨");
        request.setPhone("01011112222");
        request.setEmail("test@gmail.com");
        // when
        userService.signUp(request);
        // then
        Optional<User> user = userRepository.findById(1L);
        assertTrue("", user.isPresent());
    }

    @Test
    void 로그인_정상() {
        // given
        LoginReqDto request = new LoginReqDto();
        request.setId("testUser");
        request.setPassword("testPassword");
        // when
        LoginResDto login = userService.login(request);
        // then
        assertThat(login.getName()).isEqualTo("테스트1");
    }

    @Test
    void 로그인_존재하지않는고객() {
        // given
        LoginReqDto request = new LoginReqDto();
        request.setId("testUser1");
        request.setPassword("testPassword");
        // when & then
        assertThrows(IllegalArgumentException.class, () -> {
            userService.login(request);
        });
    }

    @Test
    void 로그인_패스워드오류() {
        // given
        LoginReqDto request = new LoginReqDto();
        request.setId("testUser");
        request.setPassword("errorPassword");
        // when & then
        assertThrows(IllegalArgumentException.class, () -> {
            userService.login(request);
        });
    }
}
