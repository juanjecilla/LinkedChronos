package com.m0n0l0c0.linkedchronos.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.m0n0l0c0.linkedchronos.R

import com.m0n0l0c0.linkedchronos.base.BaseActivity
import com.m0n0l0c0.linkedchronos.ui.home.MainActivity

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        goToNextActivity()

    }

    private fun goToNextActivity() {
        Handler().postDelayed({
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }, 1500)
    }

}
