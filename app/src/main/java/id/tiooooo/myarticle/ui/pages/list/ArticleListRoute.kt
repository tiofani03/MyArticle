package id.tiooooo.myarticle.ui.pages.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import id.tiooooo.myarticle.utils.DATATYPE

class ArticleListRoute(
    val type: DATATYPE = DATATYPE.ARTICLE,
) : Screen {
    @Composable
    override fun Content() {
        ArticleListScreen(
            modifier = Modifier.fillMaxSize(),
            type = type,
            screenModel = koinScreenModel()
        )
    }
}