package com.sparta.hanghaememo.dto;

import com.sparta.hanghaememo.entity.User;
import lombok.Getter;

import java.util.Optional;

@Getter
public class SignupResponseDto {
    private String msg;
    private Long statusCode;

    public SignupResponseDto(){
        this.msg = "회원가입 성공";
        this.statusCode = Long.valueOf(200);
    }

}
