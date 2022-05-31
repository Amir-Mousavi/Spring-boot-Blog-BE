package com.tutorial.blog.repositories

import com.tutorial.blog.model.User
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, Long> {
    fun findByEmail(email:String): User?
}