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

    @Column
    private String title;

    @Column
    private String contents;

    @ManyToOne
    @JoinColumn
    private UserEntity userId; //??

    @Column
    private Boolean deleteYn;

    @Column
    private LocalDateTime deletePostTime;

    @Column
    private LocalDateTime createPostTime; //Coulmn 이름(CREATE_POST_TIME)



}
