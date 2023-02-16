package com.gachon.board.controller;

import com.gachon.board.dto.PostDto;
import com.gachon.board.entity.PostEntity;
import com.gachon.board.service.ListService;
import com.gachon.board.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor

//RequiredArgsConstructor + private final -> autowired 안써도됨
public class PostController {

    private final PostService postService;
    private final ListService listService;



//    @PostMapping("/post")
//    public String savePost(PostDto title){ //?
//        log.info("title: {}",title.getTitle());
//        postService.savePost(title);
//        return "redirect:/";
//    }
//
    @GetMapping("/")
    public String showPostList(Model model){
        List<PostEntity> postList = listService.findPostList();//글전체 검색 후 리스트에 저장
        model.addAttribute("postlist",postList);
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
