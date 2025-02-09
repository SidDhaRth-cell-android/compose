package com.flutteroid.composing

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.flutteroid.composing.ui.navigation.AppNavigationHost


class MainActivity : ComponentActivity() {


  private lateinit var sharedPreferences: SharedPreferences

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    sharedPreferences = getSharedPreferences("food_pref", Context.MODE_PRIVATE)
    setContent {
      val navController = rememberNavController()
      MainApp(navController, this, sharedPreferences)
    }
  }

}

@Composable
fun MainApp(
  navController: NavHostController,
  context: Context,
  sharedPreferences: SharedPreferences
) {
  AppNavigationHost(navController, context, sharedPreferences)
}





