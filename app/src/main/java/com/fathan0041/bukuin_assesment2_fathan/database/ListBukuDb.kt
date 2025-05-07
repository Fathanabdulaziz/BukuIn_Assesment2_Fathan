package com.fathan0041.bukuin_assesment2_fathan.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fathan0041.bukuin_assesment2_fathan.model.ListBuku

@Database(entities = [ListBuku::class], version = 1, exportSchema = false)
abstract class ListBukuDb : RoomDatabase(){
    abstract val dao: ListBukuDao
    companion object    {
        @Volatile
        private var INSTANCE: ListBukuDb? = null
        fun getInstance(context: Context): ListBukuDb {
            synchronized(this){
                var instance = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ListBukuDb::class.java,
                        "listBuku.db"
                    ).build()
                    INSTANCE = instance
                }
                return  instance
            }
        }
    }
}