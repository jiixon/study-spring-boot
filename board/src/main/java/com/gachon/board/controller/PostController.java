package com.gachon.board.controller;

import com.gachon.board.dto.PostDto;
import com.gachon.board.entity.PostEntity;
import com.gachon.board.service.DetailService;
import com.gachon.board.service.ListService;
import com.gachon.board.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor

//RequiredArgsConstructor + private final -> autowired 안써도됨
public class PostController {

    private final PostService postService;
    private final ListService listService;


    @PostMapping("/save/post") // 글 저장
    public String savePost(PostDto title, @AuthenticationPrincipal OAuth2User oAuth2User){
        log.info("title: {}",title.getTitle());

        postService.savePost(title,oAuth2User.getAttribute("email").toString());

        return "redirect:/";
    }
    @GetMapping("/post")
    public String post(@AuthenticationPrincipal OAuth2User oAuth2User,Model model){
        String name = oAuth2User.getAttribute("name").toString();
        model.addAttribute("username",name);
        return "post";
    }

    @GetMapping("/") //메인
    public String showPostList(Model model, @AuthenticationPrincipal OAuth2User oAuth2User){
        log.info("{}",oAuth2User.getAttributes());
        log.info("{}",oAuth2User.getAttribute("name").toString());

        String name = oAuth2User.getAttribute("name").toString();
        List<PostEntity> postList = listService.findPostList();//글전체 검색 후 리스트에 저장
        model.addAttribute("postlist",postList);
        model.addAttribute("username",name);
        return "index";

    }
//    //json으로 할때 @RequestBody 붙여야함
//    @PostMapping("/test")
//    public ResponseEntity<?> test(@RequestBody PostDto postDto){ //?
//        log.info("{}",postDto.getTitle());
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }



}
