package com.example.clientvk.helpers

import com.example.clientvk.models.AvaModel
import com.vk.api.sdk.requests.VKRequest
import org.json.JSONObject

class VKFriendsRequest(uid: Int = 0) : VKRequest<ArrayList<AvaModel>>("friends.get") {
    init {
        if (uid != 0) {
            addParam("user_id", uid)
        }
        addParam("lang", 0)
        addParam("fields", "photo_max_orig, online, photo_100")
        addParam("order", "hints")
            //      addParam("count", 5)
    }
    override fun parse(r: JSONObject): ArrayList<AvaModel> {
        val users = r.getJSONObject("response").getJSONArray("items")
        val result = ArrayList<AvaModel>()
        for (i in 0 until users.length()) {
            result.add(AvaModel.parse(users.getJSONObject(i)))
        }
        return result
    }
    }

