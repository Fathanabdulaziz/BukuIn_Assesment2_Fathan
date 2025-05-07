package com.fathan0041.bukuin_assesment2_fathan.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.fathan0041.bukuin_assesment2_fathan.model.ListBuku
import kotlinx.coroutines.flow.Flow

@Dao
interface ListBukuDao {
    @Insert
    suspend fun insert(listBuku: ListBuku)
    @Update
    suspend fun update(listBuku: ListBuku)
    @Query("SELECT * FROM listBuku ORDER BY tanggal DESC")
    fun getListBuku(): Flow<List<ListBuku>>
    @Query("SELECT * FROM listBuku WHERE id = :id")
    suspend fun getListBukuById(id: Long): ListBuku?
    @Query("DELETE FROM listBuku WHERE id = :id")
    suspend fun  deleteById(id: Long)
}