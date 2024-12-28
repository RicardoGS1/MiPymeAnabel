package com.virtualworld.mipymeanabel.ui.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virtualworld.mipymeanabel.data.AuthenticationState
import com.virtualworld.mipymeanabel.domain.useCase.AuthUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val authUseCase: AuthUseCase,
) : ViewModel() {

    private val _userState = MutableStateFlow<AuthenticationState>(AuthenticationState.Loading)
    val userState: StateFlow<AuthenticationState> get() = _userState


    init {

        loadUser()

    }

    private fun loadUser() {

        viewModelScope.launch {


            authUseCase.loadUser().collect { authState ->

                _userState.update { authState }


            }


        }
    }


    fun signUp(email: String, password: String) {

        viewModelScope.launch {

            authUseCase.singUp(email, password)

        }


    }

    fun signOut() {
        viewModelScope.launch {
            _userState.update { AuthenticationState.Loading }
            authUseCase.singOut()
        }
    }

    fun singIn(userMail: String, userPassword: String) {

        viewModelScope.launch {
            authUseCase.signIn(userMail, userPassword)
        }

    }


}