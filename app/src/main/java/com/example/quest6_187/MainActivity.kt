package com.example.quest6_187

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.quest6_187.ui.theme.Quest6_187Theme
import com.example.quest6_187.uicontroller.SiswaApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Quest6_187Theme {
                SiswaApp()
            }
        }
    }
}
