package id.tiooooo.myarticle.ui.pages.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil.compose.AsyncImage
import id.tiooooo.myarticle.ui.components.common.CommonAppBar
import id.tiooooo.myarticle.ui.theme.EXTRA_SMALL_PADDING
import id.tiooooo.myarticle.ui.theme.MEDIUM_PADDING
import id.tiooooo.myarticle.utils.DATATYPE
import id.tiooooo.myarticle.utils.wrapper.ResultState

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    id: Int,
    type: DATATYPE = DATATYPE.ARTICLE,
    screenModel: DetailArticleScreenModel,
) {
    val navigator = LocalNavigator.currentOrThrow
    val articlesState by screenModel.detailState.collectAsState()

    LaunchedEffect(id) {
        screenModel.fetchDetail(id, type)
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            CommonAppBar(
                title = "Detail Article",
                subtitle = id.toString(),
            ) { navigator.pop() }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(MEDIUM_PADDING)
                .verticalScroll(rememberScrollState()),
        ) {
            when (articlesState) {
                is ResultState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .padding(MEDIUM_PADDING)
                            .align(Alignment.CenterHorizontally)
                    )
                }

                is ResultState.Error -> {
                    val errorMessage = (articlesState as ResultState.Error).throwable
                    Text(
                        text = errorMessage.orEmpty(),
                        color = Color.Red,
                        modifier = Modifier.padding(MEDIUM_PADDING)
                    )
                }

                is ResultState.Success -> {
                    val article = (articlesState as ResultState.Success).data

                    Column(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        AsyncImage(
                            model = article.imageUrl,
                            contentDescription = null,
                            modifier = Modifier
                                .height(300.dp)
                                .padding(MEDIUM_PADDING),
                            contentScale = ContentScale.Fit,
                        )

                        Text(
                            text = article.title,
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = MEDIUM_PADDING)
                        )

                        Text(
                            text = article.publishedAt,
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = EXTRA_SMALL_PADDING)
                        )


                        Text(
                            text = article.summary,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = MEDIUM_PADDING)
                        )
                    }
                }
            }
        }
    }
}