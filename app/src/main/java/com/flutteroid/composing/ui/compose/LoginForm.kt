package com.flutteroid.composing.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun LoginForm() {

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
  Scaffold(
    topBar = {
      Icon(
        imageVector = Icons.Default.ArrowBack,
        contentDescription = "back",
        modifier = Modifier
          .clickable {
          }
          .padding(start = 10.dp, top = 5.dp)
          .size(30.dp), tint = Color(0xFFb83d43)
      )
    }
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

          }
      )
    }
  }
}