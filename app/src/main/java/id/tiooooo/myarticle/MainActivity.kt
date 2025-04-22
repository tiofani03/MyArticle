package id.tiooooo.myarticle

import android.annotation.SuppressLint
import android.content.Context
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.NavigatorDisposeBehavior
import cafe.adriel.voyager.transitions.SlideTransition
import id.tiooooo.myarticle.ui.pages.login.LoginRoute
import id.tiooooo.myarticle.ui.pages.profile.ProfileEffect
import id.tiooooo.myarticle.ui.pages.profile.ProfileIntent
import id.tiooooo.myarticle.ui.pages.profile.ProfileScreenModel
import id.tiooooo.myarticle.ui.pages.splash.SplashRoute
import id.tiooooo.myarticle.ui.theme.MyArticleTheme
import id.tiooooo.myarticle.utils.AppConstants.BROADCAST_LOGOUT_TAG
import id.tiooooo.myarticle.utils.LogoutReceiver
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private lateinit var logoutReceiver: LogoutReceiver
    private val profileScreenModel: ProfileScreenModel by inject()

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        logoutReceiver = LogoutReceiver {
            navigateToLogin()
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val intentFilter = IntentFilter(BROADCAST_LOGOUT_TAG)
            registerReceiver(logoutReceiver, intentFilter, Context.RECEIVER_NOT_EXPORTED)
        } else {
            val intentFilter = IntentFilter(BROADCAST_LOGOUT_TAG)
            registerReceiver(logoutReceiver, intentFilter)
        }

        setContent {
            MyArticleTheme {
                Navigator(
                    screen = SplashRoute(),
                    disposeBehavior = NavigatorDisposeBehavior(),
                    onBackPressed = { true },
                ) { navigator ->
                    SlideTransition(navigator = navigator)
                    LaunchedEffect(Unit) {
                        profileScreenModel.effect.collect { effect ->
                            when (effect) {
                                is ProfileEffect.NavigateToLogin -> navigator.replaceAll(LoginRoute())
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(logoutReceiver)
    }

    private fun navigateToLogin() {
        profileScreenModel.dispatch(ProfileIntent.ExecuteLogout(this))
    }
}
