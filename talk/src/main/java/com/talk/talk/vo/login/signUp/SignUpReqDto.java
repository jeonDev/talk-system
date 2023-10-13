package com.talk.talk.vo.login.signUp;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 회원가입용 Request Dto 객체
 * */
@Getter
@Setter
@ToString
public class SignUpReqDto {

    /**
     * 아이디
     */
    @Pattern(regexp = "^[a-zA-Z0-9]{4,16}$", message = "아이디는 영문 & 숫자 4~16자 사이로 입력 해 주세요.")
    private String id;

    /**
     * 패스워드
     */
    @Pattern(regexp = "^[A-Za-z0-9]{8,20}$", message = "비밀번호는 영문 & 숫자 8~20자 사이로 입력 해 주세요.")
    private String password;

    /**
     * 이름
     */
    @NotEmpty(message = "이름을 입력해주세요.")
    private String name;

    /**
     * 닉네임
     */
    @NotEmpty(message = "닉네임을 입력해주세요.")
    private String nickname;

    /**
     * 전화번호
     */
    @Pattern(regexp = "^(\\d{2,3})(\\d{3,4})(\\d{4})$", message = "핸드폰번호를 입력해주세요.")
    private String phone;

    /**
     * 이메일
     */
    @Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$", message = "이메일을 입력해주세요.")
    private String email;

}
