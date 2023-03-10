package com.gachon.board.repository;

import com.gachon.board.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface CommentRepository extends JpaRepository<CommentEntity,Long> {
    Optional<CommentEntity> findById(Long commentId);
}
