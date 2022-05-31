package com.tutorial.blog.controller

import com.tutorial.blog.dto.PostDTO
import com.tutorial.blog.service.PostService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/post/")
class PostController(val postService: PostService) {

    @PostMapping
    fun create(@RequestBody postDTO: PostDTO, @RequestHeader("Authorization") idToken: String) = ResponseEntity(
        postService.createPost(postDTO, idToken),
        HttpStatus.CREATED
    )

    @PutMapping
    fun update(@RequestBody postDTO: PostDTO, @RequestHeader("Authorization") idToken: String ) = ResponseEntity(
        postService.updatePost(postDTO, idToken),
        HttpStatus.OK
    )

    @DeleteMapping("{id}")
    fun delete(@PathVariable("id") id: Long, @RequestHeader("Authorization") idToken: String):ResponseEntity<Unit> {
        postService.delete(id, idToken)

        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}