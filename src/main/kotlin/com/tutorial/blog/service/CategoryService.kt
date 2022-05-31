package com.tutorial.blog.service

import com.tutorial.blog.model.Category
import com.tutorial.blog.repositories.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(val userService: UserService, val categoryRepository: CategoryRepository) {

    fun createCategory(category: Category, idToken: String): Category {
        val user = userService.getOrCreateUser(idToken)

        category.user = user
        return categoryRepository.save(category)
    }

    fun findById(id: Long) = categoryRepository.findById(id)

}