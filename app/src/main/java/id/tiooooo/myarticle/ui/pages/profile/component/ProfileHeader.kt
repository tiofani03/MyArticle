package id.tiooooo.myarticle.ui.pages.profile.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import id.tiooooo.myarticle.R
import id.tiooooo.myarticle.ui.theme.HUGE_PADDING
import id.tiooooo.myarticle.ui.theme.MEDIUM_PADDING
import id.tiooooo.myarticle.ui.theme.SMALL_PADDING
import id.tiooooo.myarticle.ui.theme.textMedium14
import id.tiooooo.myarticle.ui.theme.textMedium16

@Composable
fun ProfileHeader(
    modifier: Modifier = Modifier,
    name: String,
    email: String,
) {
    OutlinedCard(
        modifier = modifier,
        shape = RoundedCornerShape(SMALL_PADDING),
        border = BorderStroke(0.dp, MaterialTheme.colorScheme.surface),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(MEDIUM_PADDING))
                .padding(MEDIUM_PADDING),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(50.dp)
                    .padding(bottom = HUGE_PADDING)
            )
            Spacer(modifier = Modifier.width(MEDIUM_PADDING))

            Column(modifier = Modifier.weight(1f)) {
                if (name != email) {
                    Text(
                        text = name,
                        style = textMedium16().copy(
                            fontWeight = FontWeight.Bold
                        ),
                    )
                }
                Text(
                    text = email,
                    style = textMedium14().copy(
                        fontWeight = FontWeight.Light,
                    )
                )
            }
        }
    }
}