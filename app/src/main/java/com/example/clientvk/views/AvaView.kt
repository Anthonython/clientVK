package com.example.clientvk.views

import com.example.clientvk.models.AvaModel
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface AvaView:MvpView {
    fun ShowError(id: Int)
    fun SetupEmprty()
    fun SetupAva(avaList: ArrayList<AvaModel>)
    fun StartLoad()
    fun EndLoad()
    fun ClckFava(AvaModel: AvaModel)
    fun isLiked(value: Int)
    fun sendphoto(array: ArrayList<String>)
    fun loadfullpic(link: ArrayList<String>)

}