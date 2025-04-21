package id.tiooooo.myarticle.ui.pages.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import id.tiooooo.myarticle.ui.components.common.BaseScaffold
import id.tiooooo.myarticle.ui.pages.detail.DetailArticleRoute
import id.tiooooo.myarticle.ui.pages.home.component.HomeSectionStateView
import id.tiooooo.myarticle.ui.pages.home.component.HomeTextGreeting
import id.tiooooo.myarticle.ui.theme.MEDIUM_PADDING
import id.tiooooo.myarticle.utils.DATATYPE

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeScreenModel: HomeScreenModel,
) {
    val articlesState by homeScreenModel.articlesState.collectAsState()
    val blogsState by homeScreenModel.blogsState.collectAsState()
    val reportsState by homeScreenModel.reportsState.collectAsState()
    val navigator = LocalNavigator.currentOrThrow

    BaseScaffold(
        modifier = modifier
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(MEDIUM_PADDING)
        ) {
            item {
                HomeTextGreeting(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = MEDIUM_PADDING)
                        .padding(horizontal = MEDIUM_PADDING),
                    greetingMessage = homeScreenModel.getGreetingMessage(),
                    userName = "Tio"
                )
            }
            item {
                HomeSectionStateView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = MEDIUM_PADDING),
                    titleLeft = "Article",
                    titleRight = "See more",
                    state = articlesState,
                    onDetailClicked = { id ->
                        navigator.push(
                            DetailArticleRoute(
                                id = id,
                                type = DATATYPE.ARTICLE,
                            )
                        )
                    }
                )
            }

            item {
                HomeSectionStateView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = MEDIUM_PADDING),
                    titleLeft = "Blog",
                    titleRight = "See more",
                    state = blogsState,
                    onDetailClicked = { id ->
                        navigator.push(
                            DetailArticleRoute(
                                id = id,
                                type = DATATYPE.BLOG,
                            )
                        )
                    }
                )
            }

            item {
                HomeSectionStateView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = MEDIUM_PADDING),
                    titleLeft = "Report",
                    titleRight = "See more",
                    state = reportsState,
                    onDetailClicked = { id ->
                        navigator.push(
                            DetailArticleRoute(
                                id = id,
                                type = DATATYPE.REPORT,
                            )
                        )
                    }
                )
            }
        }
    }
}