package com.example.app_03_09_2023.presentations.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ForgotPassword(navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Recordar contraseña")
        Spacer(modifier = Modifier.size(6.dp))
        Button(onClick = { navController.navigate("loginScreen") },
            modifier = Modifier
                .width(200.dp)
                .height(50.dp)) {
            Text(text = "Salir")
        }
    }
}