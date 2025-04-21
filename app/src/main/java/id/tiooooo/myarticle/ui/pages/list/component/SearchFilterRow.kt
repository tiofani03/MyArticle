package id.tiooooo.myarticle.ui.pages.list.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.unit.dp
import id.tiooooo.myarticle.ui.pages.list.ArticleListIntent
import id.tiooooo.myarticle.ui.pages.list.ArticleListScreenModel
import id.tiooooo.myarticle.ui.pages.list.ArticleListState
import id.tiooooo.myarticle.ui.theme.SMALL_PADDING

@Composable
fun SearchAndFilterRow(
    modifier: Modifier = Modifier,
    state: ArticleListState,
    screenModel: ArticleListScreenModel,
    textFieldState: TextFieldState
) {
    Row(
        modifier = modifier.padding(bottom = SMALL_PADDING),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1f)
        ) {
            SimpleSearchBar(
                modifier = Modifier.fillMaxWidth(),
                expanded = state.isSearchExpand,
                onExpandedChange = {
                    screenModel.dispatch(ArticleListIntent.UpdateSearchExpand(it))
                },
                textFieldState = textFieldState,
                onSearch = { query ->
                    screenModel.dispatch(
                        ArticleListIntent.UpdateArticleFilterParams(
                            state.articleFilterParams.copy(query = query)
                        )
                    )
                },
                history = state.searchHistory,
                onAddHistory = { query ->
                    if (query.isNotBlank() && state.searchHistory.none {
                            it.keyword.contains(query, ignoreCase = true)
                        }) {
                        screenModel.dispatch(ArticleListIntent.SaveSearchQuery(query))
                    }
                },
                onRemoveHistory = { item ->
                    screenModel.dispatch(ArticleListIntent.RemoveSearchQuery(item))
                },
            )
        }

        AnimatedVisibility(!state.isSearchExpand) {
            Spacer(modifier = Modifier.width(SMALL_PADDING))
            IconButton(
                modifier = Modifier
                    .padding(end = SMALL_PADDING)
                    .wrapContentWidth()
                    .align(Alignment.CenterVertically),
                onClick = {
                    screenModel.dispatch(ArticleListIntent.UpdateBottomSheetFilter(true))
                }
            ) {
                Icon(
                    imageVector = Icons.Default.FilterList,
                    contentDescription = "Filter"
                )

                if (state.articleFilterParams.query.isNotBlank() || state.articleFilterParams.newsSite.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .padding(start = 14.dp, bottom = 14.dp)
                            .size(SMALL_PADDING)
                            .background(Red, shape = CircleShape),
                    )
                }
            }
        }
    }
}
