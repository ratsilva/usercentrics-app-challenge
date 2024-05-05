package br.com.usercentrics.challenge

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import br.com.usercentrics.challenge.presentation.NavItem
import br.com.usercentrics.challenge.presentation.NavigationScreen
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavHostActivityTest {

    @get: Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            NavigationScreen(navController = navController)
        }
    }

    @Test
    fun verifyStartRoute() {
        navController.assertCurrentRouteName(NavItem.Home.route)
    }

}

private fun NavController.assertCurrentRouteName(expectedRouteName: String) {
    Assert.assertEquals(expectedRouteName, currentBackStackEntry?.destination?.route)
}