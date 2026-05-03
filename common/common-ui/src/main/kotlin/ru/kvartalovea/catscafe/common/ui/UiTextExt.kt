package ru.kvartalovea.catscafe.common.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.kvartalovea.catscafe.common.utils.UiText

@Composable
fun UiText.asString(): String = when (this) {
    is UiText.DynamicString -> value
    is UiText.StringRes -> stringResource(id, *args)
}
