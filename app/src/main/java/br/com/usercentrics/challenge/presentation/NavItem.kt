package br.com.usercentrics.challenge.presentation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import br.com.usercentrics.challenge.R

sealed class NavItem(@StringRes title: Int, var icon: ImageVector, var route: String){
    data object Home : NavItem(R.string.home_screen_title, Icons.Filled.Home,"home")
}