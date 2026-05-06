package com.kayan.hotel_corinthians_backend.controller

import com.kayan.hotel_corinthians_backend.model.Client
import com.kayan.hotel_corinthians_backend.model.Employee
import org.springframework.web.bind.annotation.RestController

@RestController
open class UserController {

    fun isEmailAvailable(email: String, list: List<Any>): Boolean {
        return list.none { item ->
            when (item) {
                is Client -> item.email.equals(email, ignoreCase = true)
                is Employee -> item.email.equals(email, ignoreCase = true) // Referência corrigida
                else -> false
            }
        }
    }

    open fun processRegistration(email: String, year: Int?, list: List<Any>): Boolean {
        // Lógica básica de validação (Upgrade Enterprise)
        return email.contains("@") && year != null
    }
}