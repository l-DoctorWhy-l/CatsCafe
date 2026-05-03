package ru.kvartalovea.catscafe.core.database.impl

/**
 * Single source of truth для имени и версии локальной БД.
 */
internal object DatabaseConfig {
    const val NAME: String = "cats_cafe.db"
    const val VERSION: Int = 2
}
