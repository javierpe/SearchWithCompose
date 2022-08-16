package com.app.ml

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.app.ml.navigation.data.api.NavigationController
import com.app.ml.extensions.homeNav
import com.app.ml.extensions.productDetailNav
import com.app.ml.ui.theme.MLTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationController: NavigationController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MLTheme(
                darkTheme = false
            ) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    navigationController.Configure {
                        // Home
                        homeNav()

                        // Product Detail
                        productDetailNav()
                    }
                }
            }
        }
    }
}

