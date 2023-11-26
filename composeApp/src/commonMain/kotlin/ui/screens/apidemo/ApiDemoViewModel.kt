package ui.screens.apidemo

import domain.models.User
import domain.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import ui.utils.ViewState

class ApiDemoViewModel(
    private val usersRepository: UserRepository
) : ViewModel() {

    val uiState = MutableStateFlow(UIState())

    init {
        viewModelScope.launch {
            uiState.value = uiState.value.copy(state = ViewState.LOADING)

            usersRepository.getUsers().collect {
                uiState.value = uiState.value.copy(
                    state = ViewState.LOADED,
                    users = it
                )
            }
        }
    }

    data class UIState(
        val state: ViewState = ViewState.LOADING,
        val users: List<User> = emptyList()
    )
}