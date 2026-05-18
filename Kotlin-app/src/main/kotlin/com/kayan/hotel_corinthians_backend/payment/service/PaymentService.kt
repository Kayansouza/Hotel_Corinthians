package com.kayan.hotel_corinthians_backend.payment.service

import com.kayan.hotel_corinthians_backend.payment.dto.PaymentRequest
import com.kayan.hotel_corinthians_backend.payment.dto.PaymentResponse
import com.kayan.hotel_corinthians_backend.payment.model.Payment
import com.kayan.hotel_corinthians_backend.payment.model.PaymentStatus
import com.kayan.hotel_corinthians_backend.payment.repository.PaymentRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class PaymentService(
    private val paymentRepository: PaymentRepository
) {

    fun createPayment(request: PaymentRequest): PaymentResponse {
        val payment = Payment(
            reservationId = request.reservationId,
            amount = request.amount,
            paymentMethod = request.paymentMethod,
            status = PaymentStatus.PENDING
        )

        return toResponse(paymentRepository.save(payment))
    }

    fun approvePayment(id: Long): PaymentResponse {
        val payment = paymentRepository.findById(id)
            .orElseThrow { RuntimeException("Pagamento não encontrado") }

        payment.status = PaymentStatus.APPROVED
        payment.paidAt = LocalDateTime.now()

        return toResponse(paymentRepository.save(payment))
    }

    fun refusePayment(id: Long): PaymentResponse {
        val payment = paymentRepository.findById(id)
            .orElseThrow { RuntimeException("Pagamento não encontrado") }

        payment.status = PaymentStatus.REFUSED

        return toResponse(paymentRepository.save(payment))
    }

    fun listPayments(): List<PaymentResponse> {
        return paymentRepository.findAll().map {
            toResponse(it)
        }
    }

    fun listPaymentsByReservation(reservationId: Long): List<PaymentResponse> {
        return paymentRepository.findByReservationId(reservationId).map {
            toResponse(it)
        }
    }

    private fun toResponse(payment: Payment): PaymentResponse {
        return PaymentResponse(
            id = payment.id,
            reservationId = payment.reservationId,
            amount = payment.amount,
            paymentMethod = payment.paymentMethod,
            status = payment.status.name,
            createdAt = payment.createdAt,
            paidAt = payment.paidAt
        )
    }
}