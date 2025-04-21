package id.tiooooo.myarticle.ui.pages.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import id.tiooooo.myarticle.ui.components.common.TitleLeftAndRight
import id.tiooooo.myarticle.ui.pages.home.model.HomeItemData
import id.tiooooo.myarticle.ui.theme.IMAGE_HOME_SIZE
import id.tiooooo.myarticle.ui.theme.MEDIUM_PADDING
import id.tiooooo.myarticle.ui.theme.SMALL_PADDING
import id.tiooooo.myarticle.utils.wrapper.ResultState


@Composable
fun HomeSectionStateView(
    modifier: Modifier = Modifier,
    titleLeft: String,
    titleRight: String = "",
    state: ResultState<List<HomeItemData>>,
    onSeeMoreClicked: () -> Unit = {},
    onDetailClicked: (Int) -> Unit = {},
) {
    when (state) {
        is ResultState.Success -> {
            val articles = state.data
            HomeSection(
                modifier = modifier,
                titleLeft = titleLeft,
                titleRight = titleRight,
                onSeeMoreClicked = onSeeMoreClicked,
                items = articles,
                itemPaddingValues = PaddingValues(horizontal = SMALL_PADDING),
                onDetailClicked = onDetailClicked
            )
        }

        is ResultState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is ResultState.Error -> Unit
    }
}


@Composable
fun HomeSection(
    modifier: Modifier = Modifier,
    titleLeft: String,
    titleRight: String = "",
    items: List<HomeItemData>,
    onSeeMoreClicked: () -> Unit = {},
    itemPaddingValues: PaddingValues = PaddingValues(horizontal = 16.dp),
    onDetailClicked: (Int) -> Unit = {},
) {
    Column {
        TitleLeftAndRight(
            modifier = modifier,
            titleLeft = titleLeft,
            titleRight = titleRight,
            onSeeMoreClicked = onSeeMoreClicked
        )
        Spacer(modifier = Modifier.height(SMALL_PADDING))
        LazyRow(
            contentPadding = itemPaddingValues,
            horizontalArrangement = Arrangement.spacedBy(SMALL_PADDING)
        ) {
            items(items) { article ->
                HomeItemSection(
                    modifier = Modifier
                        .clickable {
                            onDetailClicked(article.id)
                        }
                        .height(IMAGE_HOME_SIZE),
                    image = article.imageUrl,
                    title = article.title,
                )
            }
        }
    }
}