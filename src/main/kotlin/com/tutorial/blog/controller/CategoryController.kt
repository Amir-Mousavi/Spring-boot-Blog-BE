package com.tutorial.blog.controller

import com.tutorial.blog.model.Category
import com.tutorial.blog.service.CategoryService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/category/")
class CategoryController(val categoryService: CategoryService) {

    @GetMapping
    fun findAll(@RequestHeader("Authorization") idToken: String) = ResponseEntity(
        categoryService.getAll(idToken),
        HttpStatus.OK
    )

    @PostMapping
    fun createCategory(@RequestBody category: Category, @RequestHeader("Authorization") idToken: String) = ResponseEntity(
        categoryService.createCategory(category, idToken),
        HttpStatus.CREATED
    )

    @PutMapping
    fun updateCategory(@RequestBody category: Category, @RequestHeader("Authorization") idToken: String) = ResponseEntity(
        categoryService.update(category, idToken),
        HttpStatus.OK
    )

    @DeleteMapping("{id}")
    fun delete(@PathVariable("id") id: Long, @RequestHeader("Authorization") idToken: String) : ResponseEntity<Unit> {
        categoryService.delete(id, idToken)

        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}