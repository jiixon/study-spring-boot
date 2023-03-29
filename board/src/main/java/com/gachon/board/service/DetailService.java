package com.gachon.board.service;

import com.gachon.board.entity.PostEntity;
import com.gachon.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
    //public List<TableEntity> getTableRepositoryAllByDeleteYnLike() { // 삭제여부가 false로  된 모든 TableEntity 검색
//    return tableRepository.findAllByDeleteYnLike(false);
//}



}
