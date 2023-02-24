package com.example.clientvk.helpers
import com.vk.api.sdk.requests.VKRequest
import org.json.JSONObject

class testt : VKRequest<String>("friends.get"){
    init {
        addParam("lang", "ru")
        addParam("fields", "photo_100")
        addParam("count", 5)
        addParam("fields", "sex, status, online")
        addParam("order", "hints")
    }

    override fun parse(r: JSONObject): String {
        val users = r.toString()
        return users
    }
}