package com.kayan.hotel_corinthians_backend.controller

// ATENÇÃO: Os imports agora devem incluir o "com.kayan"
import com.kayan.hotel_corinthians_backend.model.Client
import com.kayan.hotel_corinthians_backend.repository.ClientRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/guests")
class GuestController(private val clientRepository: ClientRepository) {

    @GetMapping
    fun getAll(): List<Client> = clientRepository.findAll()

    @PostMapping
    fun create(@RequestBody guest: Client): Client = clientRepository.save(guest)
}