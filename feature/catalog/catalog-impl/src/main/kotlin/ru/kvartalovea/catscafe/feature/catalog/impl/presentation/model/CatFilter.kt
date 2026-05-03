package ru.kvartalovea.catscafe.feature.catalog.impl.presentation.model

import androidx.annotation.StringRes
import ru.kvartalovea.catscafe.feature.catalog.impl.R

internal enum class CatFilter(@StringRes val labelRes: Int) {
    All(R.string.cat_filter_all),
    LookingForHome(R.string.cat_filter_looking_for_home),
    New(R.string.cat_filter_new),
}
