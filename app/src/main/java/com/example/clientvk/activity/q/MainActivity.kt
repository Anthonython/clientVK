package com.example.clientvk.activity.q

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.clientvk.R
import com.example.clientvk.presenter.LoginPresented
import com.example.clientvk.views.LoginView
import com.github.rahatarmanahmed.cpv.CircularProgressView
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiConfig
import com.vk.api.sdk.VKDefaultValidationHandler
import com.vk.api.sdk.auth.VKAuthenticationResult
import com.vk.api.sdk.auth.VKScope
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter


class MainActivity : MvpAppCompatActivity(), LoginView {
    private lateinit var mbutton: Button
    private lateinit var loadm: CircularProgressView

    @InjectPresenter
    lateinit var LoginPresented: LoginPresented

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mbutton = findViewById(R.id.mainlogV)
        loadm = findViewById(R.id.loadV)

        val conf = VKApiConfig( context = this, validationHandler = VKDefaultValidationHandler(this), version = VKApiConfig.DEFAULT_API_VERSION, appId = 51481899, langProvider = { "ru" })
        VK.setConfig(conf)
        val authLauncher = VK.login(this) { result : VKAuthenticationResult ->
            when (result) {
                is VKAuthenticationResult.Success ->  LoginPresented.login(true)
                is VKAuthenticationResult.Failed -> ShowError()
            }
        }

        mbutton.setOnClickListener {
            if (VK.isLoggedIn()) LoginPresented.login(true)
            else authLauncher.launch(arrayListOf(VKScope.WALL, VKScope.PAGES))
        }
    }

    override fun StartLoad() {
        mbutton.visibility = View.INVISIBLE
        loadm.visibility = View.VISIBLE
    }

    override fun EndLoad() {
        mbutton.visibility = View.VISIBLE
        loadm.visibility = View.INVISIBLE
    }

    override fun ShowError() {
        Toast.makeText(this, R.string.failedmine, Toast.LENGTH_SHORT).show()
    }

    override fun OpenAva() {
        startActivity(Intent(this, testVKAvaLayout::class.java))
    }

    override fun onResume() {
        super.onResume()
        EndLoad()
    }
}








  






