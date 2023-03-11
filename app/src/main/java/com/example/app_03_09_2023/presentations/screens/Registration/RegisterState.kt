package com.example.app_03_09_2023.presentations.screens.Registration

import androidx.annotation.StringRes

data class RegisterState(
    val successRegister: Boolean = false,
    val displayProgressBar: Boolean = false,
    @StringRes val errorMessages: Int? = null
)
