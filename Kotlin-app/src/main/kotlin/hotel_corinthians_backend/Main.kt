package com.kayan.hotel_corinthians_backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

@SpringBootApplication
@EnableCaching
class HotelCorinthiansBackendApplication {
    // SEM ISSO O CURRENCY SERVICE FAZ O SISTEMA CAIR NA HORA
    @Bean
    fun restTemplate(): RestTemplate = RestTemplate()
}

fun main(args: Array<String>) {
    runApplication<HotelCorinthiansBackendApplication>(*args)
}