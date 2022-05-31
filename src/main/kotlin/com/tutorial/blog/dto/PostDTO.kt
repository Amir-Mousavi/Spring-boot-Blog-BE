package com.tutorial.blog.dto

data class PostDTO(
    val id: Long?,
    val title: String,
    val content: String,

    val categoryId: Long,
)