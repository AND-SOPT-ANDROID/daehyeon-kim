package org.sopt.and.ui.main

import androidx.compose.runtime.Composable
import org.sopt.and.R
import org.sopt.and.core.navigation.MainTabRoute
import org.sopt.and.core.navigation.Route

enum class MainTab(
    val iconResId: Int,
    val contentDescription: String,
    val route: MainTabRoute
) {
    HOME(
        iconResId = R.drawable.ic_home,
        contentDescription = "홈",
        route = MainTabRoute.Home
    ),
    SEARCH(
        iconResId = R.drawable.ic_search,
        contentDescription = "검색",
        route = MainTabRoute.Search
    ),
    MY(
        iconResId = R.drawable.img_profile,
        contentDescription = "My",
        route = MainTabRoute.My
    );

    companion object {
        @Composable
        fun find(predicate: @Composable (MainTabRoute) -> Boolean): MainTab? {
            return entries.find { predicate(it.route) }
        }

        @Composable
        fun contains(predicate: @Composable (Route) -> Boolean): Boolean {
            return entries.map { it.route }.any { predicate(it) }
        }
    }
}
