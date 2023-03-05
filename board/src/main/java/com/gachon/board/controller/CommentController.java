package com.gachon.board.controller;

import com.gachon.board.dto.CommentDto;
import com.gachon.board.dto.PostDto;
import com.gachon.board.entity.PostEntity;
import com.gachon.board.service.CommentService;
import com.gachon.board.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final PostService postService;


    @PostMapping("/save/comment")
    public String saveComment(CommentDto commentDto,Long postId, RedirectAttributes redirect, @AuthenticationPrincipal OAuth2User oAuth2User){
        log.info("{}",commentDto.getComment());




        String email = oAuth2User.getAttribute("email").toString();

        redirect.addAttribute("postId",postId);

        commentService.saveComment(commentDto,email);

        return "redirect:/detail";
    }




}
