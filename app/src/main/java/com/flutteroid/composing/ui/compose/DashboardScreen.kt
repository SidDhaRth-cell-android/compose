package com.flutteroid.composing.ui.compose

import android.content.SharedPreferences
import androidx.compose.foundation.layout.Column
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.flutteroid.composing.R


@Composable
fun DashboardScreen(navController: NavHostController, sharedPreferences: SharedPreferences) {

  Scaffold(
    bottomBar = {
      //GetMyBottomNavigation(navController)
    }
  ) { innerPadding ->

    Column {
      Text(text = "We are at the Dashboard", style = TextStyle(fontSize = 50.sp))

      Button(onClick = {
        val editor = sharedPreferences.edit()
        editor.putBoolean("isLoggedIn", false)
        editor.apply()
        navController.popBackStack()
      }) {
        Text(text = "Log Out")
      }
    }


    //  BottomAppNavigationHost(navController)
  }
}

fun _onLogoutClicked(onLogOut: () -> Unit) {
  onLogOut()
}


enum class BottomRoutes {
  Home, Search, AddReels, Profile
}


@Composable
fun GetNavigationIcons(nav: BottomRoutes) {
  when (nav) {
    BottomRoutes.Search -> Icon(
      painter = painterResource(id = R.drawable.baseline_search_24),
      contentDescription = nav.name
    )

    BottomRoutes.Home -> Icon(
      painter = painterResource(id = R.drawable.insta_home),
      contentDescription = nav.name
    )

    BottomRoutes.AddReels -> Icon(
      painter = painterResource(id = R.drawable.baseline_add_box_24),
      contentDescription = nav.name
    )

    BottomRoutes.Profile -> Icon(
      painter = painterResource(id = R.drawable.baseline_person_4_24),
      contentDescription = nav.name
    )
  }
}

@Composable
fun GetMyBottomNavigation(bottomNavController: NavHostController) {
  val navStack by bottomNavController.currentBackStackEntryAsState()
  val currentRoute = navStack?.destination?.route

  BottomNavigation {
    BottomRoutes.entries.forEach { nav ->
      BottomNavigationItem(selected = nav.name == currentRoute, onClick = {
        bottomNavController.navigate(nav.name)
      }, icon = {
        GetNavigationIcons(nav)
      }, label = {
        Text(text = nav.name)
      }, alwaysShowLabel = false)
    }
  }
}

