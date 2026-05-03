package ru.kvartalovea.catscafe.feature.home.impl.data.repository

import kotlinx.coroutines.delay
import ru.kvartalovea.catscafe.feature.home.impl.domain.model.BookingResult
import ru.kvartalovea.catscafe.feature.home.impl.domain.model.NearestBooking
import ru.kvartalovea.catscafe.feature.home.impl.domain.model.NewsItem
import ru.kvartalovea.catscafe.feature.home.impl.domain.model.NewsResult
import ru.kvartalovea.catscafe.feature.home.impl.domain.repository.HomeRepository

internal class HomeRepositoryMock : HomeRepository {

    override suspend fun getNews(): Result<NewsResult> {
        delay(MOCK_DELAY_MS)
        return Result.success(NewsResult(items = MOCK_NEWS, isFromCache = false))
    }

    override suspend fun getNearestBooking(): Result<BookingResult> {
        delay(MOCK_DELAY_MS)
        return Result.success(BookingResult(booking = MOCK_BOOKING, isFromCache = false))
    }

    private companion object {
        const val MOCK_DELAY_MS = 800L

        val MOCK_BOOKING = NearestBooking(
            date = "8 декабря",
            time = "15:00",
            guestsCount = 2,
            qrCodeUrl = null,
        )

        val MOCK_NEWS = listOf(
            NewsItem(
                id = "1",
                title = "Скидка 20% на все напитки!",
                description = "С понедельника по пятницу с 10:00 до 12:00 действует акция на все напитки.",
                imageUrl = "https://images.unsplash.com/photo-1509042239860-f550ce710b93?w=800",
                publishedAt = "1 декабря 2025 г.",
            ),
            NewsItem(
                id = "2",
                title = "Новые жители котокафе",
                description = "Познакомьтесь с Луной и Снежинкой — нашими новыми пушистыми друзьями!",
                imageUrl = "https://images.unsplash.com/photo-1514888286974-6c03e2ca1dba?w=800",
                publishedAt = "28 ноября 2025 г.",
            ),
            NewsItem(
                id = "3",
                title = "Открыты предзаписи на декабрь",
                description = "Успейте забронировать столик на праздничные дни — мест становится всё меньше.",
                imageUrl = null,
                publishedAt = "25 ноября 2025 г.",
            ),
        )
    }
}
