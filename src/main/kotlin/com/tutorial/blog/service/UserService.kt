package com.tutorial.blog.service

import com.tutorial.blog.model.User
import com.tutorial.blog.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository, val firebaseService: FirebaseService) {
    fun getUserByEmail(email:String) = userRepository.findByEmail(email)

    fun getOrCreateUser(idToken: String): User {

        val firebaseToken = firebaseService.getFirebaseToken(idToken)
        val email = firebaseToken!!.email

        val user = getUserByEmail(email)

        if (user != null) {
            return user
        }

        val newUser = User(id = null, email = email)

        return userRepository.save(newUser)
    }
}