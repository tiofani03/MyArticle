package id.tiooooo.myarticle.ui.pages.login

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import id.tiooooo.myarticle.ui.pages.home.HomeRoute
import id.tiooooo.myarticle.ui.theme.HUGE_PADDING
import id.tiooooo.myarticle.ui.theme.LARGE_PADDING
import id.tiooooo.myarticle.ui.theme.MEDIUM_PADDING
import id.tiooooo.myarticle.ui.theme.SMALL_PADDING
import id.tiooooo.myarticle.utils.SessionManager
import id.tiooooo.myarticle.utils.requestNotificationPermission

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    screenModel: LoginScreenModel,
) {
    val context = LocalContext.current
    val accessToken by screenModel.accessToken.collectAsState()
    val navigator = LocalNavigator.currentOrThrow

    LaunchedEffect(Unit) {
        screenModel.effect.collect {
            when (it) {
                is LoginEffect.NavigateToMain -> {
                    navigator.replaceAll(HomeRoute())
                    SessionManager.scheduleLogoutTimer(context)
                }

                is LoginEffect.ShowErrorMessage -> {
                    Toast.makeText(context, it.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }

        requestNotificationPermission(context, context as Activity)
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(LARGE_PADDING)
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(100.dp)
                    .padding(bottom = HUGE_PADDING)
            )

            Text(
                text = "Welcome!",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = "Login or register to Continue",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                modifier = Modifier.padding(top = SMALL_PADDING, bottom = LARGE_PADDING)
            )

            Button(
                onClick = {
                    screenModel.dispatch(LoginIntent.OnLoginOrRegisterClicked(context as Activity))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(MEDIUM_PADDING)
            ) {
                Text("Login or Register")
            }

            if (accessToken != null) {
                Spacer(modifier = Modifier.height(HUGE_PADDING))
                CircularProgressIndicator()
            }
        }
    }
}
