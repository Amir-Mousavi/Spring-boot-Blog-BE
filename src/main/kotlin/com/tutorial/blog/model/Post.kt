package com.tutorial.blog.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class Post(
    @Id @GeneratedValue val id: Long?,

    val title: String,

    @Column(columnDefinition = "TEXT")
    val content: String,

    @ManyToOne val category: Category,
    @ManyToOne val user: User,
)