package com.example.app_03_09_2023.presentations.screens.Login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.app_03_09_2023.R
import com.example.app_03_09_2023.presentations.components.EventDialog
import com.example.app_03_09_2023.presentations.components.RoundedButton
import com.example.app_03_09_2023.presentations.components.TransparentTextField
import com.example.app_03_09_2023.presentations.navigation.Destinations
import com.example.app_03_09_2023.presentations.screens.Login.LoginState
import kotlin.reflect.KFunction0


@Composable
fun LoginScreen(
    navController:NavController,
    state: LoginState,
    onLogin: (dato1: String, dato2: String) -> Unit,
    onNavigateRegister: () -> Unit,
    onNavigateForgot: () -> Unit,
    onDismissDialog: KFunction0<Unit>,
) {
    val emailValue = rememberSaveable { mutableStateOf("") }
    val passwordValue = rememberSaveable { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        Image(
            painter = painterResource(id = R.drawable.asics),
            contentDescription = "Imagen del logo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(400.dp)
        ) //fin de la Image
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            ConstraintLayout {
                val (surface, fab) = createRefs()
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .constrainAs(surface) {
                            bottom.linkTo(parent.bottom)
                        },
                    color = Color.White,
                    shape = RoundedCornerShape(
                        topStartPercent = 8,
                        topEndPercent = 8
                    )
                ) {
                    Column() {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "¡BIENVENIDO!",
                                style = MaterialTheme.typography.h4.copy(
                                    fontWeight = FontWeight.Medium
                                )
                            )
                            Text(
                                text = "Accede a tu cuenta:",
                                style = MaterialTheme.typography.h5.copy(
                                    color = MaterialTheme.colors.primary,
                                    fontWeight = FontWeight.Medium
                                )
                            )
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                TransparentTextField(
                                    textFieldValue = emailValue,
                                    textLabel = "E-Mail",
                                    keyboardType = KeyboardType.Email,
                                    keyboardActions = KeyboardActions(
                                        onNext = {
                                            focusManager.moveFocus(focusDirection = FocusDirection.Down)
                                        }
                                    ),
                                    imeAction = ImeAction.Next
                                )

                                TransparentTextField(
                                    textFieldValue = passwordValue,
                                    textLabel = "Password",
                                    keyboardType = KeyboardType.Password,
                                    keyboardActions = KeyboardActions(
                                        onDone = {
                                            focusManager.clearFocus()
                                            onLogin(emailValue.value, passwordValue.value)
                                        }
                                    ),
                                    imeAction = ImeAction.Done,
                                    trailingIcon = {
                                        IconButton(
                                            onClick = {
                                                passwordVisibility = !passwordVisibility
                                            }
                                        ) {
                                            Icon(
                                                imageVector = if (passwordVisibility) {
                                                    Icons.Default.Visibility
                                                } else {
                                                    Icons.Default.VisibilityOff
                                                },
                                                contentDescription = "Icono del Password"
                                            )
                                        } //fin del trailingIcon de la password
                                    },
                                    visualTransformation = if (passwordVisibility) {
                                        VisualTransformation.None
                                    } else {
                                        PasswordVisualTransformation()
                                    }
                                ) // fin del TextField de la contraseña
                                ClickableText(
                                    text = buildAnnotatedString {
                                        append(" ")
                                        withStyle(
                                            style = SpanStyle(
                                                color = MaterialTheme.colors.primary,
                                                fontWeight = FontWeight.Bold
                                            )
                                        ) {
                                            append("Remember Password")
                                        }//fin del withStyle
                                    }//fin texto
                                ) {
                                    onNavigateForgot()
                                }
                            }
                        }//fin columna interna
                        Spacer(modifier = Modifier.height(10.dp))
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            RoundedButton(text = "Login",
                                displayProgressBar = state.displayProgressBar,
                                onClick = {
                                    val datos = listOf(
                                        LoginState(
                                            emailValue.value, passwordValue.value,
                                            false, false, null
                                        )
                                    )
                                    onLogin(emailValue.value, passwordValue.value)
                                } //fin del onClick
                            ) //fin del RoundedButton
                            ClickableText(
                                text = buildAnnotatedString {
                                    append("¿No tiene una cuenta activa? ")
                                    withStyle(
                                        style = SpanStyle(
                                            color = MaterialTheme.colors.error,
                                            fontWeight = FontWeight.Bold
                                        )
                                    ) {
                                        append("Sing Up")
                                    } //fin del withstyle
                                }//fin texto
                            ) {
                                onNavigateRegister()
                            }//fin clickabletext
                        } // columna para botones adicionales
                    }//fin de la columna central
                }//fin del surface
                FloatingActionButton(
                    modifier = Modifier
                        .size(72.dp)
                        .constrainAs(fab) {
                            top.linkTo(surface.top, margin = (-36).dp)
                            end.linkTo(surface.end, margin = 36.dp)
                        },
                    backgroundColor = MaterialTheme.colors.primary,
                    onClick = {
                        onLogin(emailValue.value, passwordValue.value)
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(72.dp),
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Boton de avanzar",
                        tint = Color.White
                    )
                }
            }//fin del FloatingActionButton
        }//fin del Box interno
        if (state.errorMessages != null) {
            EventDialog(
                onDismiss = onDismissDialog,
                errorMessage = state.errorMessages
            )
        }//fin if
    }//fin del Box
} //fin fun