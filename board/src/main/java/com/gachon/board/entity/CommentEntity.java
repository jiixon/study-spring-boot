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
    @Column(name = "ID")
    private Long id;

    @Column(name = "COMMENT")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "POST_ID")
    private PostEntity postId;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserEntity userId;

    @Column(name = "CREATE_COMMENT_TIME")
    private LocalDateTime createCommentTime;


}
