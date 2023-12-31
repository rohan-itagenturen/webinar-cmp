package ui.screens.picker

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import filepicker.ImagePicker
import filepicker.rememberBitmapFromBytes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImagePickerScreen(imagePicker: ImagePicker, onBackPressed: () -> Unit) {
    val imageBitmap = remember { mutableStateOf<ByteArray?>(null) }

    imagePicker.registerPicker {
        imageBitmap.value = it
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Image Picker Demo") },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Go Back"
                        )
                    }
                }
            )
        }
    ) {
        ImagePickerContent(
            modifier = Modifier,
            onPickImage = { imagePicker.pickImage() },
            byteArray = imageBitmap.value
        )
    }
}

@Composable
fun ImagePickerContent(modifier: Modifier, onPickImage: () -> Unit, byteArray: ByteArray? = null) {

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val bitmap = rememberBitmapFromBytes(byteArray)
        val photoModifier = modifier.size(200.dp).clip(RoundedCornerShape(10))

        Button(onPickImage) {
            Text("Open Media Gallery")
        }

        if (bitmap != null) {
            Image(
                bitmap = bitmap,
                contentDescription = null,
                modifier = photoModifier,
                contentScale = ContentScale.Crop
            )
        } else {
            Box(modifier = photoModifier.background(MaterialTheme.colorScheme.primaryContainer))
        }
    }
}