package com.gustavolins.wallet.ui.activity.SignUp


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gustavolins.wallet.model.*
import com.gustavolins.wallet.usecase.SignUpUseCase
import kotlinx.coroutines.launch

class SingUpViewModel @ViewModelInject constructor(val signUpUseCase: SignUpUseCase ) : ViewModel() {
    val client = MutableLiveData<Client>()
    val register = MutableLiveData<Boolean>()


    fun signUp(client: Client) {
        viewModelScope.launch {
            signUpUseCase.signUp(client)
            register.value = true
        }
    }

}