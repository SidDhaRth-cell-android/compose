package com.flutteroid.composing

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage


class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {

      //LoginForm()
      //ShowMyImageFromNetwork()
    }
  }


}


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun ShowMyStack() {
  Text(text = "Hello WOrld")
  Text(text = "Hello WOrld sjkd")
  Text(text = "Hello WOrld dsfsdfsd")
  Text(text = "Hello WOrld dsfsdfsdfsdf")
  Text(text = "Hello WOrld sdfsdfsdfsdfsdfsd")
  Text(text = "Hello WOrld dsfsdfsdfsdfsdfsdfsdf")
}


@Composable
fun ShowMyImageFromNetwork() {
  AsyncImage(
    model = "https://reqres.in/img/faces/8-image.jpg",
    contentDescription = "network_image"
  )
}


@Composable
fun ShowMyImage() {
  Box(
    modifier = Modifier.background(Color.Black)
  ) {
    Image(
      painter = painterResource(id = R.drawable.image),
      contentDescription = "image",
      contentScale = ContentScale.Crop,
      modifier = Modifier
        .height(200.dp)
        .fillMaxSize()
    )
  }

}


@Composable
fun LoginForm() {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(color = Color(0xFF042F68))
      .padding(horizontal = 20.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Column(
      modifier = Modifier.weight(1f),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(
        text = "facebook", style = TextStyle(
          color = Color.White,
          fontSize = 28.sp,
          fontWeight = FontWeight.ExtraBold
        )
      )
      Spacer(modifier = Modifier.height(30.dp))
      TextField(value = "", onValueChange = {

      }, placeholder = {
        Text(text = "email or phone")
      }, modifier = Modifier.fillMaxWidth())
      Spacer(modifier = Modifier.height(10.dp))
      TextField(value = "", onValueChange = {

      }, placeholder = {
        Text(text = "password")
      }, modifier = Modifier.fillMaxWidth())
      Spacer(modifier = Modifier.height(20.dp))
      Button(
        onClick = {},
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.buttonColors().copy(containerColor = Color(0xFF031936))
      ) {
        Text(text = "Log In")
      }
    }
    Text(
      text = "sign up for facebook",
      style = TextStyle(color = Color.White),
      fontSize = 16.sp,
      modifier = Modifier.padding(bottom = 30.dp)
    )
  }

}


@Composable
fun ShowMyText() {
  Text(
    text = "Siddharth is an Android Developer", style = TextStyle(
      fontSize = 50.sp,
      fontWeight = FontWeight.ExtraBold,
      color = Color.Green
    )
  )
}

@Composable
fun ShowMyInputField() {
  val data = remember { mutableStateOf("") }
  val isPasswordShown = remember {
    mutableStateOf(false)
  }
  OutlinedTextField(value = data.value, onValueChange = { value ->
    data.value = value
    Log.e("TAG", "ShowMyInputField: $value")
  }, modifier = Modifier.padding(top = 50.dp), placeholder = {
    Text(text = "Enter Your name here")
  }, leadingIcon = {
    Icon(Icons.Default.Person, contentDescription = "person")
  }, trailingIcon = {
    IconButton(onClick = {
      isPasswordShown.value = !isPasswordShown.value
    }) {
      if (isPasswordShown.value)
        Icon(Icons.Default.Favorite, contentDescription = "password")
      else
        Icon(Icons.Default.FavoriteBorder, contentDescription = "password")
    }
  }, label = {
    Text(text = "Your name")
  },
    visualTransformation = if (isPasswordShown.value) VisualTransformation.None else PasswordVisualTransformation()
  )
}


@Composable
fun ShowMyButton() {
  Button(
    onClick = {
      Log.d("We're learning logging", "Data Logging")
      Log.e("We're learning logging", "Error Logging")
      Log.i("We're learning logging", "Information Logging")
    },
    modifier = Modifier
      .padding(top = 50.dp, start = 20.dp, end = 10.dp)
      .fillMaxWidth(),
    shape = RoundedCornerShape(5.dp),
    colors = ButtonDefaults.buttonColors()
      .copy(containerColor = Color.Black)
  ) {
    Text(text = "Login with google", style = TextStyle(color = Color.Green))
    Spacer(modifier = Modifier.width(20.dp))
    Icon(Icons.Default.Person, contentDescription = "person")
    Spacer(modifier = Modifier.width(20.dp))
    Icon(Icons.Default.Person, contentDescription = "person")
    Spacer(modifier = Modifier.width(20.dp))
    Icon(Icons.Default.Person, contentDescription = "person")
  }
}
