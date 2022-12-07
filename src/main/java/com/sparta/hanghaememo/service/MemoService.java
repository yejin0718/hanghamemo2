package com.sparta.hanghaememo.service;

import com.sparta.hanghaememo.dto.DeleteResponseDto;
import com.sparta.hanghaememo.dto.MemoRequestDto;
import com.sparta.hanghaememo.dto.MemoResponseDto;
import com.sparta.hanghaememo.entity.Memo;
import com.sparta.hanghaememo.entity.User;
import com.sparta.hanghaememo.jwt.JwtUtil;
import com.sparta.hanghaememo.repository.MemoRepository;
import com.sparta.hanghaememo.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public Memo createMemo(MemoRequestDto requestDto, HttpServletRequest request) {//생성

        String username = requestDto.getUsername();
        String title = requestDto.getTitle();
        String contens = requestDto.getContents();

        String token = jwtUtil.resolveToken(request); //request에서 token 가져오기
        Claims claims = null; //username 담을 변

        if (token != null) {
            if (jwtUtil.validateToken(token)) {//유효한 토큰인지 검사
                claims = jwtUtil.getUserInfoFromToken(token);// 토큰에서 사용자 정보 가져오기
            } else {//유효한 토큰이 아니면
                throw new IllegalArgumentException("Token Error"); //토큰 에러 메세지 출력
            }
        }

        //1. user에서 username으로 찾는다
        User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
        );

        //2. memo에 username에 저장해준다
        Memo memo = new Memo(username, title, contens, user);

        memoRepository.save(memo);
        return memo;
    }

    @Transactional
    public List<MemoResponseDto> getMemos(){//조회
        return memoRepository.findAllByOrderByModifiedAtDesc().stream()
                .map(MemoResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public MemoResponseDto showMemos(Long id) { //조회2
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        MemoResponseDto responseDto = new MemoResponseDto(memo);

        return responseDto;
    }

    @Transactional
    public MemoRequestDto update(Long id, MemoRequestDto requestDto, HttpServletRequest request) {//수정

        String token = jwtUtil.resolveToken(request); //request에서 token 가져오기
        Claims claims = null; //username 담을 변수

        if (token != null) {
            if (jwtUtil.validateToken(token)) {//유효한 토큰인지 검사
                claims = jwtUtil.getUserInfoFromToken(token);// 토큰에서 사용자 정보 가져오기
            } else {//유효한 토큰이 아니면
                throw new IllegalArgumentException("Token Error"); //토큰 에러 메세지 출력
            }
        }

        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        if(memo.getUser().getUsername().equals(claims.getSubject()) == false){
            throw new IllegalArgumentException("본인이 작성한 메모만 수정할 수 있습니다.");
        }

        memo.update(requestDto);

        return requestDto;
    }

    @Transactional
    public DeleteResponseDto deleteMemo(Long id, MemoRequestDto requestDto, HttpServletRequest request) { //삭제

        String token = jwtUtil.resolveToken(request); //request에서 token 가져오기
        Claims claims = null; //username 담을 변수

        if (token != null) {
            if (jwtUtil.validateToken(token)) {//유효한 토큰인지 검사
                claims = jwtUtil.getUserInfoFromToken(token);// 토큰에서 사용자 정보 가져오기
            } else {//유효한 토큰이 아니면
                throw new IllegalArgumentException("Token Error"); //토큰 에러 메세지 출력
            }
        }

        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        System.out.println("claims.getId() = " + claims.getId());

        if(memo.getUser().getUsername().equals(claims.getSubject()) == false){
            throw new IllegalArgumentException("본인이 작성한 메모만 삭제할 수 있습니다.");
        }

        memoRepository.deleteById(id);

        DeleteResponseDto deleteResponseDto = new DeleteResponseDto();

        return deleteResponseDto;
    }

    }
