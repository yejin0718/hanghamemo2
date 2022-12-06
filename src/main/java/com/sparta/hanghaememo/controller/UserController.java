package com.sparta.hanghaememo.controller;

import com.sparta.hanghaememo.dto.*;
import com.sparta.hanghaememo.entity.Memo;
import com.sparta.hanghaememo.service.MemoService;
import com.sparta.hanghaememo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.experimental.theories.ParametersSuppliedBy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/signup") // 회원가입
    public SignupResponseDto signup(@RequestBody SignupRequestDto requestDto){
        return userService.signup(requestDto);
    }

    @ResponseBody
    @PostMapping("/api/login") //로그인
    public LoginResponseDto login(@RequestBody LoginRequestDto requestDto, HttpServletResponse response){
        return userService.login(requestDto, response);
    }


}
