package id.tiooooo.myarticle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.NavigatorDisposeBehavior
import cafe.adriel.voyager.transitions.SlideTransition
import id.tiooooo.myarticle.ui.pages.home.HomeRoute
import id.tiooooo.myarticle.ui.theme.MyArticleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyArticleTheme {
                Navigator(
                    screen = HomeRoute(),
                    disposeBehavior = NavigatorDisposeBehavior(),
                    onBackPressed = { true },
                ) { navigator ->
                    SlideTransition(navigator = navigator)
                }
            }
        }
    }
}