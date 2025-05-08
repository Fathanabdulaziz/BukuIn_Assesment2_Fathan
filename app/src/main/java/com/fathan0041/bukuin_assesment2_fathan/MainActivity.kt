package com.fathan0041.bukuin_assesment2_fathan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.fathan0041.bukuin_assesment2_fathan.navigation.SetupNavGraph
import com.fathan0041.bukuin_assesment2_fathan.ui.theme.BukuIn_Assesment2_FathanTheme
import com.fathan0041.bukuin_assesment2_fathan.util.SettingsDataStore


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val dataStore = SettingsDataStore(this)
            val themeId by dataStore.selectedThemeFlow.collectAsState(initial = 0)

            BukuIn_Assesment2_FathanTheme(themeId = themeId) {
                SetupNavGraph()
            }
        }
    }
}