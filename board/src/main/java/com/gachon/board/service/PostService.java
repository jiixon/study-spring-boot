package com.gachon.board.service;

import com.gachon.board.dto.PostDto;
import com.gachon.board.entity.PostEntity;
import com.gachon.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    public void savePost(PostDto postDto){
        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(postDto.getTitle());
        postEntity.setContents(postDto.getContents());
        postEntity.setUser("afd@fad");
        postEntity.setCreatePostTime(LocalDateTime.now());
        postRepository.save(postEntity);


    }
}
