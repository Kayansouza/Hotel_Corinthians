
// Para evitar que dois clientes reservem o mesmo quarto ao mesmo tempo
package com.kayan.hotel_corinthians_backend.repository

import com.kayan.hotel_corinthians_backend.model.Room
import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import java.util.*

interface RoomRepository : JpaRepository<Room, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT r FROM Room r WHERE r.id = :id")
    fun findByIdWithLock(id: Long): Optional<Room>
}