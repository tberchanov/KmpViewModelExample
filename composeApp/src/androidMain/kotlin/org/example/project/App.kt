package org.example.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import kmpviewmodelexample.composeapp.generated.resources.Res
import kmpviewmodelexample.composeapp.generated.resources.compose_multiplatform
import org.example.project.presentation.viewModel.ExampleViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
@Preview
fun App(viewModel: ExampleViewModel = koinViewModel()) {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                    val counter by viewModel.counterFlow.collectAsStateWithLifecycle(-1)
                    Text("Counter: $counter")
                }
                DisposableEffect(showContent) {
                    if (showContent) {
                        viewModel.startCounter()
                    }
                    onDispose {
                        viewModel.clear()
                    }
                }
            }
        }
    }
}