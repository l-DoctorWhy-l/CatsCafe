package ru.kvartalovea.catscafe.feature.mybookings.impl.domain.usecase

import ru.kvartalovea.catscafe.feature.mybookings.impl.domain.model.UserBooking
import ru.kvartalovea.catscafe.feature.mybookings.impl.domain.repository.MyBookingsRepository

internal class GetMyBookingsUseCase(private val repository: MyBookingsRepository) {
    suspend operator fun invoke(): Result<List<UserBooking>> = repository.getMyBookings()
}
