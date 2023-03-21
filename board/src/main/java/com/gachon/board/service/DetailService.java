package com.gachon.board.service;

import com.gachon.board.entity.PostEntity;
import com.gachon.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DetailService {
    private final PostRepository postRepository;

    public PostEntity findPostByPostId(Long postId) throws Exception {
        Optional<PostEntity> byId = postRepository.findById(postId);
        if (byId.isPresent()){
            return byId.get();
        }
        else {
            throw new Exception("not found post");
        }


    }

}
