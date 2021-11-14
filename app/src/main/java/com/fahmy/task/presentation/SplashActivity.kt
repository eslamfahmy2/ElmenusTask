package com.fahmy.task.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import kotlinx.coroutines.ExperimentalCoroutinesApi

@SuppressLint("CustomSplashScreen")
@ExperimentalCoroutinesApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        finish()

    }
}