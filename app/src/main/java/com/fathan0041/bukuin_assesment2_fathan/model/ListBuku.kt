package com.fathan0041.bukuin_assesment2_fathan.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "listBuku")
data class ListBuku(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val judulBuku: String,
    val kategori: String,
    val harga: String,
    val catatan: String,
    val tanggal: String
)
