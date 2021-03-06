package com.tutorial.blog.repositories

import com.tutorial.blog.model.Category
import com.tutorial.blog.model.User
import org.springframework.data.repository.CrudRepository

interface CategoryRepository: CrudRepository<Category, Long> {
    fun findAllByUser(user: User): List<Category>

    fun findByIdAndUser(id: Long, user: User): Category?

    fun deleteByIdAndUser(id: Long, user: User)
}