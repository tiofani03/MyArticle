package id.tiooooo.myarticle.ui.pages.list.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import id.tiooooo.myarticle.data.api.model.ArticleData
import id.tiooooo.myarticle.ui.theme.EXTRA_SMALL_PADDING
import id.tiooooo.myarticle.ui.theme.MEDIUM_PADDING
import id.tiooooo.myarticle.ui.theme.SMALL_PADDING
import id.tiooooo.myarticle.ui.theme.textMedium10


@Composable
fun ArticleListItem(
    item: ArticleData,
    onClick: (ArticleData) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(item) }
    ) {
        Box(
            modifier = Modifier,
        ) {
            AsyncImage(
                model = item.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Row(
                modifier = Modifier
                    .padding(bottom = SMALL_PADDING)
                    .align(Alignment.BottomCenter),
            ) {
                if (item.launches.isNotEmpty()) {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = MEDIUM_PADDING)
                            .background(
                                color = MaterialTheme.colorScheme.primary.copy(0.8f),
                                shape = RoundedCornerShape(SMALL_PADDING)
                            )
                            .clip(RoundedCornerShape(SMALL_PADDING))
                            .padding(vertical = EXTRA_SMALL_PADDING, horizontal = SMALL_PADDING),
                        text = item.launches.joinToString(","),
                        style = textMedium10(),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.White,
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                if (item.events.isNotEmpty()) {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = MEDIUM_PADDING)
                            .background(
                                color = MaterialTheme.colorScheme.primary.copy(0.8f),
                                shape = RoundedCornerShape(SMALL_PADDING)
                            )
                            .clip(RoundedCornerShape(SMALL_PADDING))
                            .padding(EXTRA_SMALL_PADDING),
                        text = item.events.joinToString(","),
                        style = textMedium10(),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.White,
                    )
                }
            }
        }
    }
}

