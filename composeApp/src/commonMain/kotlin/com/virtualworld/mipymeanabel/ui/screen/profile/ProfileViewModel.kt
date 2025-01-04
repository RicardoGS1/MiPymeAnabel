package com.virtualworld.mipymeanabel.ui.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virtualworld.mipymeanabel.data.model.AuthenticationState
import com.virtualworld.mipymeanabel.data.model.SignResponseState
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

    private val _signInState = MutableStateFlow<SignResponseState>(SignResponseState.Idle)
    val signInState: StateFlow<SignResponseState> get() = _signInState


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


    fun signUp(name: String, email: String, password: String) {

        viewModelScope.launch {

            authUseCase.singUp(email, password, name)

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
            _signInState.update { SignResponseState.Loading }
            _signInState.update { authUseCase.signIn(userMail, userPassword) }
        }

    }


}