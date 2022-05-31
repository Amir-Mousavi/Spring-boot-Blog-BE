package com.tutorial.blog.controller

import com.tutorial.blog.model.Category
import com.tutorial.blog.service.CategoryService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/category/")
class CategoryController(val categoryService: CategoryService) {

    @PostMapping
    fun createCategory(@RequestBody category: Category, @RequestHeader("Authorization") idToken: String) = ResponseEntity(
        categoryService.createCategory(category, idToken),
        HttpStatus.CREATED
    )
}