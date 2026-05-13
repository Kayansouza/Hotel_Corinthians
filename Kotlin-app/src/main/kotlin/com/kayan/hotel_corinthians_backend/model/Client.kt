package com.kayan.hotel_corinthians_backend.model

import jakarta.persistence.*

@Entity
@Table(name = "clients")
data class Client(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val name: String = "",

    val email: String = "",

    val phoneNumber: String = "",

    val taxId: String = "",

    val passwordHash: String = ""
)