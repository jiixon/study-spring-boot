package com.gachon.board.dto;

import lombok.Data;

@Data
public class CommentDto {
    public String comment; //댓글 내용
//    public Long writerId; //댓글작성자ID
    public Long postId; //해당 게시물ID

}
