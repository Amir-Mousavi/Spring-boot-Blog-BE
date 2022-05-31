package com.tutorial.blog.service

import com.tutorial.blog.model.Category
import com.tutorial.blog.repositories.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(val firebaseService: FirebaseService, val userService: UserService, val categoryRepository: CategoryRepository) {

    fun createCategory(category: Category, idToken: String): Category {
        val firebaseToken = firebaseService.getFirebaseToken(idToken)
        val user = userService.getOrCreateUser(firebaseToken!!.email)

        category.user = user
        return categoryRepository.save(category)
    }

}