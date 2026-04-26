package ru.kvartalovea.catscafe.common.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * Палитра, заточенная под макеты Cats Cafe:
 *  - primary   — оранжевый (главный бренд-акцент);
 *  - secondary — тёмно-синий navy (контрастная кнопка, шапки);
 *  - tertiary  — зелёный (статусы успеха, бейджи).
 *
 * Dynamic color намеренно отключён, чтобы бренд не подменялся системным акцентом.
 */
private val LightColors = lightColorScheme(
    primary = OrangePrimary,
    onPrimary = OrangeOnPrimary,
    primaryContainer = OrangeContainer,
    onPrimaryContainer = OnOrangeContainer,

    secondary = NavyPrimary,
    onSecondary = OnNavy,
    secondaryContainer = NavyContainer,
    onSecondaryContainer = OnNavyContainer,

    tertiary = GreenAccent,
    onTertiary = OnGreenAccent,
    tertiaryContainer = GreenContainer,
    onTertiaryContainer = OnGreenContainer,

    error = ErrorRed,
    onError = OnErrorRed,
    errorContainer = ErrorRedContainer,
    onErrorContainer = OnErrorRedContainer,

    background = NeutralBackgroundLight,
    onBackground = NeutralOnSurfaceLight,
    surface = NeutralSurfaceLight,
    onSurface = NeutralOnSurfaceLight,
    surfaceVariant = NeutralSurfaceVariantLight,
    onSurfaceVariant = NeutralOnSurfaceVariantLight,
    surfaceTint = OrangePrimary,

    outline = NeutralOutlineLight,
    outlineVariant = NeutralOutlineVariantLight,
)

private val DarkColors = darkColorScheme(
    primary = OrangeDark,
    onPrimary = Color.Black,
    primaryContainer = OrangeContainerDark,
    onPrimaryContainer = OnOrangeContainerDark,

    secondary = NavyDark,
    onSecondary = Color.Black,
    secondaryContainer = NavyContainerDark,
    onSecondaryContainer = OnNavyContainerDark,

    tertiary = GreenAccentDark,
    onTertiary = Color.Black,
    tertiaryContainer = GreenContainerDark,
    onTertiaryContainer = OnGreenContainerDark,

    error = ErrorRedDark,
    onError = Color.Black,
    errorContainer = ErrorRedContainerDark,
    onErrorContainer = OnErrorRedContainerDark,

    background = NeutralBackgroundDark,
    onBackground = NeutralOnSurfaceDark,
    surface = NeutralSurfaceDark,
    onSurface = NeutralOnSurfaceDark,
    surfaceVariant = NeutralSurfaceVariantDark,
    onSurfaceVariant = NeutralOnSurfaceVariantDark,
    surfaceTint = OrangeDark,

    outline = NeutralOutlineDark,
    outlineVariant = NeutralOutlineVariantDark,
)

@Composable
fun CatsCafeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colorScheme,
        typography = CatsCafeTypography,
        shapes = CatsCafeShapes,
        content = content,
    )
}
