package ru.kvartalovea.catscafe.feature.catalog.impl.data.repository

import kotlinx.coroutines.delay
import ru.kvartalovea.catscafe.feature.catalog.impl.domain.model.Cat
import ru.kvartalovea.catscafe.feature.catalog.impl.domain.repository.CatalogRepository

internal class CatalogRepositoryMock : CatalogRepository {

    override suspend fun getCats(): Result<List<Cat>> {
        delay(MOCK_DELAY_MS)
        return Result.success(MOCK_CATS)
    }

    private companion object {
        const val MOCK_DELAY_MS = 700L

        val MOCK_CATS = listOf(
            Cat(
                id = "1",
                name = "Мурзик",
                description = "Спокойный, ласковый",
                ageLabel = "3 года",
                imageUrl = "https://images.unsplash.com/photo-1529778873920-4da4926a72c2?w=400",
                isNew = false,
                isLookingForHome = true,
            ),
            Cat(
                id = "2",
                name = "Луна",
                description = "Игривая, энергичная",
                ageLabel = "1 год",
                imageUrl = "https://images.unsplash.com/photo-1514888286974-6c03e2ca1dba?w=400",
                isNew = true,
                isLookingForHome = true,
            ),
            Cat(
                id = "3",
                name = "Барсик",
                description = "Дружелюбный, величественный",
                ageLabel = "2 года",
                imageUrl = "https://images.unsplash.com/photo-1495360010541-f48722b34f7d?w=400",
                isNew = false,
                isLookingForHome = false,
            ),
            Cat(
                id = "4",
                name = "Снежинка",
                description = "Нежная, независимая",
                ageLabel = "2 года",
                imageUrl = "https://images.unsplash.com/photo-1518715308788-3005759c61d4?w=400",
                isNew = true,
                isLookingForHome = true,
            ),
        )
    }
}
