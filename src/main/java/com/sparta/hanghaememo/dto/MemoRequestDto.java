package com.sparta.hanghaememo.dto;

import lombok.Getter;

@Getter
public class MemoRequestDto {

    private String title;
    private String username;
    private String contents;
    private String pwd;

    public String findPwd(MemoRequestDto requestDto){
        String pwd= requestDto.getPwd();
        return pwd;
    }
}
