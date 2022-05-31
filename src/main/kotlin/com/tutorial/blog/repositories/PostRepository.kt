package com.tutorial.blog.repositories

import com.tutorial.blog.model.Post
import org.springframework.data.repository.CrudRepository

interface PostRepository: CrudRepository<Post, Long>