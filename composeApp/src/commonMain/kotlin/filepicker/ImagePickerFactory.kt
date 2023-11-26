package filepicker

import androidx.compose.runtime.Composable

expect class ImagePickerFactory {

    @Composable
    fun createImagePicker() : ImagePicker
}