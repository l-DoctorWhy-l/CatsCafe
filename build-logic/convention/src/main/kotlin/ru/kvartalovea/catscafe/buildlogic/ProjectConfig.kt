package ru.kvartalovea.catscafe.buildlogic

import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

internal object ProjectConfig {
    const val COMPILE_SDK = 36
    const val MIN_SDK = 28
    const val TARGET_SDK = 36

    val JAVA_VERSION = JavaVersion.VERSION_11
    val JVM_TARGET = JvmTarget.JVM_11
}
