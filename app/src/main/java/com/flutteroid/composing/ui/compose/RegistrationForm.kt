package com.flutteroid.composing.ui.compose

import android.content.Context
import android.content.SharedPreferences
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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


@Composable
fun RegistrationForm(
  context: Context? = null,
  sharedPreferences: SharedPreferences? = null,
  navController: NavHostController
) {


  val firstName = remember {
    mutableStateOf("")
  }
  val lastName = remember {
    mutableStateOf("")
  }
  val email = remember {
    mutableStateOf("")
  }
  val password = remember {
    mutableStateOf("")
  }
  val isPasswordShown = remember {
    mutableStateOf(false)
  }
  Scaffold { innerPadding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
        .padding(horizontal = 20.dp, vertical = 30.dp),
      horizontalAlignment = Alignment.Start
    ) {
      Icon(
        imageVector = Icons.Default.ArrowBack,
        contentDescription = "back",
        modifier = Modifier
          .clickable {

          }
          .size(30.dp), tint = Color(0xFFb83d43))
      Spacer(modifier = Modifier.height(5.dp))
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
      Spacer(modifier = Modifier.height(20.dp))
      OutlinedTextField(value = firstName.value, onValueChange = { value ->
        firstName.value = value
      }, modifier = Modifier.fillMaxWidth(), label = {
        Text(text = "FIRST NAME", color = Color.Gray, fontWeight = FontWeight.SemiBold)
      }, maxLines = 1, keyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Next
      )
      )
      Spacer(modifier = Modifier.height(10.dp))
      OutlinedTextField(value = lastName.value, onValueChange = { value ->
        lastName.value = value
      }, modifier = Modifier.fillMaxWidth(), label = {
        Text(text = "LAST NAME", color = Color.Gray, fontWeight = FontWeight.SemiBold)
      }, maxLines = 1, keyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Next
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
          val firstName = firstName.value
          val lastName = lastName.value
          val email = email.value
          val password = password.value
          onSubmitClicked(
            firstName,
            lastName,
            email,
            password,
            context,
            sharedPreferences
          ) { message, success ->
            if (success) {
              navController.popBackStack()
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
        Text(text = "SIGN UP", fontSize = 18.sp)
      }
      Spacer(modifier = Modifier.height(10.dp))
      Text(text = "Already have an account?", modifier = Modifier
        .clickable {
          navController.popBackStack()
        }
        .align(Alignment.CenterHorizontally))
    }
  }
}

fun onSubmitClicked(
  firstName: String,
  lastName: String,
  email: String,
  password: String,
  context: Context?,
  sharedPreferences: SharedPreferences?,
  onSubmitCompletedCallback: (String, Boolean) -> Unit
) {
  if (firstName.isEmpty() || firstName.length < 3) {
    onSubmitCompletedCallback("First name is not valid", false)
  } else if (lastName.isEmpty() || lastName.length < 3) {
    onSubmitCompletedCallback("Last name is not valid", false)
  } else if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
    onSubmitCompletedCallback("Email is not valid", false)
  } else if (password.isEmpty() || password.length < 8) {
    onSubmitCompletedCallback("Password is not valid", false)
  } else {
    val editor = sharedPreferences?.edit()
    editor?.putString("first_name", firstName)
    editor?.putString("last_name", lastName)
    editor?.putString("email", email)
    editor?.putString("password", password)
    editor?.apply()
    onSubmitCompletedCallback("User Registered Successfully", true)

  }
}
