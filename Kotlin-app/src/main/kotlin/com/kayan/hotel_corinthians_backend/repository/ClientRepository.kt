package com.kayan.hotel_corinthians_backend.repository

import com.kayan.hotel_corinthians_backend.model.Client // Exemplo para o ClientRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClientRepository : JpaRepository<Client, Long>