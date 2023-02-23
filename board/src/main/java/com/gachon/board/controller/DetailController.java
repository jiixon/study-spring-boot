package com.gachon.board.controller;

import com.gachon.board.entity.MemberEntity;
import com.gachon.board.entity.PostEntity;
import com.gachon.board.entity.UserEntity;
import com.gachon.board.service.DetailService;
import com.gachon.board.service.MemberService;
import com.gachon.board.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
public class DetailController {
    private final DetailService detailService;
    private final UserService userService;

    //post_user정보-user객체 안에있는 email(쓴사람)과 Oatu2user로 가져온 객체의 email(본사람) 같으면 보이게하기

   @GetMapping("/detail")
    public String dfadfd(Model model, @RequestParam Long postId, @AuthenticationPrincipal OAuth2User oAuth2User) throws Exception {
        PostEntity postByPostId = detailService.findPostByPostId(postId);
//        UserEntity userByEmail = userService.findByEmail(email);
        log.info("{}",postId);
//        log.info("{}",email);

//        if(postByPostId.getUserId().equals(userByEmail)){
//            model.addAttribute("isSameUser",true);
//
//        }
//        else {
//            model.addAttribute("isSameUser",false);
//        }

       String name = oAuth2User.getAttribute("name").toString();
       model.addAttribute("username",name);
       model.addAttribute("post",postByPostId);

        return "detail";

    }
}
