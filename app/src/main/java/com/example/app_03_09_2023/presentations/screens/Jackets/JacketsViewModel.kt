package com.example.app_03_09_2023.presentations.screens.Jackets

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase

class JacketsViewModel : ViewModel() {
    //Para poder consultar debemos crear un metodo get de la clase
    private val _jackets = mutableStateOf<List<Jacket>>(emptyList())
    //Creamos una variable para el Query a la coleccion de la BD
    val jackets : State<List<Jacket>>
        get() = _jackets
    private val query = Firebase.firestore.collection("jackets")
    //Creamos el metodo constructor del viewModel que leera los alumnos
    init {
        query.addSnapshotListener{value, _ ->
            if (value != null){
                _jackets.value = value.toObjects()
            }
        }
    }
}