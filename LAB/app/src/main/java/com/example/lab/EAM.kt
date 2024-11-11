package com.example.lab

import androidx.compose.runtime.Composable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.ui.Modifier
import com.example.lab.ui.theme.LABTheme
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Estados() {
    var currentState by remember { mutableStateOf("Cargando") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AnimatedContent(
            targetState = currentState,
            transitionSpec = {
                fadeIn() with fadeOut()
            }
        ) { state ->
            when (state) {
                "Cargando" -> LoadingContent()
                "Contenido" -> DisplayContent()
                "Error" -> ErrorContent()
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            currentState = when (currentState) {
                "Cargando" -> "Contenido"
                "Contenido" -> "Error"
                "Error" -> "Cargando"
                else -> "Cargando"
            }
        }) {
            Text(text = "Cambiar Estado")
        }
    }
}

@Composable
fun LoadingContent() {
    Text(text = "Cargando...", style = MaterialTheme.typography.headlineMedium)
}

@Composable
fun DisplayContent() {
    Text(text = "¡Contenido cargado!", style = MaterialTheme.typography.headlineMedium)
}

@Composable
fun ErrorContent() {
    Text(text = "Error al cargar contenido.", style = MaterialTheme.typography.headlineMedium)
}

