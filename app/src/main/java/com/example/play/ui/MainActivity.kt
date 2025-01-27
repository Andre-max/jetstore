package com.example.play.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.core.view.WindowCompat
import com.example.play.utils.LocalSysUiController
import com.example.play.utils.SystemUiController

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    // This app draws behind the system bars, so we want to handle fitting system windows
    WindowCompat.setDecorFitsSystemWindows(window, true)

    setContent {
      val systemUiController = remember { SystemUiController(window) }

      CompositionLocalProvider(LocalSysUiController provides systemUiController) {
        PlayApp { finish() }
      }
    }
  }
}