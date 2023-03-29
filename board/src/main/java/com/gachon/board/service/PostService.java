package com.gachon.board.service;

import com.gachon.board.dto.PostDto;
import com.gachon.board.entity.PostEntity;
import com.gachon.board.entity.UserEntity;
import com.gachon.board.repository.PostRepository;
import com.gachon.board.repository.UserRepository;
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

        postEntity.setDeleteYn(false);
        postEntity.setDeletePostTime(null);
        postEntity.setCreatePostTime(LocalDateTime.now()); //글작성 시간
        postRepository.save(postEntity);

    }
    public void detailModifiedPost(Long postId, PostDto postDto){ //전달받은 DTO내용으로 디비에 반영
        PostEntity find = postRepository.findById(postId).get();

        find.setTitle(postDto.getTitle()); //수정된 제목 저장
        find.setContents(postDto.getContents()); //수정된 내용 저장
        find.setCreatePostTime(LocalDateTime.now());
        find.setDeletePostTime(LocalDateTime.now());

        postRepository.save(find);
    }
    public void deleteDetailPost(Long postId){ //글 삭제여부, 삭제시간 반영하는 메서드
        PostEntity postById = postRepository.findById(postId).get();
        postById.setDeleteYn(true); //삭제여부 true로 변경
        postById.setDeletePostTime(LocalDateTime.now()); //글 삭제시간

        postRepository.save(postById);

    }

}
