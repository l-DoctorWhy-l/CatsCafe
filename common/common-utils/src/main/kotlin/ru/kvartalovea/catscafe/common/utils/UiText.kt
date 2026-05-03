package ru.kvartalovea.catscafe.common.utils


sealed class UiText {
    data class DynamicString(val value: String) : UiText()
    class StringRes(val id: Int, vararg val args: Any) : UiText()
}
