package com.sparta.hanghaememo.dto;

import com.sparta.hanghaememo.entity.Memo;
import lombok.Getter;

@Getter
public class UpdateResponseDto {

    private String title;

    private String contents;

    public UpdateResponseDto(String title, String contents){
        this.title = title;
        this.contents = contents;
    }

}
