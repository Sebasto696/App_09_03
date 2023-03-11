package com.example.app_03_09_2023.presentations.screens.Registration


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.app_03_09_2023.presentations.components.EventDialog
import com.example.app_03_09_2023.presentations.components.RoundedButton
import com.example.app_03_09_2023.presentations.components.SocialMediaButton
import com.example.app_03_09_2023.presentations.components.TransparentTextField
import com.example.app_03_09_2023.presentations.navigation.Destinations
import com.example.app_03_09_2023.presentations.screens.Registration.RegisterState

@Composable
fun RegistrationScreen(
    navController: NavController,
    state: RegisterState,
    onRegister: (nameValue: String, emailValue: String, phoneValue: String, passValue: String, confirmPassValue: String) -> Unit,
    onBack: ()-> Unit,
    onDismissDialog: ()->Unit
) {
    val nameValue = remember { mutableStateOf("") }
    val emailValue = remember { mutableStateOf("") }
    val phoneValue = remember { mutableStateOf("") }
    val passValue = remember { mutableStateOf("") }
    val confirmPassValue = remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    var passwordVisibility by remember { mutableStateOf(false) }
    var confirmPasswordVisibility by remember { mutableStateOf(false) }


    Box(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { onBack() })
                {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Regresar al login",
                        tint = MaterialTheme.colors.primary
                    )
                }
                Text(
                    text = "CREAR UNA CUENTA",
                    style = MaterialTheme.typography.h6.copy(
                        color = MaterialTheme.colors.primary
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TransparentTextField(
                    textFieldValue = nameValue,
                    textLabel = "Nombre",
                    keyboardType = KeyboardType.Text,
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    imeAction = ImeAction.Next
                ) //texto nombre
                TransparentTextField(
                    textFieldValue = emailValue,
                    textLabel = "Correo",
                    keyboardType = KeyboardType.Email,
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    imeAction = ImeAction.Next
                ) //texto correo
                TransparentTextField(
                    textFieldValue = phoneValue,
                    textLabel = "Telefono",
                    maxChar = 10,
                    keyboardType = KeyboardType.Phone,
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    imeAction = ImeAction.Next
                ) //texto telefono
                TransparentTextField(
                    textFieldValue = passValue,
                    textLabel = "Contraseña",
                    keyboardType = KeyboardType.Password,
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    imeAction = ImeAction.Next,
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                passwordVisibility = !passwordVisibility
                            })
                        {
                            Icon(
                                imageVector = if (passwordVisibility) {
                                    Icons.Default.Visibility
                                } else {
                                    Icons.Default.VisibilityOff
                                },
                                contentDescription = "Toggle"
                            )
                        }
                    }, //fin del trailingIcon
                    visualTransformation = if (passwordVisibility) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    }
                ) //texto clave
                TransparentTextField(
                    textFieldValue = confirmPassValue,
                    textLabel = "Confirmar Contraseña",
                    keyboardType = KeyboardType.Password,
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                            onRegister(
                                nameValue.value,
                                emailValue.value,
                                phoneValue.value,
                                passValue.value,
                                confirmPassValue.value
                            )
                        }
                    ),
                    imeAction = ImeAction.Done,
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                confirmPasswordVisibility = !confirmPasswordVisibility
                            }) {
                            Icon(
                                imageVector = if (confirmPasswordVisibility) {
                                    Icons.Default.Visibility
                                } else {
                                    Icons.Default.VisibilityOff
                                },
                                contentDescription = "Icono del password"
                            )
                        }
                    }, //fin del trailingIcon
                    visualTransformation = if (confirmPasswordVisibility) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    }
                ) //texto confirmar clave
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                )
                RoundedButton(text = "Sign up",
                    displayProgressBar = false,
                    onClick = {
                        navController.navigate(route = Destinations.HomeScreen.route)
                    }
                ) //Boton Sign Up
                ClickableText(text = buildAnnotatedString {
                    append("Already have Account ")
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colors.primary,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("Log In ")
                    }
                }, onClick = {
                    navController.navigate(Destinations.LoginScreen.route)
                }) //Already have a account
            } //fin de la columna interna
            Spacer(modifier = Modifier.height(16.dp))
            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Divider(
                        modifier = Modifier.width(24.dp),
                        thickness = 1.dp,
                        color = Color.Gray
                    ) //linea divisoria
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = "OR",
                        style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Black)
                    )
                    Divider(
                        modifier = Modifier.width(24.dp),
                        thickness = 1.dp,
                        color = Color.Gray
                    )
                } //fin de la fila
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    text = "Login With",
                    style = MaterialTheme.typography.body1.copy(MaterialTheme.colors.primary),
                    textAlign = TextAlign.Center
                )
            } //fin de  la columna interna
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                SocialMediaButton(
                    text = "Facebook",
                    onClick = {/*TODO*/ },
                    socialMediaColor = MaterialTheme.colors.onSurface
                )
                SocialMediaButton(
                    text = "Instagram",
                    onClick = { /*TODO*/ },
                    socialMediaColor = MaterialTheme.colors.secondaryVariant
                )
            }
        } //fin de la columna principal
        if (state.errorMessages != null) {
            EventDialog(
                errorMessage = state.errorMessages,
                onDismiss = onDismissDialog
            )
        } //fin if
    } //fin del Box
} //fin de la pantalla