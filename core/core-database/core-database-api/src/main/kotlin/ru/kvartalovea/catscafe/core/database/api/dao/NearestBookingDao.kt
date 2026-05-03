package ru.kvartalovea.catscafe.core.database.api.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.kvartalovea.catscafe.core.database.api.entity.NearestBookingEntity

@Dao
interface NearestBookingDao {

    @Query("SELECT * FROM nearest_booking WHERE id = 1")
    suspend fun get(): NearestBookingEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: NearestBookingEntity)

    @Query("DELETE FROM nearest_booking")
    suspend fun clear()
}
