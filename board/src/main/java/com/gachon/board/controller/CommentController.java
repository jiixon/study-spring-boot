package com.gachon.board.controller;

import com.gachon.board.dto.CommentDto;
import com.gachon.board.dto.PostDto;
import com.gachon.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;


    @PostMapping("/save/comment")
    public String saveComment(CommentDto commentDto,@AuthenticationPrincipal OAuth2User oAuth2User){
        log.info("{}",commentDto.getComment());

        String email = oAuth2User.getAttribute("email").toString();

        commentService.saveComment(commentDto,email);

        return "redirect:/detail";
    }




}