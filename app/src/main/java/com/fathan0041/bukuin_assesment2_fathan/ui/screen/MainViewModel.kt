package com.fathan0041.bukuin_assesment2_fathan.ui.screen

import androidx.lifecycle.ViewModel
import com.fathan0041.bukuin_assesment2_fathan.model.ListBuku

class MainViewModel: ViewModel(){
    val data = listOf(
        ListBuku(
            1,
            "Kuliah Mobpro 17 Feb",
            "akademi",
            "Kuliah hari pertama. Ternyata keren juga yang mau dipelajari. ",
            "Rp 100000",
            "2025-02-17 12:34:56"
        ),
        ListBuku(
            2,
            "Kuliah Mobpro 19 Feb",
            "akademi",
            "Kuliah hari pertama. Ternyata keren juga yang mau dipelajari. ",
            "Rp 100000",
            "2025-02-19 12:34:56"
        ),
        ListBuku(
            3,
            "Kuliah Mobpro 24 Feb",
            "akademi",
            "Kuliah hari pertama. Ternyata keren juga yang mau dipelajari. ",
            "Rp 100000",
            "2025-02-24 12:34:56"
        ),
        ListBuku(
            4,
            "Kuliah Mobpro 26 Feb",
            "akademi",
            "Kuliah hari pertama. Ternyata keren juga yang mau dipelajari. ",
            "Rp 100000",
            "2025-02-26 12:34:56"
        ),
        ListBuku(
            5,
            "Kuliah Mobpro 03 Mar",
            "akademi",
            "Kuliah hari pertama. Ternyata keren juga yang mau dipelajari. ",
            "Rp 100000",
            "2025-03-03 12:34:56"
        ),
        ListBuku(
            6,
            "Kuliah Mobpro 05 Mar",
            "akademi",
            "Kuliah hari pertama. Ternyata keren juga yang mau dipelajari. ",
            "Rp 100000",
            "2025-03-05 12:34:56"
        )
    )
    fun getListBuku(id: Long): ListBuku?{
        return data.find { it.id == id }
    }
}