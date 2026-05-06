package com.kayan.hotel_corinthians_backend.model

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*

@Entity
@Table(name = "clients")
@Schema(name = "Hospede", description = "Representação de um cliente do Hotel Corinthians")
data class Client(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do cliente no banco de dados", example = "1")
    val id: Long? = null,

    @Schema(description = "Nome completo do torcedor/cliente", example = "Richard Kayan")
    val name: String = "",

    @Schema(description = "E-mail para contato e reservas", example = "richard@email.com")
    val email: String = "",

    @Schema(description = "Número de telefone ou WhatsApp", example = "11999999999")
    val phoneNumber: String = "",

    @Schema(description = "CPF do hóspede", example = "123.456.789-00")
    val taxId: String = "",

    @Schema(hidden = true) // Esconde o hash da senha no Swagger por segurança
    val passwordHash: String = ""
)