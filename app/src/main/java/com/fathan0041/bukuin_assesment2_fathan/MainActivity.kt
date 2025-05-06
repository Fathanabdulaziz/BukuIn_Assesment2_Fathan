package com.fathan0041.bukuin_assesment2_fathan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.fathan0041.bukuin_assesment2_fathan.navigation.SetupNavGraph
import com.fathan0041.bukuin_assesment2_fathan.ui.theme.BukuIn_Assesment2_FathanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BukuIn_Assesment2_FathanTheme{
                SetupNavGraph()
            }
        }
    }
}