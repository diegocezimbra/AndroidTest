package br.com.androidtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.com.androidtest.designsystem.theme.AndroidTestTheme
import br.com.androidtest.navigation.AppNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AndroidTestTheme {
                AppNavHost(onExitApp = { finish() })
            }
        }
    }
}
