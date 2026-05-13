package com.kayan.hotel_corinthians_backend.model

import jakarta.persistence.*

@Entity
@Table(name = "employees")
class Employee(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String = "",
    val email: String = "", // Este campo mata o erro do UsuarioController
    val position: String = ""
)