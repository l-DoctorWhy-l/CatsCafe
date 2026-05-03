package ru.kvartalovea.catscafe.core.database.api.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.kvartalovea.catscafe.core.database.api.entity.NewsItemEntity

@Dao
interface NewsDao {

    @Query("SELECT * FROM news_items")
    suspend fun getAll(): List<NewsItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<NewsItemEntity>)

    @Query("DELETE FROM news_items")
    suspend fun clearAll()
}
