package com.gachon.board.service;

import com.gachon.board.dto.CommentDto;
import com.gachon.board.entity.CommentEntity;
import com.gachon.board.entity.PostEntity;
import com.gachon.board.entity.UserEntity;
import com.gachon.board.repository.CommentRepository;
import com.gachon.board.repository.PostRepository;
import com.gachon.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public void saveComment(CommentDto commentDto, String email){

        Optional<PostEntity> postById = postRepository.findById(commentDto.getPostId());
        Optional<UserEntity> userByEmail = userRepository.findByEmail(email);

        log.info("userByEmail.get(): {}",userByEmail.get());
        log.info("postById: {}",postById);
        log.info("postById.get(): {}",postById.get());

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setComment(commentDto.getComment()); //댓글 내용 저장
        commentEntity.setCreateCommentTime(LocalDateTime.now()); //댓글 단 시간 저장
        commentEntity.setUserId(userByEmail.get()); //댓글 단 userId저장
        commentEntity.setPostId(postById.get()); //postId 글의 id 저장

        commentRepository.save(commentEntity);
    }
    public List<CommentEntity> findCommentList(){
        List<CommentEntity> all = commentRepository.findAll();

        return all;
    }

}
