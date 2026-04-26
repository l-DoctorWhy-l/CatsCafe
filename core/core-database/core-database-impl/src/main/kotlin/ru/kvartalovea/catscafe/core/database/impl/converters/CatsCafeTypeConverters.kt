package ru.kvartalovea.catscafe.core.database.impl.converters

import androidx.room.TypeConverter
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import java.util.UUID

/**
 * Общий набор Room [TypeConverter]'ов для всех `Entity` приложения.
 *
 * Регистрируется на `CatsCafeDatabase` через `@TypeConverters(CatsCafeTypeConverters::class)`.
 * Все методы — чистые `pure functions`, без состояния, потому Room сам инстанцирует объект.
 *
 * Соглашения по форматам в БД:
 *  - [Instant]       — Long, эпоха в миллисекундах (UTC).
 *  - [LocalDate]     — String, ISO-8601 (`2026-04-26`).
 *  - [LocalDateTime] — String, ISO-8601 (`2026-04-26T13:40:00`).
 *  - [UUID]          — String, каноническое представление (`8-4-4-4-12`).
 */
internal class CatsCafeTypeConverters {

    // ----- Instant -----

    @TypeConverter
    fun instantToEpochMillis(value: Instant?): Long? = value?.toEpochMilliseconds()

    @TypeConverter
    fun epochMillisToInstant(value: Long?): Instant? = value?.let(Instant::fromEpochMilliseconds)

    // ----- LocalDate -----

    @TypeConverter
    fun localDateToString(value: LocalDate?): String? = value?.toString()

    @TypeConverter
    fun stringToLocalDate(value: String?): LocalDate? = value?.let(LocalDate::parse)

    // ----- LocalDateTime -----

    @TypeConverter
    fun localDateTimeToString(value: LocalDateTime?): String? = value?.toString()

    @TypeConverter
    fun stringToLocalDateTime(value: String?): LocalDateTime? = value?.let(LocalDateTime::parse)

    // ----- UUID -----

    @TypeConverter
    fun uuidToString(value: UUID?): String? = value?.toString()

    @TypeConverter
    fun stringToUuid(value: String?): UUID? = value?.let(UUID::fromString)
}
