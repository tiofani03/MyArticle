package id.tiooooo.myarticle.ui.pages.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import id.tiooooo.myarticle.ui.theme.EXTRA_SMALL_PADDING
import id.tiooooo.myarticle.ui.theme.textMedium12
import id.tiooooo.myarticle.ui.theme.textMedium14

@Composable
fun HomeTextGreeting(
    modifier: Modifier = Modifier,
    greetingMessage: String,
    userName: String,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier,
            text = greetingMessage,
            style = textMedium12().copy(
                fontWeight = FontWeight.Light
            )
        )
        Text(
            modifier = Modifier.padding(top = EXTRA_SMALL_PADDING),
            text = userName,
            style = textMedium14().copy(
                fontWeight = FontWeight.Bold,
            )
        )
    }
}