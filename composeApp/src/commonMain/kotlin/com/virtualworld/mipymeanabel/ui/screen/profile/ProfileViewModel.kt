package com.virtualworld.mipymeanabel.ui.screen.profile

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.virtualworld.mipymeanabel.data.AuthenticationState
import com.virtualworld.mipymeanabel.domain.models.AuthModel
import com.virtualworld.mipymeanabel.domain.useCase.auth.LoadUserUseCase
import com.virtualworld.mipymeanabel.domain.useCase.auth.SignInUseCase
import com.virtualworld.mipymeanabel.domain.useCase.auth.SignOutUseCase
import com.virtualworld.mipymeanabel.domain.useCase.auth.SignUpUseCase
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val signInUseCase: SignInUseCase,
    private val signOutUseCase: SignOutUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val loadUserUseCase: LoadUserUseCase
) : ViewModel() {

    private val _userState = MutableStateFlow<AuthenticationState>(AuthenticationState.Loading)
    val userState: StateFlow<AuthenticationState> get() = _userState


    init {

        loadUser()

    }

    private fun loadUser() {

        viewModelScope.launch {


            loadUserUseCase.invoke().collect { authState ->

                _userState.update { authState }


            }


        }
    }


    fun signUp(email: String, password: String) {

        viewModelScope.launch {

            signUpUseCase(email, password)

        }


    }

    fun signOut() {
        viewModelScope.launch {
            _userState.update { AuthenticationState.Loading }
            signOutUseCase()
        }
    }

    fun singIn(userMail: String, userPassword: String) {

        viewModelScope.launch {
            signInUseCase(userMail, userPassword)
        }

    }


}