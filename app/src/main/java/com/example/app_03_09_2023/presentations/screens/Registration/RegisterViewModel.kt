package com.example.app_03_09_2023.presentations.screens.Registration

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_03_09_2023.R
import com.example.app_03_09_2023.presentations.screens.Registration.RegisterState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RegisterViewModel: ViewModel() {
    val state : MutableState<RegisterState> = mutableStateOf(RegisterState())
    fun register(
        nameValue:String,
        emailValue:String,
        phoneValue:String,
        passValue:String,
        confirmPassValue:String
    ){
        val errorMessages = if(nameValue.isBlank() || emailValue.isBlank()  || phoneValue.isBlank()
            || passValue.isBlank() || confirmPassValue.isBlank()){
            R.string.error_input_empty
        }else if(!Patterns.PHONE.matcher(phoneValue).matches()){
            R.string.error_not_e_valid_phone_number
        }else if(!Patterns.EMAIL_ADDRESS.matcher(emailValue).matches()){
            R.string.error_not_e_valid_email
        }else if(passValue != confirmPassValue){
            R.string.error_incorrectly_repeated_password
        }else null
        //validacion de la variable
        errorMessages?.let {
            state.value = state.value.copy(errorMessages = errorMessages)
            return
        }//fin validacion
        //carga del modelo
        viewModelScope.launch {
            state.value = state.value.copy(displayProgressBar = true)
            delay(3000)
            state.value = state.value.copy(displayProgressBar = true)
        }//fin viewmodelscope
    }//fin fun
    //fun para resetear el mensaje de error
    fun hideErrorDialog(){
        state.value = state.value.copy(errorMessages = null)
    }//fin fun hide
}