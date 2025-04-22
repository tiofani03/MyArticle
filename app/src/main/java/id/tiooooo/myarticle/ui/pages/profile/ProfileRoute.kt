package id.tiooooo.myarticle.ui.pages.profile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel

class ProfileRoute : Screen {
    @Composable
    override fun Content() {
        ProfileScreen(
            modifier = Modifier.fillMaxSize(),
            screenModel = koinScreenModel()
        )
    }
}