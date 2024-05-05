package br.com.usercentrics.challenge.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.usercentrics.core_design_system.preview.DevicesPreview
import br.com.usercentrics.core_design_system.theme.AppChallengeTheme

class NavHostActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppChallengeTheme {
                NavigationScreen()
            }
        }
    }
}

@Composable
fun NavigationScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Scaffold(modifier = modifier.fillMaxSize()) { paddingValues ->
        NavigationGraph(
            modifier = Modifier.padding(paddingValues = paddingValues),
            navController = navController
        )
    }
}

@Composable
fun NavigationGraph(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(navController, modifier = modifier, startDestination = NavItem.Home.route) {
        composable(NavItem.Home.route) {
            Text(text = "Home Screen")
        }
    }
}

@DevicesPreview
@Composable
fun NavigationScreenPreview() {
    AppChallengeTheme {
        NavigationScreen()
    }
}