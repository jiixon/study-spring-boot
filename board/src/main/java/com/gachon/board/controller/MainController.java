package com.gachon.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MainController {

    @GetMapping("/post")
    String list(){
        //model.addAttribute("sw", "title");
        return "post";

    }
    @GetMapping("/login")
    String list_login(){
        //model.addAttribute("sw", "title");
        return "login";

    }
    @GetMapping("/join")
    String list_join(){
        //model.addAttribute("sw", "title");
        return "join";

    }
    @GetMapping("/detail")
    String list_detail(){
        //model.addAttribute("sw", "title");
        return "detail";

    }
}
