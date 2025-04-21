package id.tiooooo.myarticle.ui.pages.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import id.tiooooo.myarticle.ui.theme.IMAGE_HOME_SIZE
import id.tiooooo.myarticle.ui.theme.SMALL_PADDING

@Composable
fun HomeItemSection(
    modifier: Modifier = Modifier,
    image: String,
    title: String,
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(SMALL_PADDING))
            .background(MaterialTheme.colorScheme.surface)
    ) {
        AsyncImage(
            model = image,
            contentDescription = title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(IMAGE_HOME_SIZE)
                .clip(RoundedCornerShape(SMALL_PADDING))
        )
    }
}