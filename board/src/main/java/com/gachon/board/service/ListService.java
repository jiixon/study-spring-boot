package com.gachon.board.service;

import com.gachon.board.entity.PostEntity;
import com.gachon.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ListService {
    private final PostRepository postRepository;

    public List<PostEntity> findPostList(){
        List<PostEntity> all = postRepository.findAll();

        return all;
    }
    public List<PostEntity> getPostRepositoryAllByDeleteYn(){ // 삭제여부가 false로 된 모든 PostEntity 검색
        return postRepository.findAllByDeleteYn(false);
    }
}
