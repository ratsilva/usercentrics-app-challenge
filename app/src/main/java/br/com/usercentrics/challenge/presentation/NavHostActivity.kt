package br.com.usercentrics.challenge.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.usercentrics.core_design_system.preview.DevicesPreview
import br.com.usercentrics.core_design_system.theme.AppChallengeTheme
import br.com.usercentrics.feature_virtual_cost.presentation.screen.VirtualCostScreen
import org.koin.compose.KoinContext

class NavHostActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KoinContext {
                AppChallengeTheme {
                    NavigationScreen()
                }
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
            VirtualCostScreen()
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