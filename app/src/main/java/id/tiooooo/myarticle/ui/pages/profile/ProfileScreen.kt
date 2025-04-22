package id.tiooooo.myarticle.ui.pages.profile

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import id.tiooooo.myarticle.ui.components.common.BaseScaffold
import id.tiooooo.myarticle.ui.components.common.CommonAppBar
import id.tiooooo.myarticle.ui.pages.login.LoginRoute
import id.tiooooo.myarticle.ui.pages.profile.component.ProfileHeader
import id.tiooooo.myarticle.ui.theme.MEDIUM_PADDING
import id.tiooooo.myarticle.ui.theme.SMALL_PADDING
import id.tiooooo.myarticle.utils.showSimpleNotification

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    screenModel: ProfileScreenModel,
) {
    val context = LocalContext.current
    val navigator = LocalNavigator.currentOrThrow
    val state by screenModel.state.collectAsState()
    LaunchedEffect(Unit) {
        screenModel.effect.collect { effect ->
            when (effect) {
                is ProfileEffect.NavigateToLogin -> {
                    showSimpleNotification(context)
                    navigator.replaceAll(LoginRoute())
                }
            }
        }
    }

    BaseScaffold(
        topBar = {
            CommonAppBar(
                title = "Profile",
            ) { navigator.pop() }
        }
    ) { paddingValues ->
        Box(
            modifier = modifier.padding(paddingValues),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
            ) {
                ProfileHeader(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = MEDIUM_PADDING),
                    name = state.name,
                    email = state.email,
                )
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MEDIUM_PADDING, vertical = SMALL_PADDING)
                    .align(Alignment.BottomCenter),
                onClick = { screenModel.dispatch(ProfileIntent.ExecuteLogout(context as Activity)) },
            ) {
                Text("Logout")
            }
        }
    }
}