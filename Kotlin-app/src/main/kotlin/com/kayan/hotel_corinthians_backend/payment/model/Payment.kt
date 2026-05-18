package com.kayan.hotel_corinthians_backend.payment.model

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "payments")
data class Payment(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "reservation_id", nullable = false)
    val reservationId: Long,

    @Column(nullable = false)
    val amount: BigDecimal,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var status: PaymentStatus = PaymentStatus.PENDING,

    @Column(name = "payment_method", nullable = false)
    val paymentMethod: String,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "paid_at")
    var paidAt: LocalDateTime? = null
)