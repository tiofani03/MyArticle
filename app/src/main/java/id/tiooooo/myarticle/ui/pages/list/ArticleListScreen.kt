package id.tiooooo.myarticle.ui.pages.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import id.tiooooo.myarticle.ui.components.common.CommonAppBar
import id.tiooooo.myarticle.ui.pages.detail.DetailArticleRoute
import id.tiooooo.myarticle.ui.pages.list.component.ArticleListItem
import id.tiooooo.myarticle.ui.pages.list.component.FilterBottomSheet
import id.tiooooo.myarticle.ui.pages.list.component.SearchAndFilterRow
import id.tiooooo.myarticle.ui.theme.MEDIUM_PADDING
import id.tiooooo.myarticle.ui.theme.SMALL_PADDING
import id.tiooooo.myarticle.utils.DATATYPE
import id.tiooooo.myarticle.utils.toStringType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleListScreen(
    modifier: Modifier = Modifier,
    type: DATATYPE,
    screenModel: ArticleListScreenModel,
) {
    val articlesState = screenModel.articlesFlow.collectAsLazyPagingItems()
    val state by screenModel.state.collectAsState()
    val navigator = LocalNavigator.currentOrThrow

    val textFieldState = rememberTextFieldState()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)

    LaunchedEffect(type) {
        screenModel.dispatch(
            ArticleListIntent.UpdateArticleFilterParams(
                ArticleFilterParams(
                    types = type,
                )
            )
        )
    }

    Scaffold(
        modifier = modifier,
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            AnimatedVisibility(!state.isSearchExpand) {
                CommonAppBar(title = type.toStringType()) { navigator.pop() }
            }
            SearchAndFilterRow(
                modifier = Modifier.fillMaxWidth(),
                state = state,
                screenModel = screenModel,
                textFieldState = textFieldState
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(SMALL_PADDING),
                contentPadding = PaddingValues(MEDIUM_PADDING)
            ) {
                items(articlesState.itemCount) { index ->
                    val item = articlesState[index]
                    item?.let {
                        ArticleListItem(
                            item = it,
                            onClick = { item ->
                                navigator.push(
                                    DetailArticleRoute(item.id)
                                )
                            }
                        )
                    }
                }

                when (articlesState.loadState.append) {
                    is LoadState.Loading -> item {
                        CircularProgressIndicator(modifier = Modifier
                            .wrapContentHeight()
                            .wrapContentWidth()
                            .align(Alignment.CenterHorizontally)
                            .padding(MEDIUM_PADDING))
                    }

                    is LoadState.Error -> item {
                        Text(
                            text = "Something error",
                            color = Color.Red,
                            modifier = Modifier.padding(MEDIUM_PADDING)
                        )
                    }

                    else -> Unit
                }

                when (articlesState.loadState.refresh) {
                    is LoadState.Loading -> item {
                        CircularProgressIndicator(modifier = Modifier
                            .wrapContentHeight()
                            .wrapContentWidth()
                            .align(Alignment.CenterHorizontally)
                            .padding(MEDIUM_PADDING))
                    }

                    is LoadState.Error -> item {
                        Text(
                            text = "Something went wrong while loading data",
                            color = Color.Red,
                            modifier = Modifier.padding(MEDIUM_PADDING)
                        )
                    }

                    else -> Unit
                }
            }
        }
    }

    if (state.isBottomSheetFilterOpen) {
        FilterBottomSheet(
            sheetState = sheetState,
            currentSort = state.articleFilterParams.sortBy,
            currentNewsSite = state.articleFilterParams.newsSite.split(","),
            onDismissRequest = {
                screenModel.dispatch(
                    ArticleListIntent.UpdateBottomSheetFilter(
                        false
                    )
                )
            },
            sortOptions = state.filterSortBy,
            newsSiteOptions = state.filterNewsSite,
            onNewsSiteSelected = { newFilters ->
                screenModel.dispatch(
                    ArticleListIntent.UpdateArticleFilterParams(
                        state.articleFilterParams.copy(
                            newsSite = newFilters.joinToString(",")
                        )
                    )
                )
            },
            onSortSelected = { selected ->
                screenModel.dispatch(
                    ArticleListIntent.UpdateArticleFilterParams(
                        state.articleFilterParams.copy(
                            sortBy = selected
                        )
                    )
                )
            }
        )
    }
}