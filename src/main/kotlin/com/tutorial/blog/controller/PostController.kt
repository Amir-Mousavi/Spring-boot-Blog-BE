package com.tutorial.blog.controller

import com.tutorial.blog.dto.PostDTO
import com.tutorial.blog.service.PostService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/post/")
class PostController(val postService: PostService) {

    @PostMapping
    fun create(@RequestBody postDTO: PostDTO, @RequestHeader("Authorization") idToken: String) = postService.createPost(postDTO, idToken)
}