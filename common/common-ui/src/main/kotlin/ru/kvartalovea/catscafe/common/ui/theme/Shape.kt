package ru.kvartalovea.catscafe.common.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

/**
 * Скругления, единообразные с макетами:
 *  - small (8dp)   — чипы, инпуты, мелкие кнопки.
 *  - medium (16dp) — карточки котиков, карточки новостей, чипы времени.
 *  - large (20dp)  — крупные кнопки, большие карточки.
 *  - extraLarge (28dp) — bottom sheets, ключевые акцентные блоки.
 */
internal val CatsCafeShapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(20.dp),
    extraLarge = RoundedCornerShape(28.dp),
)
