package com.tutorial.blog.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class Category(
    @Id @GeneratedValue val id: Long,
    val name: String,

    @ManyToOne val user: User
)