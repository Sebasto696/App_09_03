package com.example.app_03_09_2023.presentations.screens.Login

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_03_09_2023.R
import com.example.app_03_09_2023.presentations.screens.Login.LoginState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    val state : MutableState<LoginState> = mutableStateOf(LoginState())
    fun login(email:String, pass:String) {
        val errorMessages = if (email.isBlank() || pass.isBlank()) {
            R.string.error_input_empty
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            R.string.error_not_e_valid_email
        } else if (email != "scarrillo26@misena.edu.co" || pass != "12345") {
            R.string.error_invalid_credentials
        } else null
        errorMessages?.let {
            state.value = state.value.copy(errorMessages = it)
            return
        }
        viewModelScope.launch {
            state.value = state.value.copy(displayProgressBar = true)
            delay(3000)
            state.value = state.value.copy(email = email, pass = pass)
            state.value = state.value.copy(displayProgressBar = true)
            state.value = state.value.copy(successLogin = true)
        } //fin del viewModelScope
    } //fin de la fun
    fun hideErrorDialog(){
        state.value = state.value.copy(errorMessages = null)
    } //fin del hideErrorDialog
} //fin de la clase