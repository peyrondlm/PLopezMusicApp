package com.example.plopezmusicapp.services

import com.example.plopezmusicapp.models.SongModel
import retrofit2.http.GET
import retrofit2.http.Path

interface SongService {
    @GET("albums")
    suspend fun getAllSongs() : List<SongModel>
}