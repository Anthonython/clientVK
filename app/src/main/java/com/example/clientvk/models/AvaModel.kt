package com.example.clientvk.models

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONObject

data class AvaModel(var id: Int, var name: String, var surname: String, var isOnline: Int = 0, var avatar: String, var fullAva: String): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString())

    companion object CREATOR : Parcelable.Creator<AvaModel> {
        override fun createFromParcel(parcel: Parcel): AvaModel {
            return AvaModel(parcel) }

        override fun newArray(size: Int): Array<AvaModel?> {
            return arrayOfNulls(size) }

        fun parse(json: JSONObject) = AvaModel(
            name = json.optString("first_name", ""),
            surname = json.optString("last_name", ""),
            avatar = json.optString("photo_100", ""),
            isOnline = json.optInt("online", 0),
            fullAva = json.optString("photo_max_orig", ""),
            id = json.optInt("id", 0))
    }

    override fun describeContents(): Int {
        return 0 }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(surname)
        parcel.writeByte(1)
        parcel.writeString(avatar)
        parcel.writeString(fullAva)
    }


}