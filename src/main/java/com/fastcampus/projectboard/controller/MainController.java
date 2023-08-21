package com.fastcampus.projectboard.controller;

import com.fastcampus.projectboard.dto.response.ArticleCommentResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@Controller
public class MainController {

    @GetMapping("/")
    public String root() {
        return "forward:/articles";
    }

    @GetMapping("/test-rest")
    @ResponseBody
    public ArticleCommentResponse test() {
        return ArticleCommentResponse.of(
                1L,
                "ad",
                LocalDateTime.now(),
                "ad@mail.com",
                "ad",
                "ad"
        );


    }
}
