package com.gachon.board.controller;

import com.gachon.board.entity.CommentEntity;
import com.gachon.board.entity.PostEntity;
import com.gachon.board.service.CommentService;
import com.gachon.board.service.DetailService;
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

@Controller
@Slf4j
@RequiredArgsConstructor
public class DetailController {
    private final DetailService detailService;
    private final CommentService commentService;
    private final PostService postService;

    //post_user정보-user객체 안에있는 email(쓴사람)과 Oauth2user로 가져온 객체의 email(본사람) 같으면 보이게하기

   @GetMapping("/detail") //글 상세조회
    public String detail(Model model, Long postId, @AuthenticationPrincipal OAuth2User oAuth2User) throws Exception {
       PostEntity postByPostId = detailService.findPostByPostId(postId);
       String userByEmail = oAuth2User.getAttribute("email").toString();
       log.info("postId: {}",postId);
       log.info("userByEmail: {}",userByEmail);
       log.info("postByPostId: {}",postByPostId);
       String writerByEmail = postByPostId.getUserId().getEmail();

       if(writerByEmail.equals(userByEmail)){ //작성자와 글을 본 사람이 같으면
            model.addAttribute("isSameUser",true);
            log.info("true");
        }
        else {
            model.addAttribute("isSameUser",false);
            log.info("false");
        }

       String name = oAuth2User.getAttribute("name").toString();
       model.addAttribute("username",name);
       model.addAttribute("post",postByPostId);

       //댓글조회
       List<CommentEntity> commentList = commentService.findCommentList();
       for (int i=0; i<commentList.size(); i++){
           //댓글 작성자 id와 현재 사용자 id를 비교
           boolean isWriter = commentList.get(i).getUserId().getEmail().equals(userByEmail);
           log.info("isWriter: {}",isWriter);
           model.addAttribute("isWriter",isWriter);
       }
       model.addAttribute("commentlist",commentList);
       log.info("commentlist: {}",commentList);

       return "detail";
    }
    @GetMapping("/edit/{postId}") //페이지 수정 폼
    public String editForm(Model model,@PathVariable("postId") Long postId) throws Exception {
        PostEntity postByPostId = detailService.findPostByPostId(postId);
        model.addAttribute("post",postByPostId);

        return "editForm";

    }

//    @PostMapping("/update/{postId}") //글 수정 후 저장
//    public String update(@PathVariable("postId") Long postId, PostEntity postEntity){
//       postService.
//    }
}
