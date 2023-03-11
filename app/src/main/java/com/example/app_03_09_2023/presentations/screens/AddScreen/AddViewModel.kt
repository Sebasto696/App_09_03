package com.example.app_03_09_2023.presentations.screens.Login

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_03_09_2023.R
import com.example.app_03_09_2023.presentations.screens.AddScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AddViewModel : ViewModel() {
    val state : MutableState<AddState> = mutableStateOf(AddState())
    fun add(nombre:String, año:String, temporada:String) {
        val errorMessages = if (nombre.isBlank() || año.isBlank() || temporada.isBlank()) {
            R.string.error_input_empty
        } else null
        errorMessages?.let {
            state.value = state.value.copy(errorMessages = it)
            return
        }
        viewModelScope.launch {
            state.value = state.value.copy(displayProgressBar = true)
            delay(3000)
            state.value = state.value.copy(nombre = nombre, año = año, temporada = temporada)
            state.value = state.value.copy(displayProgressBar = true)
        } //fin del viewModelScope
    } //fin de la fun
    fun hideErrorDialog(){
        state.value = state.value.copy(errorMessages = null)
    } //fin del hideErrorDialog
} //fin de la clase