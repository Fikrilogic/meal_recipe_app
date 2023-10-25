package com.fikrisandi.recipeapp.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var backPressed = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            com.fikrisandi.recipeapp.main.MainRoot {
                finishApplication
            }
        }
    }

    private val finishApplication: () -> Unit = {
        if (backPressed + 3000 > System.currentTimeMillis()) {
            finishAndRemoveTask()
        } else {
            Toast.makeText(this, "Press Back Again For Exit", Toast.LENGTH_SHORT)
        }
        backPressed = System.currentTimeMillis()
    }
}
