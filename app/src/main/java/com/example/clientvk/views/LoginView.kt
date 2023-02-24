package com.example.clientvk.views

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface LoginView:MvpView {
    fun StartLoad()
    fun EndLoad()
    fun ShowError()
    fun OpenAva()
}