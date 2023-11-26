package com.cmp.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import filepicker.ImagePickerFactory
import ui.screens.App

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App(
                imagePicker = ImagePickerFactory().createImagePicker()
            )
        }
    }
}