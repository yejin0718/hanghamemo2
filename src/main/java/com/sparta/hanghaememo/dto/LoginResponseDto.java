package com.sparta.hanghaememo.dto;

import lombok.Getter;
import lombok.val;

@Getter
public class LoginResponseDto {
    private String msg;
    private Long statusCode;


    public LoginResponseDto(){
        this.msg = "로그인 성공";
        this.statusCode = Long.valueOf(200);
    }
}
