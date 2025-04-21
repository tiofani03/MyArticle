package id.tiooooo.myarticle.ui.pages.detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import id.tiooooo.myarticle.utils.DATATYPE

data class DetailArticleRoute(
    val id: Int,
    val type: DATATYPE = DATATYPE.ARTICLE
) : Screen {

    @Composable
    override fun Content() {
        DetailScreen(
            modifier = Modifier.fillMaxSize(),
            id = id,
            type = type,
            screenModel = koinScreenModel()
        )
    }
}