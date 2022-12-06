package com.sparta.hanghaememo.controller;

import com.sparta.hanghaememo.dto.MemoMapping;
import com.sparta.hanghaememo.dto.MemoRequestDto;
import com.sparta.hanghaememo.dto.MemoResponseDto;
import com.sparta.hanghaememo.entity.Memo;
import com.sparta.hanghaememo.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @GetMapping("/")
    public ModelAndView home(){
        return new ModelAndView("index");
    }

    @PostMapping("/api/memos") //생성
    public Memo createMemo(@RequestBody MemoRequestDto requestDto){
        return memoService.createMemo(requestDto);
    }

    @GetMapping("/api/memos") //조회
    public List<MemoMapping> getMemos(){
        return memoService.getMemos();
    }

    @GetMapping("/api/memos/{id}")//조회2
    public MemoResponseDto showMemos(@PathVariable Long id){
        return memoService.showMemos(id);
    }

    @PutMapping("/api/memos/{id}") //수정
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto){
        return memoService.update(id, requestDto);
    }

    @DeleteMapping("/api/memos/{id}") //삭제
    public Long deleteMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto){
        return memoService.deleteMemo(id, requestDto);
    }
}
