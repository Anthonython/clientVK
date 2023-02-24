package com.example.clientvk.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FModel: ViewModel() {
    val piclink0: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val piclink1: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val piclink2: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val piclink3: MutableLiveData<String> by lazy { MutableLiveData<String>() }
}