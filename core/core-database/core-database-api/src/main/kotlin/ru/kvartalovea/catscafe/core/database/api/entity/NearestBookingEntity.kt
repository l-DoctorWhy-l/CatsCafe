package ru.kvartalovea.catscafe.core.database.api.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Хранит последнее известное ближайшее бронирование пользователя.
 * Всегда одна строка (id = 1).
 */
@Entity(tableName = "nearest_booking")
data class NearestBookingEntity(
    @PrimaryKey
    val id: Int = 1,

    @ColumnInfo(name = "date")
    val date: String,

    @ColumnInfo(name = "time")
    val time: String,

    @ColumnInfo(name = "guests_count")
    val guestsCount: Int,

    @ColumnInfo(name = "qr_code_url")
    val qrCodeUrl: String?,
)
