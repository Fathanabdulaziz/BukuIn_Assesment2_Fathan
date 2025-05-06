package com.fathan0041.bukuin_assesment2_fathan.navigation

import com.fathan0041.bukuin_assesment2_fathan.ui.screen.KEY_ID_CATATAN

sealed class Screen (val route:String){
    data object  Home: Screen("mainScreen")
    data object FormBaru: Screen("detailScreen")
    data object FormUbah: Screen("detailScreen/{$KEY_ID_CATATAN}") {
        fun withId(id: Long) = "detailScreen/$id"
    }
}