package com.sparta.hanghaememo.controller;

import com.sparta.hanghaememo.dto.DeleteResponseDto;
import com.sparta.hanghaememo.dto.MemoRequestDto;
import com.sparta.hanghaememo.dto.MemoResponseDto;
import com.sparta.hanghaememo.entity.Memo;
import com.sparta.hanghaememo.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @GetMapping("/")
    public ModelAndView home(){
        return new ModelAndView("index");
    }

    @PostMapping("/api/memos") //생성
    public Memo createMemo(@RequestBody MemoRequestDto requestDto,HttpServletRequest request){
        return memoService.createMemo(requestDto, request);
    }

    @GetMapping("/api/memos") //조회
    public List<MemoResponseDto> getMemos(){
        return memoService.getMemos();
    }

    @GetMapping("/api/memos/{id}")//조회2
    public MemoResponseDto showMemos(@PathVariable Long id){
        return memoService.showMemos(id);
    }

    @PutMapping("/api/memos/{id}") //수정
    public MemoRequestDto updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto,HttpServletRequest request){
        return memoService.update(id, requestDto, request);
    }

    @DeleteMapping("/api/memos/{id}") //삭제
    public DeleteResponseDto deleteMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto, HttpServletRequest request){
        return memoService.deleteMemo(id, requestDto, request);
    }

}
