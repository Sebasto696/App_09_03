package com.example.app_03_09_2023.presentations.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.app_03_09_2023.presentations.navigation.Destinations
import com.example.app_03_09_2023.presentations.screens.Jackets.Jacket
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable

fun AddScreen(navController: NavHostController) {
    BodyContent(navController)
}

@Composable
fun BodyContent(navController: NavController){
    var nombre by remember { mutableStateOf("") }
    var año by remember { mutableStateOf("") }
    var temporada by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(all = 16.dp)) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = nombre,
            onValueChange = {nombre = it},
            textStyle = TextStyle(color = Color.Magenta, fontWeight = FontWeight.Bold),
            label = { Text(text = "Nombre")},
            )
            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = año,
                onValueChange = {año = it},
                textStyle = TextStyle(color = Color.Magenta, fontWeight = FontWeight.Bold),
                label = { Text(text = "Año de salida")},
            )
            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = temporada,
                onValueChange = {temporada = it},
                textStyle = TextStyle(color = Color.Magenta, fontWeight = FontWeight.Bold),
                label = { Text(text = "Temporada de salida")},
            )
            Spacer(modifier = Modifier.height(20.dp))
            
            Button(onClick = {
                val jacket = Jacket (nombre, año, temporada)
                Firebase.firestore.collection("jackets").add(jacket)
            },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
                ) {
                    Text(text = "Add Jacket")
            }
            Button(onClick = {navController.navigate(Destinations.HomeScreen.route)},
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Regresar")
            }
        }
    }

}