package com.example.app_03_09_2023.presentations.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.app_03_09_2023.presentations.navigation.Destinations
import com.example.app_03_09_2023.presentations.screens.Jackets.Jacket
import com.example.app_03_09_2023.presentations.screens.Jackets.JacketsViewModel
import com.example.app_03_09_2023.R




@Composable
fun JacketCard(jacket: Jacket){
    Card(
        modifier = Modifier
            .padding(all = 16.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.DarkGray)
        ) {
            Text(text = "Nombre de la jacket: ${jacket.nombre}", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = "Año de salida: ${jacket.año} ", color = Color.Gray, fontWeight = FontWeight.Bold)
            Text(text = "Temporada de salida: ${jacket.temporada} ", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AppJackets(viewModel: JacketsViewModel, navController: NavController) {
    val logo = painterResource(R.drawable.gorp)
    Scaffold(
        topBar = {
            TopAppBar {}
        },
        floatingActionButton = {
            FloatingActionButton(modifier = Modifier.size(32.dp),
                onClick = {navController.navigate(route = Destinations.AddScreen.route)}) {
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = "Agregar",
                    tint = Color.White
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    )
    {
        Box(modifier = Modifier.fillMaxSize()) {
            Column() {
                Image(
                    logo, contentDescription = "Jackets",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 32.dp)
                )
                LazyColumn() {
                    items(viewModel.jackets.value) { jacket ->
                        JacketCard(jacket)
                    }

                }
            }
        }
    }


}

