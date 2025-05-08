package com.fathan0041.bukuin_assesment2_fathan.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fathan0041.bukuin_assesment2_fathan.database.ListBukuDao
import com.fathan0041.bukuin_assesment2_fathan.model.ListBuku
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DetailViewModel (private val dao:ListBukuDao): ViewModel(){

    private val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
    fun insert(judulBuku: String,kategori: String,harga: String, isi: String){
        val listBuku = ListBuku(
            tanggal = formatter.format(Date()),
            judulBuku = judulBuku,
            kategori = kategori,
            harga = harga,
            catatan = isi
        )
        viewModelScope.launch(Dispatchers.IO){
            dao.insert(listBuku)
        }
    }
    suspend fun getListBuku(id: Long):ListBuku? {
        return dao.getListBukuById(id)
    }

    fun update (id: Long, judulBuku: String,kategori: String,harga: String, isi: String){
        val listBuku = ListBuku(
            id = id,
            tanggal = formatter.format(Date()),
            judulBuku = judulBuku,
            kategori = kategori,
            harga = harga,
            catatan = isi
        )
        viewModelScope.launch(Dispatchers.IO){
            dao.update(listBuku)
        }
    }
    fun  delete (id: Long){
        viewModelScope.launch(Dispatchers.IO){
            dao.deleteById(id)
        }
    }
}