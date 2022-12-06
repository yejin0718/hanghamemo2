package com.sparta.hanghaememo.service;

import com.sparta.hanghaememo.dto.*;
import com.sparta.hanghaememo.entity.User;
import com.sparta.hanghaememo.jwt.JwtUtil;
import com.sparta.hanghaememo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public SignupResponseDto signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        Optional<User> find = userRepository.findByUsername(username);
        if(find.isPresent()){
            throw new IllegalArgumentException("중복된 사용자가 존재합니다");
        }

        //아이디 검사
        if(3 > username.length() || username.length() > 11){ //아이디 길이 검사
            throw new IllegalArgumentException("아이디는 최소 4자 이상, 10자 이하로 작성해주세요");
        }

        boolean id_lower_check = false;
        boolean id_num_check = false;
        for(int i=0; i<username.length(); i++){ //아이디에 소문자, 숫자 포함되어 있는지 검사
            if(Character.isLowerCase(username.charAt(i))){
                id_lower_check = true;
            }
            else if(Character.isDigit(username.charAt(i)) == true){
                id_num_check = true;
            }
        }

        if(id_lower_check == false){
            throw new IllegalArgumentException("아이디에 알파벳 소문자가 포함되어야 합니다");
        }

        if(id_num_check == false){
            throw new IllegalArgumentException("아이디에 숫자가 포함되어야 합니다");
        }

        //비밀번호 검사
        if(8 > password.length() || password.length() > 15){ //아이디 길이 검사
            throw new IllegalArgumentException("비밀번호는 최소 8자 이상, 15자 이하로 작성해주세요");
        }

        boolean pw_lower_check = false;
        boolean pw_upper_check = false;
        boolean pw_num_check = false;
        for(int i=0; i<password.length(); i++){ //아이디에 소문자, 숫자 포함되어 있는지 검사
            if(Character.isLowerCase(password.charAt(i))){
                pw_lower_check = true;
            }
            else if(Character.isUpperCase(password.charAt(i))){
                pw_upper_check = true;
            }
            else if(Character.isDigit(password.charAt(i)) == true){
                pw_num_check = true;
            }
        }

        if(pw_lower_check == false){
            throw new IllegalArgumentException("비밀번호에 알파벳 소문자가 포함되어야 합니다");
        }
        if(pw_upper_check == false){
            throw new IllegalArgumentException("비밀번호에 알파벳 대문자가 포함되어야 합니다");
        }
        if(pw_num_check == false){
            throw new IllegalArgumentException("비밀번호에 숫자가 포함되어야 합니다");
        }


        User user = new User(username, password);
        userRepository.save(user);

        SignupResponseDto responseDto = new SignupResponseDto();

        return responseDto;

    }

    @Transactional
    public LoginResponseDto login(LoginRequestDto requestDto, HttpServletResponse response) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다")
        );

        if(!password.equals(user.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }

        LoginResponseDto responseDto = new LoginResponseDto();


        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername()));

        return responseDto;

    }

}
