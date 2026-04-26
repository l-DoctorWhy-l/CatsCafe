package ru.kvartalovea.catscafe.core.database.impl

import org.koin.dsl.module

/**
 * Koin-модуль `core-database-impl`.
 *
 * Сейчас **пустой**, потому что в проекте пока нет ни одной `@Entity`,
 * а Room не разрешает `@Database(entities = [])`. Как только появится первая
 * `Entity` из любой `feature-X-impl`-data, выполнить шаги:
 *
 * 1. Создать класс `CatsCafeDatabase` рядом с этим файлом:
 *
 * ```
 * @Database(
 *     entities = [
 *         CatEntity::class,
 *         // другие Entity — по мере роста
 *     ],
 *     version = DatabaseConfig.VERSION,
 *     exportSchema = true,
 * )
 * @TypeConverters(CatsCafeTypeConverters::class)
 * internal abstract class CatsCafeDatabase : RoomDatabase() {
 *     abstract fun catDao(): CatDao
 * }
 * ```
 *
 * 2. Развернуть этот модуль до:
 *
 * ```
 * val coreDatabaseModule = module {
 *     single<CatsCafeDatabase> {
 *         Room.databaseBuilder(
 *             context = androidContext(),
 *             klass = CatsCafeDatabase::class.java,
 *             name = DatabaseConfig.NAME,
 *         )
 *             // .addMigrations(MIGRATION_1_2, ...)
 *             .fallbackToDestructiveMigrationOnDowngrade()
 *             .build()
 *     }
 *
 *     // DAO-биндинги — по одному `single` на каждое DAO:
 *     single { get<CatsCafeDatabase>().catDao() }
 * }
 * ```
 *
 * 3. Подключить [coreDatabaseModule] в `CatsCafeApplication.startKoin { modules(...) }`.
 *
 * Соглашение: `Entity` и `Dao` живут в `core-database-api` (чтобы фичи могли
 * импортировать тип DAO без Room-генерации у себя). Реализации DAO собираются
 * Room-плагином здесь, в `core-database-impl`, при компиляции `CatsCafeDatabase`.
 */
val coreDatabaseModule = module {
    // intentionally empty — см. KDoc выше
}
