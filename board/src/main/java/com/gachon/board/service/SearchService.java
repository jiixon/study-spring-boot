package com.gachon.board.service;

import com.gachon.board.entity.PostEntity;
import com.gachon.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final PostRepository postRepository;

    public List<PostEntity> getContentsByTitle(String keyword){
        List<PostEntity> allByTitleContaining = postRepository.findAllByTitleContaining(keyword);

        return  allByTitleContaining;
    }
}
