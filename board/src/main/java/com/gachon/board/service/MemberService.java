package com.gachon.board.service;

import com.gachon.board.entity.MemberEntity;
import com.gachon.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberEntity findMemberByEmail(String email) throws Exception {
        Optional<MemberEntity> byEmail = memberRepository.findByEmail(email);
        if (byEmail.isPresent()){
            return byEmail.get(); //Optional에서 까서 준다
        }
        else {
            throw new Exception("Not found member");
        }
    }
}
