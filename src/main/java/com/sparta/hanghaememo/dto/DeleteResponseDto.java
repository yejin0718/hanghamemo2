package com.sparta.hanghaememo.dto;

import lombok.Getter;

@Getter
public class DeleteResponseDto {
    private String msg;
    private Long statusCode;

    public DeleteResponseDto(){
        this.msg = "삭제 성공";
        this.statusCode = Long.valueOf(200);
    }
}
