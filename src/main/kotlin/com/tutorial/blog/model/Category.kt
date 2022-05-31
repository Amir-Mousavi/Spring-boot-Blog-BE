package com.tutorial.blog.model

import javax.persistence.*

@Entity
data class Category(
    @Id @GeneratedValue val id: Long?,
    val name: String,

    @ManyToOne var user: User?,

    @OneToMany(cascade = [CascadeType.REMOVE], fetch = FetchType.LAZY, mappedBy = "category")
    var posts: List<Post>?
)