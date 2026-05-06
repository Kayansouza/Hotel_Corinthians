package com.kayan.hotel_corinthians_backend.controller


import com.kayan.hotel_corinthians_backend.model.Employee
import com.kayan.hotel_corinthians_backend.state.HotelState
import org.springframework.web.bind.annotation.RestController

@RestController
class EmployeeController : UserController() { // Herda do seu novo UserController

    fun processEmployeeRegistration(email: String, birthYear: Int?, code: String): Boolean {
        // Agora o super.processRegistration será encontrado
        val isBasicValid = super.processRegistration(email, birthYear, HotelState.employeeList)
        return isBasicValid && code == "BANDO2026"
    }
}