package com.sparta.hanghaememo.dto;

import com.sparta.hanghaememo.entity.Memo;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemoResponseDto {

    private Long id;
    private String username;
    private String title;
    private String contents;
    private LocalDateTime createdAt;

    public MemoResponseDto(Memo memo) {
        this.id = memo.getId();
        this.username = memo.getUsername();
        this.title = memo.getTitle();
        this.contents = memo.getContents();
        this.createdAt = memo.getCreatedAt();
    }
}
