package com.tezov.koin_sample.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.tezov.koin_sample.presentation.navigation.BottomNavBar
import com.tezov.koin_sample.presentation.navigation.AppNavHost
import com.tezov.koin_sample.presentation.theme.KoinsampleTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KoinsampleTheme {
                KoinSampleApp(viewModel)
            }
        }
    }
}

@Composable
fun KoinSampleApp(
    viewModel: MainActivityViewModel? = null
) {
    val navController = rememberNavController()
    val title = viewModel?.title?.collectAsState()?.value

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Text(title ?: "Preview Title")
        },
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) { innerPadding ->
        AppNavHost(navController = navController, innerPadding = innerPadding)
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewKoinSample() {
    KoinsampleTheme {
        KoinSampleApp()
    }
}
