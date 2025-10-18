package com.example.plopezmusicapp.services

import com.example.plopezmusicapp.models.SongModel
import retrofit2.http.GET

interface SongService {
    @GET("albums")
    suspend fun getAllSongs() : List<SongModel>
}