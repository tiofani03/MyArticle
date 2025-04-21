package id.tiooooo.myarticle.ui.pages.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import id.tiooooo.myarticle.ui.components.common.CommonAppBar
import id.tiooooo.myarticle.ui.pages.list.component.ArticleListItem
import id.tiooooo.myarticle.ui.pages.list.component.NewsSiteBottomSheet
import id.tiooooo.myarticle.ui.pages.list.component.SimpleSearchBar
import id.tiooooo.myarticle.ui.pages.list.component.SortFilterBottomSheet
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
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val multiSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

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
            SimpleSearchBar(
                expanded = state.isSearchExpand,
                onExpandedChange = { screenModel.dispatch(ArticleListIntent.UpdateSearchExpand(it)) },
                textFieldState = textFieldState,
                onSearch = { query ->
                    screenModel.dispatch(
                        ArticleListIntent.UpdateArticleFilterParams(
                            state.articleFilterParams.copy(
                                query = query
                            )
                        )
                    )
                },
                history = state.searchHistory,
                onAddHistory = { query ->
                    if (query.isNotBlank() && state.searchHistory.none {
                            it.keyword.contains(
                                query,
                                ignoreCase = true
                            )
                        }) {
                        screenModel.dispatch(ArticleListIntent.SaveSearchQuery(query))
                    }
                },
                onRemoveHistory = { item ->
                    screenModel.dispatch(ArticleListIntent.RemoveSearchQuery(item))
                },
                onBack = {

                }
            )
            Row {
                Button(
                    onClick = {
                        screenModel.dispatch(
                            ArticleListIntent.UpdateBottomSheetFilter(
                                true
                            )
                        )
                    }
                ) {
                    Text(text = "Filter urutan")
                }

                Button(
                    onClick = {
                        screenModel.dispatch(ArticleListIntent.UpdateBottomSheetNewsSite(true))
                    }
                ) {
                    Text(text = "Filter Tanggal")
                }
            }


            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(articlesState.itemCount) { index ->
                    val item = articlesState[index]
                    item?.let {
                        ArticleListItem(
                            item = it,
                            onClick = {

                            }
                        )
                    }
                }

                when (articlesState.loadState.append) {
                    is LoadState.Loading -> item {
                        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                    }

                    is LoadState.Error -> item {
                        Text(
                            text = "Terjadi kesalahan saat memuat data.",
                            color = Color.Red,
                            modifier = Modifier.padding(16.dp)
                        )
                    }

                    else -> Unit
                }

                when (articlesState.loadState.prepend) {
                    is LoadState.Loading -> item {
                        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                    }

                    else -> Unit
                }
            }
        }
    }

    if (state.isBottomSheetFilterOpen) {
        SortFilterBottomSheet(
            sheetState = sheetState,
            currentSort = state.articleFilterParams.sortBy,
            onDismissRequest = {
                screenModel.dispatch(
                    ArticleListIntent.UpdateBottomSheetFilter(
                        false
                    )
                )
            },
            sortOptions = state.filterSortBy,
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

    if (state.isBottomSheetNewsSite) {
        NewsSiteBottomSheet(
            sheetState = multiSheetState,
            currentSelections = state.articleFilterParams.newsSite.split(","),
            onDismissRequest = {
                screenModel.dispatch(
                    ArticleListIntent.UpdateBottomSheetNewsSite(false)
                )
            },
            filterOptions = state.filterNewsSite,
            onApplyFilter = { newFilters ->
                screenModel.dispatch(
                    ArticleListIntent.UpdateArticleFilterParams(
                        state.articleFilterParams.copy(
                            newsSite = newFilters.joinToString(",")
                        )
                    )
                )
            }
        )
    }
}