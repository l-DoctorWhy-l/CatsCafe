package ru.kvartalovea.catscafe.core.database.impl

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.kvartalovea.catscafe.core.database.api.dao.NearestBookingDao
import ru.kvartalovea.catscafe.core.database.api.dao.NewsDao
import ru.kvartalovea.catscafe.core.database.api.entity.NearestBookingEntity
import ru.kvartalovea.catscafe.core.database.api.entity.NewsItemEntity
import ru.kvartalovea.catscafe.core.database.impl.converters.CatsCafeTypeConverters

@Database(
    entities = [
        NewsItemEntity::class,
        NearestBookingEntity::class,
    ],
    version = DatabaseConfig.VERSION,
    exportSchema = true,
)
@TypeConverters(CatsCafeTypeConverters::class)
internal abstract class CatsCafeDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
    abstract fun nearestBookingDao(): NearestBookingDao
}
