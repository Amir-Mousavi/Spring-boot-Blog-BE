package com.tutorial.blog.filters

import com.tutorial.blog.service.FirebaseService
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthFilter(val firebaseService: FirebaseService): OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val idToken = request.getHeader("Authorization")

        val firebaseToken = firebaseService.getFirebaseToken(idToken)

        if (firebaseToken?.uid != null) {
            filterChain.doFilter(request, response)
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED)
        }
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        return request.requestURI.contains("/api/health/")
    }
}