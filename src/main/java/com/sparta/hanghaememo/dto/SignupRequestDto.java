package com.sparta.hanghaememo.dto;

import com.sparta.hanghaememo.entity.User;
import lombok.Getter;

import java.util.Optional;

@Getter
public class SignupRequestDto {

    private String username;
    private String password;

}
