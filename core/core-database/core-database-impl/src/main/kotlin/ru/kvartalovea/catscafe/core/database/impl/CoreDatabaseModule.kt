package ru.kvartalovea.catscafe.core.database.impl

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val coreDatabaseModule = module {
    single<CatsCafeDatabase> {
        Room.databaseBuilder(
            context = androidContext(),
            klass = CatsCafeDatabase::class.java,
            name = DatabaseConfig.NAME,
        )
        .fallbackToDestructiveMigration()
        .build()
    }

    single { get<CatsCafeDatabase>().newsDao() }
    single { get<CatsCafeDatabase>().nearestBookingDao() }
}
