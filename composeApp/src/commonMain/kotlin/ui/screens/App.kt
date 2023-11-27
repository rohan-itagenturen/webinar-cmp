package ui.screens

import androidx.compose.runtime.Composable
import di.AppModule
import filepicker.ImagePicker
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.PopUpTo
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition
import moe.tlaster.precompose.viewmodel.viewModel
import ui.navigation.ScreenRoute
import ui.screens.apidemo.ApiDemoScreen
import ui.screens.apidemo.ApiDemoViewModel
import ui.screens.home.HomeScreen
import ui.screens.picker.ImagePickerScreen
import ui.screens.splash.SplashScreen
import ui.theme.AppTheme

@Composable
fun App(
    imagePicker: ImagePicker
) {
    val userRepository = AppModule.getUserRepository()

    PreComposeApp {
        AppTheme {
            val navigator = rememberNavigator()
            NavHost(
                navigator = navigator,
                navTransition = NavTransition(),
                initialRoute = ScreenRoute.SPLASH
            ) {
                scene(
                    route = ScreenRoute.SPLASH,
                    navTransition = NavTransition()
                ) {
                    SplashScreen {
                        navigator.navigate(
                            it, NavOptions(
                                popUpTo = PopUpTo.First(true)
                            )
                        )
                    }
                }

                scene(
                    route = ScreenRoute.HOME,
                    navTransition = NavTransition()
                ) {
                    HomeScreen {
                        navigator.navigate(it)
                    }
                }

                scene(
                    route = ScreenRoute.IMAGEPICKER,
                    navTransition = NavTransition()
                ) {
                    ImagePickerScreen(
                        imagePicker = imagePicker,
                        onBackPressed = {
                            navigator.popBackStack()
                        }
                    )
                }

                scene(
                    route = ScreenRoute.APIDEMO,
                    navTransition = NavTransition()
                ) {
                    val viewModel = viewModel(ApiDemoViewModel::class) {
                        ApiDemoViewModel(userRepository)
                    }
                    ApiDemoScreen(
                        viewModel = viewModel,
                        onBackPressed = {
                            navigator.popBackStack()
                        }
                    )
                }
            }
        }
    }
}