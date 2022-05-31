package com.tutorial.blog.service

import com.tutorial.blog.model.Category
import com.tutorial.blog.repositories.CategoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CategoryService(val userService: UserService, val categoryRepository: CategoryRepository) {

    fun createCategory(category: Category, idToken: String): Category {
        val user = userService.getOrCreateUser(idToken)

        category.user = user
        return categoryRepository.save(category)
    }

    fun findById(id: Long) = categoryRepository.findById(id)

    fun update(category: Category, idToken: String): Category {
        val user = userService.getOrCreateUser(idToken)

        val categoryDB = categoryRepository.findByIdAndUser(category.id!!, user)
            ?: throw java.lang.IllegalArgumentException("Category does not exist")

        val newCategory = categoryDB.copy(
            name = category.name
        )

        return categoryRepository.save(newCategory)
    }

    @Transactional
    fun delete(id: Long, idToken: String) {
        val user = userService.getOrCreateUser(idToken)

        categoryRepository.deleteByIdAndUser(id, user)
    }

}