package com.gachon.board.service;

import com.gachon.board.dto.PostDto;
import com.gachon.board.entity.MemberEntity;
import com.gachon.board.entity.PostEntity;
import com.gachon.board.entity.UserEntity;
import com.gachon.board.repository.MemberRepository;
import com.gachon.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    public void savePost(PostDto postDto){
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


        UserEntity userEntity = new UserEntity();


        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(postDto.getTitle());
        postEntity.setContents(postDto.getContents());

        //UserId 어케해야할까,,,??
        postEntity.setDeleteYn(true); //일단은 이렇게,,
        postEntity.setDeletePostTime(LocalDateTime.now()); //이것도 일단 이렇게,,
        postEntity.setCreatePostTime(LocalDateTime.now());
        postRepository.save(postEntity);

    }
}
