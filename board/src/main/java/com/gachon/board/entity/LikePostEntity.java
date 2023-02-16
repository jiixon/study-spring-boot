package com.gachon.board.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NegativeOrZero;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LikePostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn
    private MemberEntity memberId;

    @ManyToOne
    @JoinColumn
    private PostEntity postId;

}
