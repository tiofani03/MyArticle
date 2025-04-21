package id.tiooooo.myarticle.ui.components.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import id.tiooooo.myarticle.ui.theme.textMedium14
import id.tiooooo.myarticle.ui.theme.textMedium16

@Composable
fun TitleLeftAndRight(
    modifier: Modifier = Modifier,
    titleLeft: String,
    titleRight: String = "",
    onSeeMoreClicked: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = titleLeft,
            style = textMedium16().copy(
                fontWeight = FontWeight.SemiBold
            )
        )
        Spacer(
            modifier = Modifier.weight(1f)
        )
        if (titleRight.isNotEmpty()) {
            Text(
                modifier = Modifier.clickable {
                    onSeeMoreClicked.invoke()
                },
                text = titleRight,
                style = textMedium16().copy(
                    fontWeight = FontWeight.Light
                ),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
