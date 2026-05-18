package com.kayan.hotel_corinthians_backend.payment.controller

import com.kayan.hotel_corinthians_backend.payment.dto.PaymentRequest
import com.kayan.hotel_corinthians_backend.payment.dto.PaymentResponse
import com.kayan.hotel_corinthians_backend.payment.service.PaymentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/payments")
class PaymentController(
    private val paymentService: PaymentService
) {

    @PostMapping
    fun createPayment(@RequestBody request: PaymentRequest): ResponseEntity<PaymentResponse> {
        return ResponseEntity.ok(paymentService.createPayment(request))
    }

    @PatchMapping("/{id}/approve")
    fun approvePayment(@PathVariable id: Long): ResponseEntity<PaymentResponse> {
        return ResponseEntity.ok(paymentService.approvePayment(id))
    }

    @PatchMapping("/{id}/refuse")
    fun refusePayment(@PathVariable id: Long): ResponseEntity<PaymentResponse> {
        return ResponseEntity.ok(paymentService.refusePayment(id))
    }

    @GetMapping
    fun listPayments(): ResponseEntity<List<PaymentResponse>> {
        return ResponseEntity.ok(paymentService.listPayments())
    }

    @GetMapping("/reservation/{reservationId}")
    fun listPaymentsByReservation(
        @PathVariable reservationId: Long
    ): ResponseEntity<List<PaymentResponse>> {
        return ResponseEntity.ok(paymentService.listPaymentsByReservation(reservationId))
    }
}