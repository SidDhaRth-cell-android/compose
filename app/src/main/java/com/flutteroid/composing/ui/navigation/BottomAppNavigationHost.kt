package com.flutteroid.composing.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.flutteroid.composing.ui.compose.BottomRoutes


@Composable
fun BottomAppNavigationHost(bottomNavController: NavHostController) {
  NavHost(navController = bottomNavController, startDestination = BottomRoutes.Home.name) {
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


@Composable
fun Home() {
  Scaffold { innerPadding ->
    Text(
      text = "Hello from Home",
      style = TextStyle(fontSize = 50.sp),
      modifier = Modifier.padding(innerPadding)
    )
  }
}


@Composable
fun Search() {
  Scaffold { innerPadding ->
    Text(text = "Hello from Search", modifier = Modifier.padding(innerPadding), style = TextStyle(fontSize = 50.sp),)
  }
}


@Composable
fun AddReels() {
  Scaffold { innerPadding ->
    Text(text = "Hello from Add Reels", modifier = Modifier.padding(innerPadding),  style = TextStyle(fontSize = 50.sp),)
  }
}

@Composable
fun Profile() {
  Scaffold { innerPadding ->
    Text(text = "Hello from Profile", modifier = Modifier.padding(innerPadding),  style = TextStyle(fontSize = 50.sp),)
  }
}
