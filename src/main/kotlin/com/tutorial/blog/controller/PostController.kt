package com.tutorial.blog.controller

import com.tutorial.blog.dto.PostDTO
import com.tutorial.blog.service.PostService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/post/")
class PostController(val postService: PostService) {

    @GetMapping
    fun findAll(@RequestHeader("Authorization") idToken: String) = ResponseEntity(
        postService.findAll(idToken),
        HttpStatus.OK
    )

    @GetMapping("{id}")
    fun findById(@RequestHeader("Authorization") idToken: String, @PathVariable("id") id: Long): ResponseEntity<Any> {
        val post = postService.findById(idToken, id)

        return if (post != null) {
            ResponseEntity(post, HttpStatus.OK)
        } else {
            ResponseEntity("Post id: $id does not exist", HttpStatus.BAD_REQUEST)
        }
    }

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