package id.tiooooo.myarticle.ui.pages.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
    onProfileClick: () -> Unit = {}
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = greetingMessage,
                style = textMedium12().copy(fontWeight = FontWeight.Light)
            )
            Text(
                modifier = Modifier.padding(top = EXTRA_SMALL_PADDING),
                text = userName,
                style = textMedium14().copy(fontWeight = FontWeight.Bold)
            )
        }

        IconButton(
            modifier = Modifier
                .padding(EXTRA_SMALL_PADDING)
                .align(Alignment.CenterEnd),
            onClick = onProfileClick
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Profile"
            )
        }
    }
}
