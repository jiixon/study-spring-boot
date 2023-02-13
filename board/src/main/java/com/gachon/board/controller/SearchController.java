package com.gachon.board.controller;

import com.gachon.board.entity.PostEntity;
import com.gachon.board.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @GetMapping("/search")
    public String showSearchList(Model model, @RequestParam String keyword){
        List<PostEntity> contentsByTitle = searchService.getContentsByTitle(keyword);
        model.addAttribute("postlist",contentsByTitle);

        return "index";

    }
}
