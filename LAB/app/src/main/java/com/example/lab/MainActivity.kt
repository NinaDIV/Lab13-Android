package com.example.lab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import com.example.lab.ui.theme.LABTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LABTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    var isVisible by remember { mutableStateOf(false) }
    var isBlue by remember { mutableStateOf(true) }
    var isExpanded by remember { mutableStateOf(false) }
    var isDarkMode by remember { mutableStateOf(false) }

    // Cambiar el color de fondo según el modo
    val backgroundColor by animateColorAsState(
        targetValue = if (isDarkMode) Color.DarkGray else Color.White,
        animationSpec = tween(durationMillis = 500)
    )

    // Animación de tamaño y posición
    val size by animateDpAsState(if (isExpanded) 150.dp else 100.dp)
    val offsetX by animateDpAsState(if (isExpanded) 100.dp else 0.dp)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp)
            .background(backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Botón para alternar visibilidad
        ToggleVisibilityButton { isVisible = !isVisible }

        Spacer(modifier = Modifier.height(16.dp))

        // Cuadro que aparece y desaparece
        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            ColorBox(size, offsetX, if (isBlue) Color.Blue else Color.Green)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botón para cambiar color
        ColorChangeButton { isBlue = !isBlue }

        Spacer(modifier = Modifier.height(24.dp))

        // Botón para mover y cambiar tamaño
        ToggleSizeButton { isExpanded = !isExpanded }

        Spacer(modifier = Modifier.height(24.dp))

        // Botón para cambiar entre modo oscuro y claro
        DarkModeToggleButton { isDarkMode = !isDarkMode }
    }
}

@Composable
fun ToggleVisibilityButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = "Mostrar/Ocultar Cuadro")
    }
}

@Composable
fun ColorChangeButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = "Cambiar Color")
    }
}

@Composable
fun ToggleSizeButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = "Mover y Cambiar Tamaño")
    }
}

@Composable
fun DarkModeToggleButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = "Cambiar a Modo Oscuro")
    }
}

@Composable
fun ColorBox(size: Dp, offsetX: Dp, backgroundColor: Color) {
    Box(
        modifier = Modifier
            .size(size)
            .offset(x = offsetX)
            .background(backgroundColor, shape = CircleShape)
            .shadow(8.dp, shape = CircleShape)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MainScreen()
}
