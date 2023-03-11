package com.example.app_03_09_2023.presentations.screens.Login

import androidx.annotation.StringRes

data class AddState(
    val nombre: String = "",
    val año: String = "",
    val temporada: String = "",
    val displayProgressBar: Boolean = false,
    @StringRes val errorMessages: Int? = null
)