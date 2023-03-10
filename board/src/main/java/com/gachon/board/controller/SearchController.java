package com.gachon.board.controller;

import com.gachon.board.entity.PostEntity;
import com.gachon.board.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;

    @GetMapping("/search") //제목으로 글찾기
    public String showSearchList(Model model, @RequestParam String keyword, @AuthenticationPrincipal OAuth2User oAuth2User){
        List<PostEntity> contentsByTitle = searchService.getContentsByTitle(keyword);
        String name = oAuth2User.getAttribute("name").toString();
        model.addAttribute("postlist",contentsByTitle);
        model.addAttribute("username",name);
        return "index";

    }
}
