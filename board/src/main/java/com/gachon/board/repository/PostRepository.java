package com.gachon.board.repository;

import com.gachon.board.entity.PostEntity;
import com.gachon.board.service.ListService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//<Entity class,해당하는 pk 타입>
public interface PostRepository extends JpaRepository<PostEntity,Long> {
    List<PostEntity> findAllByTitleContaining(String keyword); //스프링데이터jpa쿼리메소드 규칙

}
