package com.tutorial.blog.exceptionHandler

import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MissingKotlinParameterException::class)
    fun missingKotlinParameterException(ex: MissingKotlinParameterException) = ResponseEntity.badRequest().body(
        "Missing parameter ${ex.parameter.name}"
    )

    @ExceptionHandler(IllegalArgumentException::class)
    fun illegalArgumentException(ex: IllegalArgumentException) = ResponseEntity.badRequest().body(
        ex.message
    )

}