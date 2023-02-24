package com.example.clientvk.providers

import com.example.clientvk.models.AvaModel
import com.example.clientvk.presenter.AvaPresenter
import com.example.clientvk.helpers.VKFriendsRequest
import com.google.gson.JsonParser
import com.vk.api.sdk.*
import com.vk.api.sdk.requests.VKRequest
import org.json.JSONObject

private var photoid = 0
private var userid = 0
private var isliked = 0

class AvaProv(var presenter: AvaPresenter) {

    fun loadVKFriend() {
         VK.execute(VKFriendsRequest(), object : VKApiCallback<ArrayList<AvaModel>> {
             override fun fail(error: Exception) {}
             override fun success(result: ArrayList<AvaModel>) { presenter.loadedava(result) }
         })
    }

    fun loadPhoto(id: Int){
        userid = id
        val request = VKRequest<JSONObject>("photos.get").addParam("owner_id", userid).addParam("album_id", "profile").addParam("count", "1").addParam("rev", 1)
        VK.execute(request, object : VKApiCallback<JSONObject>{
            override fun success(result: JSONObject) {
                JsonParser().parse(result.toString()).asJsonObject.get("response").asJsonObject.getAsJsonArray("items").forEach {
                    photoid = it.asJsonObject.get("id").asInt
                }
            }
            override fun fail(error: Exception) {}
        })
    }

    fun loadAphotos(id: Int){
        userid = id
        val photos = VKRequest<JSONObject>("photos.get").addParam("owner_id", userid).addParam("album_id", "profile").addParam("rev", 1).addParam("photo_sizes", 1)
        VK.execute(photos, object: VKApiCallback<JSONObject>{
            override fun fail(error: Exception) {}
            override fun success(result: JSONObject) {
                val ArrayPhoto = ArrayList<String>()
                ArrayPhoto.clear()
                ArrayPhoto.add("https://cojo.ru/wp-content/uploads/2022/12/belyi-priamougolnik-2.webp")
                JsonParser().parse(result.toString()).asJsonObject.get("response").asJsonObject.getAsJsonArray("items").forEach {
                        ArrayPhoto.add(it.asJsonObject.getAsJsonArray("sizes").get(2).asJsonObject.get("url").asString)
                }
                presenter.viewState.sendphoto(ArrayPhoto)
            }

        })
    }

    fun likeava(){
        val addlike = VKRequest<JSONObject>("likes.add").addParam("type", "photo").addParam("owner_id", userid.toString()).addParam("item_id", photoid.toString())
        VK.execute(addlike, object : VKApiCallback<JSONObject>{
            override fun fail(error: Exception) {}

            override fun success(result: JSONObject) {} })
    }

    fun isLiked(id: Int){
        val isLiked = VKRequest<JSONObject>("likes.isLiked").addParam("type", "photo").addParam("owner_id", id).addParam("item_id", photoid)
        VK.execute(isLiked, object : VKApiCallback<JSONObject>{
            override fun fail(error: Exception) {}

            override fun success(result: JSONObject) {
                isliked = JsonParser().parse(result.toString()).asJsonObject.get("response").asJsonObject.get("liked").asInt
                presenter.isLikedSet(isliked)
            }
        })
    }

    fun loadfullpic(id: Int){
        userid = id
        val pic = VKRequest<JSONObject>("photos.get").addParam("owner_id", userid).addParam("album_id", "profile").addParam("rev", 1)
        VK.execute(pic, object: VKApiCallback<JSONObject>{
            override fun fail(error: Exception) {}
            override fun success(result: JSONObject) {
                val arrayphot = ArrayList<String>()
                JsonParser().parse(result.toString()).asJsonObject.get("response").asJsonObject.getAsJsonArray("items").forEach {
                    arrayphot.add(it.asJsonObject.getAsJsonArray("sizes").get(it.asJsonObject.getAsJsonArray("sizes").size()-1).asJsonObject.get("url").asString)
                }
                presenter.viewState.loadfullpic(arrayphot)
            } })
    }


}

