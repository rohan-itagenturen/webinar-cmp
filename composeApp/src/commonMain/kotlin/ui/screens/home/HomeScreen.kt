package ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ui.navigation.ScreenRoute.APIDEMO
import ui.navigation.ScreenRoute.IMAGEPICKER

@Composable
fun HomeScreen(onNavigate: (String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Button(onClick = {
            onNavigate(IMAGEPICKER)
        }) {
            Text("Show Image Picker Demo")
        }

        Button(onClick = {
            onNavigate(APIDEMO)
        }) {
            Text("Show API Call Demo")
        }
    }
}