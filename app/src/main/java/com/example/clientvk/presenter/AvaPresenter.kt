package com.example.clientvk.presenter

import com.example.clientvk.models.AvaModel
import com.example.clientvk.providers.AvaProv
import com.example.clientvk.views.AvaView
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class AvaPresenter:MvpPresenter<AvaView>() {
    fun loadAva() {
           viewState.StartLoad()
           AvaProv(this).loadVKFriend()
        }
    fun loadedava(avalist: ArrayList<AvaModel>) {
        viewState.EndLoad()
        if (avalist.size == 0){
            viewState.SetupEmprty()
        } else {
            viewState.SetupAva(avalist)
        } }
    fun error(id: Int){ viewState.ShowError(id) }
    fun loadphoto(id: Int){ AvaProv(this).loadPhoto(id) }
    fun loadAphotos(id: Int){AvaProv(this).loadAphotos(id)}
    fun addlike(){AvaProv(this).likeava()}
    fun isLiked(id: Int){AvaProv(this).isLiked(id)}
    fun isLikedSet(value: Int){viewState.isLiked(value)}
    fun loadfullpic(id: Int){AvaProv(this).loadfullpic(id)}

    }

