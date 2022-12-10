package com.dicoding.jetreward.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dicoding.jetreward.R
import com.dicoding.jetreward.ui.theme.JetRewardTheme

@Composable
fun BottomBar(navController: NavHostController, modifier: Modifier = Modifier) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    BottomNavigation(modifier = modifier) {
        val navigationItem = listOf(
            NavigationItem(
                title = stringResource(id = R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(id = R.string.menu_cart),
                icon = Icons.Default.ShoppingCart,
                screen = Screen.Cart
            ),
            NavigationItem(
                title = stringResource(id = R.string.menu_profile),
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profile
            )
        )
        BottomNavigation {
            navigationItem.map { item ->
                BottomNavigationItem(
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    },
                    label = { Text(text = item.title) },
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title,
                        )
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BottomBarPreview() {
    JetRewardTheme {
        BottomBar(navController = rememberNavController())
    }
}