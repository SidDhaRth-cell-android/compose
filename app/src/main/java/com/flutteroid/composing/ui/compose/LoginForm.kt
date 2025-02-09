package com.flutteroid.composing.ui.compose

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.flutteroid.composing.ui.navigation.Screen


@Composable
fun LoginForm(navController: NavHostController, sharedPreferences: SharedPreferences, context: Context) {

  val email = remember {
    mutableStateOf("")
  }
  val password = remember {
    mutableStateOf("")
  }
  val isPasswordShown = remember {
    mutableStateOf(false)
  }
  Scaffold(
  ) { innerPadding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
        .padding(horizontal = 20.dp, vertical = 30.dp),
      horizontalAlignment = Alignment.Start,
      verticalArrangement = Arrangement.Center
    ) {
      Text(
        text = "Your fries\ndeserve better.", style = TextStyle(
          color = Color(0xFFb83d43), fontWeight = FontWeight.ExtraBold, fontSize = 32.sp
        )
      )
      Spacer(modifier = Modifier.height(5.dp))
      Text(
        text = "Join TomatoBasil now, It's free!", style = TextStyle(
          color = Color.Gray, fontSize = 16.sp
        )
      )
      Spacer(modifier = Modifier.height(10.dp))
      OutlinedTextField(value = email.value, onValueChange = { value ->
        email.value = value
      }, modifier = Modifier.fillMaxWidth(), label = {
        Text(text = "EMAIL", color = Color.Gray, fontWeight = FontWeight.SemiBold)
      }, maxLines = 1, keyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Next
      )
      )
      Spacer(modifier = Modifier.height(10.dp))
      OutlinedTextField(
        value = password.value,
        onValueChange = { value ->
          password.value = value
        },
        modifier = Modifier.fillMaxWidth(),
        label = {
          Text(text = "PASSWORD", color = Color.Gray, fontWeight = FontWeight.SemiBold)
        },
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
          imeAction = ImeAction.Done
        ),
        trailingIcon = {
          IconButton(onClick = {
            isPasswordShown.value = !isPasswordShown.value
          }) {
            if (isPasswordShown.value)
              Icon(Icons.Default.Favorite, contentDescription = "password")
            else
              Icon(Icons.Default.FavoriteBorder, contentDescription = "password")
          }
        },
        visualTransformation = if (isPasswordShown.value) VisualTransformation.None else PasswordVisualTransformation()
      )
      Spacer(modifier = Modifier.height(20.dp))
      Button(
        onClick = {
          onSignInClicked(email.value, password.value, sharedPreferences) { message, status ->
            if (status) {
              val editor = sharedPreferences.edit()
              editor.putBoolean("isLoggedIn", true)
              editor.apply()
              navController.navigate(Screen.Dashboard.name)
            } else {
              Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }

          }
        },
        modifier = Modifier
          .fillMaxWidth()
          .height(50.dp),
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.buttonColors().copy(containerColor = Color(0xFFb83d43))
      ) {
        Text(text = "SIGN IN", fontSize = 18.sp)
      }
      Spacer(modifier = Modifier.height(10.dp))
      Text(
        text = "Don't have an account? Register.",
        modifier = Modifier
          .align(Alignment.CenterHorizontally)
          .clickable {
            navController.navigate(Screen.Registration.name)
          }
      )
    }
  }
}

fun onSignInClicked(
  email: String,
  password: String,
  sharedPreferences: SharedPreferences,
  onLoginCallback: (String, Boolean) -> Unit
) {
  val storedEmail = sharedPreferences.getString("email", null)
  val storedPassword = sharedPreferences.getString("password", null)

  if (email != storedEmail) {
    onLoginCallback("Email is not valid", false)
  } else if (password != storedPassword) {
    onLoginCallback("Password is not valid", false)
  } else {
    onLoginCallback("Login Successful", true)
  }


  Log.e("TAG", "onSignInClicked: $storedEmail")
  Log.e("TAG", "onSignInClicked: $storedPassword")

}
