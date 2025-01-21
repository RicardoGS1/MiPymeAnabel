package com.virtualworld.mipymeanabel.ui.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virtualworld.mipymeanabel.data.dto.Order
import com.virtualworld.mipymeanabel.data.model.AuthenticationState
import com.virtualworld.mipymeanabel.data.model.NetworkResponseState
import com.virtualworld.mipymeanabel.data.model.SignResponseState
import com.virtualworld.mipymeanabel.domain.useCase.AuthUseCase
import com.virtualworld.mipymeanabel.domain.useCase.GetOrdersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val authUseCase: AuthUseCase,
    private val getOrdersUseCase: GetOrdersUseCase
) : ViewModel() {

    private val _userState = MutableStateFlow<AuthenticationState<String>>(AuthenticationState.Loading)
    val userState: StateFlow<AuthenticationState<String>> get() = _userState

    private val _signInState = MutableStateFlow<SignResponseState>(SignResponseState.Idle)
    val signInState: StateFlow<SignResponseState> get() = _signInState

    private val _signUpState = MutableStateFlow<SignResponseState>(SignResponseState.Idle)
    val signUpState: StateFlow<SignResponseState> get() = _signUpState

    private val _ordersState = MutableStateFlow<List<Order>>(emptyList())
    val ordersState: StateFlow<List<Order>> get() = _ordersState


    init {

        loadUser()

    }

    private fun loadUser() {

        viewModelScope.launch {


            authUseCase.loadUser().collect { authState ->
                when (authState) {
                    is AuthenticationState.Authenticated -> {
                        _userState.update { AuthenticationState.Authenticated(authState.result.email.toString()) }
                        loadOrders(authState.result.uid)
                    }

                    is AuthenticationState.AuthenticationError -> {
                        _userState.update { AuthenticationState.AuthenticationError(authState.error) }
                    }

                    is AuthenticationState.Loading -> {
                        _userState.update { AuthenticationState.Loading }
                    }

                    AuthenticationState.Unauthenticated -> {
                        _userState.update { AuthenticationState.Unauthenticated }
                    }
                }
            }
        }


    }

    private suspend fun loadOrders(uid: String) {

        getOrdersUseCase(uid).collect { stateOr ->
            when (stateOr) {
                is NetworkResponseState.Error -> {

                }

                is NetworkResponseState.Loading -> {

                }

                is NetworkResponseState.Success -> {
                    _ordersState.value = stateOr.result
                }
            }
        }

    }


    fun signUp(name: String, email: String, password: String) {

        viewModelScope.launch {

            _signUpState.update { SignResponseState.Loading }
            _signUpState.update { authUseCase.singUp(email, password, name) }

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