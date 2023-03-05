package com.gachon.board.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String comment;

    @ManyToOne
    @JoinColumn
    private PostEntity postId;

    @ManyToOne
    @JoinColumn
    private UserEntity userId;

    @Column
    private LocalDateTime createCommentTime;


}
