package com.flutteroid.composing.ui.navigation

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.flutteroid.composing.ui.compose.BottomRoutes
import com.flutteroid.composing.ui.compose.DashboardScreen
import com.flutteroid.composing.ui.compose.LoginForm
import com.flutteroid.composing.ui.compose.RegistrationForm


enum class Screen {
  Login,
  Registration,
  Dashboard,
  Profile
}


@Composable
fun AppNavigationHost(
  navController: NavHostController,
  context: Context,
  sharedPreferences: SharedPreferences
) {

  val isUserLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

  NavHost(
    navController = navController,
    startDestination = if (isUserLoggedIn) Screen.Dashboard.name else Screen.Login.name
  ) {
    composable(Screen.Login.name) {
      LoginForm(navController = navController, sharedPreferences, context)
    }
    composable(Screen.Registration.name) {
      RegistrationForm(
        navController = navController,
        context = context,
        sharedPreferences = sharedPreferences
      )
    }
    composable(Screen.Dashboard.name) {
      DashboardScreen(navController, sharedPreferences)
    }
    composable(BottomRoutes.Home.name) {
      Home()
    }

    composable(BottomRoutes.Search.name) {
      Search()
    }

    composable(BottomRoutes.AddReels.name) {
      AddReels()
    }

    composable(BottomRoutes.Profile.name) {
      Profile()
    }
  }

}