package ru.kvartalovea.catscafe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import ru.kvartalovea.catscafe.common.ui.theme.CatsCafeTheme
import ru.kvartalovea.catscafe.core.navigation.impl.AppNavHost
import ru.kvartalovea.catscafe.feature.splash.api.SplashRoute

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CatsCafeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavHost(
                        startDestination = SplashRoute,
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}
