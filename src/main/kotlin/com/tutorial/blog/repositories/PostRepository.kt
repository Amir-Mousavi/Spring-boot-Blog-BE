package com.tutorial.blog.repositories

import com.tutorial.blog.model.Post
import com.tutorial.blog.model.User
import org.springframework.data.repository.CrudRepository

interface PostRepository: CrudRepository<Post, Long> {
    fun findByIdAndUser(id: Long, user: User): Post?

    fun deleteByIdAndUser(id: Long, user: User)
}