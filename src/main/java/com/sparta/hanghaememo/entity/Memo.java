package com.sparta.hanghaememo.entity;

import com.sparta.hanghaememo.dto.MemoRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Memo extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    public Memo(String username,String title, String contents, User user){
        this.username = username;
        this.title = title;
        this.contents = contents;
        this.user = user;
    } //이부분을 빼고 디티오에 ? 의존성? 음?

    public void update(MemoRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }



}
