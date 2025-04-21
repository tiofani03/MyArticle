package id.tiooooo.myarticle.ui.pages.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel

class HomeRoute : Screen {
    @Composable
    override fun Content() {
        HomeScreen(
            modifier = Modifier.fillMaxSize(),
            homeScreenModel = koinScreenModel()
        )
    }
}