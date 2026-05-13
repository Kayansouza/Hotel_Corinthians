package com.kayan.hotel_corinthians_backend.security.service


import org.springframework.stereotype.Service

@Service
class SmartRoomIoTService {
    fun prepareRoom(roomId: Long?) {
        println("📡 Enviando sinal IoT para ligar ar condicionado do quarto $roomId")
    }
}