package ru.kvartalovea.catscafe.feature.catdetails.impl.data.repository

import kotlinx.coroutines.delay
import ru.kvartalovea.catscafe.feature.catdetails.impl.domain.model.CatDetail
import ru.kvartalovea.catscafe.feature.catdetails.impl.domain.repository.CatDetailsRepository

internal class CatDetailsRepositoryMock : CatDetailsRepository {

    override suspend fun getCatById(id: String): Result<CatDetail?> {
        delay(MOCK_DELAY_MS)
        return Result.success(MOCK_CATS[id])
    }

    private companion object {
        const val MOCK_DELAY_MS = 500L

        val MOCK_CATS = mapOf(
            "1" to CatDetail(
                id = "1",
                name = "Мурзик",
                imageUrl = "https://images.unsplash.com/photo-1529778873920-4da4926a72c2?w=800",
                isLookingForHome = true,
                age = "3 года",
                breed = "Британская короткошёрстная",
                temperament = "Спокойный, ласковый",
                bio = "Мурзик — настоящий джентльмен. Он любит комфорт и спокойствие. " +
                    "С удовольствием посидит у вас на коленях, помурлычет и подарит тепло. " +
                    "Отлично ладит с детьми и другими животными. Ищет любящую семью, " +
                    "где его будут ценить за мягкий характер.",
            ),
            "2" to CatDetail(
                id = "2",
                name = "Луна",
                imageUrl = "https://images.unsplash.com/photo-1514888286974-6c03e2ca1dba?w=800",
                isLookingForHome = true,
                age = "1 год",
                breed = "Сибирская",
                temperament = "Игривая, энергичная",
                bio = "Луна — непоседа и любительница приключений. Она всегда в движении " +
                    "и готова играть в любое время. Обожает высокие места и яркие игрушки. " +
                    "Быстро привязывается к хозяевам и требует внимания.",
            ),
            "3" to CatDetail(
                id = "3",
                name = "Барсик",
                imageUrl = "https://images.unsplash.com/photo-1495360010541-f48722b34f7d?w=800",
                isLookingForHome = false,
                age = "2 года",
                breed = "Мейн-кун",
                temperament = "Дружелюбный, величественный",
                bio = "Барсик — настоящий лесной кот с королевскими манерами. " +
                    "Несмотря на внушительный размер, он очень нежен с людьми. " +
                    "Любит прогулки, активные игры и с удовольствием выполняет несложные команды.",
            ),
            "4" to CatDetail(
                id = "4",
                name = "Снежинка",
                imageUrl = "https://images.unsplash.com/photo-1518715308788-3005759c61d4?w=800",
                isLookingForHome = true,
                age = "2 года",
                breed = "Персидская",
                temperament = "Нежная, независимая",
                bio = "Снежинка ценит личное пространство, но умеет быть невероятно ласковой " +
                    "с теми, кому доверяет. Любит тихие вечера и мягкие пледы. " +
                    "Идеальный компаньон для спокойного дома.",
            ),
        )
    }
}
