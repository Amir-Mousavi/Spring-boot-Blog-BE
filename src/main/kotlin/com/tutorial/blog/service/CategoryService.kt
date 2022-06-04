package com.tutorial.blog.service

import com.tutorial.blog.dto.CategoryDTO
import com.tutorial.blog.model.Category
import com.tutorial.blog.repositories.CategoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CategoryService(val userService: UserService, val categoryRepository: CategoryRepository) {

    fun findAll(idToken: String): List<CategoryDTO> {
        return categoryRepository
            .findAllByUser(userService.getOrCreateUser(idToken))
            .map { CategoryDTO(id = it.id!!, name = it.name, numberOfPosts = it.posts?.size ?: 0) }
    }

    fun findById(idToken: String, id: Long): CategoryDTO? {
        val category = categoryRepository.findByIdAndUser(id, userService.getOrCreateUser(idToken))

        return if (category == null) {
            null
        } else {
            CategoryDTO(id = category.id!!, name = category.name, numberOfPosts = category.posts?.size ?: 0)
        }
    }

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