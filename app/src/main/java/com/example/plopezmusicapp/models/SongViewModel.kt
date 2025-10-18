package com.example.plopezmusicapp.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SongViewModel : ViewModel() {
    var songs by mutableStateOf<List<SongModel>>(emptyList())
        private set

    fun updateSongs(newSongs: List<SongModel>) {
        songs = newSongs
    }

    fun getSongById(id: String): SongModel? {
        return songs.find { it.id == id }
    }
}