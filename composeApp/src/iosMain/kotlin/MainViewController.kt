import androidx.compose.ui.window.ComposeUIViewController
import androidx.compose.ui.interop.LocalUIViewController
import filepicker.ImagePickerFactory
import ui.screens.App

fun MainViewController() = ComposeUIViewController {
    App(
        imagePicker = ImagePickerFactory(LocalUIViewController.current).createImagePicker()
    )
}
