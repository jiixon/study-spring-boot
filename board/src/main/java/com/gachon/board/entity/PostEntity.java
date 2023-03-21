package com.gachon.board.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity //JPA가 관린하는 entity
public class PostEntity {
    @Id //Entity에서 PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //IDENTITY PK 1부터 하나씩 증가
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENTS")
    private String contents;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserEntity userId;

    @Column(name = "DELETE_YN")
    private Boolean deleteYn;

    @Column(name = "DELETE_POST_TIME")
    private LocalDateTime deletePostTime;

    @Column(name = "CREATE_POST_TIME")
    private LocalDateTime createPostTime; //Coulmn 이름(CREATE_POST_TIME)



}
