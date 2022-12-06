package com.sparta.hanghaememo.service;

import com.sparta.hanghaememo.dto.MemoMapping;
import com.sparta.hanghaememo.dto.MemoRequestDto;
import com.sparta.hanghaememo.dto.MemoResponseDto;
import com.sparta.hanghaememo.entity.Memo;
import com.sparta.hanghaememo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    @Transactional
    public Memo createMemo(MemoRequestDto requestDto){//생성
        Memo memo = new Memo(requestDto);
        memoRepository.save(memo);
        return memo;
    }

    @Transactional
    public List<MemoMapping> getMemos(){//조회
        List<MemoMapping> allByOrderByModifiedAtDesc = memoRepository.findAllByOrderByModifiedAtDesc();
        return allByOrderByModifiedAtDesc;
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
    public Long update(Long id, MemoRequestDto requestDto) {//수정
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        String pwd = requestDto.findPwd(requestDto);

        if(pwd.equals(memo.getPwd())){
            memo.update(requestDto);
        }
        return memo.getId();
    }

    @Transactional
    public Long deleteMemo(Long id, MemoRequestDto requestDto) { //삭제
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        String pwd = requestDto.findPwd(requestDto);

        if(pwd.equals(memo.getPwd())){
            memoRepository.deleteById(id);
        }

        return id;
    }
}
