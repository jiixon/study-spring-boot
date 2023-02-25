package com.gachon.board.service;

import com.gachon.board.dto.PostDto;
import com.gachon.board.entity.MemberEntity;
import com.gachon.board.entity.PostEntity;
import com.gachon.board.entity.UserEntity;
import com.gachon.board.repository.MemberRepository;
import com.gachon.board.repository.PostRepository;
import com.gachon.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final UserRepository userRepository;
    public void savePost(PostDto postDto, String email){

        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(postDto.getTitle()); //글 제목
        postEntity.setContents(postDto.getContents()); //글 내용

        Optional<UserEntity> byEmail = userRepository.findByEmail(email);
        if(byEmail.isPresent()){
            postEntity.setUserId(byEmail.get()); //글쓴유저 ID저장
        }else {
            log.error("가입되지 않는 이메일입니다");
        }

        //UserId 어케해야할까,,,??
        postEntity.setDeleteYn(true); //일단은 이렇게,,
        postEntity.setDeletePostTime(LocalDateTime.now()); //이것도 일단 이렇게,,
        postEntity.setCreatePostTime(LocalDateTime.now()); //글작성 시간
        postRepository.save(postEntity);


        //Optional<MemberEntity> byEmail = memberRepository.findByEmail(postDto.getEmail());
//        if(byEmail.isPresent()) {
//            PostEntity postEntity = new PostEntity();
//            postEntity.setTitle(postDto.getTitle());
//            postEntity.setContents(postDto.getContents());
//            postEntity.setMemberId(byEmail.get());
//            postEntity.setCreatePostTime(LocalDateTime.now());
//            postRepository.save(postEntity);
//        }
//        else {
//            log.error("Member is not found");
//        }


//        MemberEntity build = MemberEntity.builder().email("apdf@apdf").joinDate(LocalDateTime.now()).password("dwfa").name("aaa").build();
//        memberRepository.save(build);




    }
}
