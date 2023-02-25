package com.gachon.board.service;

import com.gachon.board.dto.CommentDto;
import com.gachon.board.entity.CommentEntity;
import com.gachon.board.entity.PostEntity;
import com.gachon.board.entity.UserEntity;
import com.gachon.board.repository.CommentRepository;
import com.gachon.board.repository.PostRepository;
import com.gachon.board.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public void saveComment(CommentDto commentDto, String email){

//        Optional<PostEntity> postById = postRepository.findById(commentDto.getCommentId());
        Optional<UserEntity> userByEmail = userRepository.findByEmail(email);

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setComment(commentDto.getComment());
        commentEntity.setCreateCommentTime(LocalDateTime.now());


        //userId
        commentEntity.setUserId(userByEmail.get()); //댓글 단 userId저장

        //postId 글의 id 저장

        //commentEntity.setPostId(postById.get());



        commentRepository.save(commentEntity);



    }
}
