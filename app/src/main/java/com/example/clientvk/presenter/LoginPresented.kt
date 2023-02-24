package com.example.clientvk.presenter

import com.example.clientvk.views.LoginView
import moxy.InjectViewState
import moxy.MvpPresenter
import android.os.Handler

@InjectViewState
class LoginPresented:MvpPresenter<LoginView>() {
    fun login(isSuccess: Boolean) {
        viewState.StartLoad()
        if (isSuccess) viewState.OpenAva()
        else {
            viewState.ShowError()
            viewState.EndLoad()
        }
    }
}