package com.fathan0041.bukuin_assesment2_fathan.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fathan0041.bukuin_assesment2_fathan.database.ListBukuDao
import com.fathan0041.bukuin_assesment2_fathan.model.ListBuku
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class MainViewModel(dao: ListBukuDao):ViewModel() {

    val data: StateFlow<List<ListBuku>> = dao.getListBuku().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList()
    )

}