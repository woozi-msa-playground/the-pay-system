package com.practice.msa.common

import jakarta.validation.ConstraintViolationException
import jakarta.validation.Validation
import jakarta.validation.Validator

open class SelfValidating<T>(
    private val validator: Validator = Validation.buildDefaultValidatorFactory().validator
) {
    fun validateSelf() {
        val violations = validator.validate(this as T)
        if (violations.isNotEmpty()) {
            throw ConstraintViolationException(violations)
        }
    }
}