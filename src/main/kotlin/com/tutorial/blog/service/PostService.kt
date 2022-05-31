package com.tutorial.blog.service

import com.tutorial.blog.dto.PostDTO
import com.tutorial.blog.model.Post
import com.tutorial.blog.repositories.PostRepository
import org.springframework.stereotype.Service

@Service
class PostService(val categoryService: CategoryService, val userService: UserService, val postRepository: PostRepository) {

    fun createPost(postDTO: PostDTO, idToken: String): Post {
        val user = userService.getOrCreateUser(idToken)
        val category = categoryService.findById(postDTO.categoryId)

        if (!category.isPresent) {
            throw java.lang.IllegalArgumentException("Category ID is not valid")
        }

        val newPost = Post(id = null, title = postDTO.title, content = postDTO.content, category = category.get(), user = user);


        return postRepository.save(newPost)
    }
}