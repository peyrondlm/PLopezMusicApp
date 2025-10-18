package com.example.plopezmusicapp.views

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.plopezmusicapp.components.AlbumList
import com.example.plopezmusicapp.components.Greeting
import com.example.plopezmusicapp.components.RecentlyPlayed
import com.example.plopezmusicapp.components.SeeMoreLabel
import com.example.plopezmusicapp.models.SongViewModel
import com.example.plopezmusicapp.services.SongService
import com.example.plopezmusicapp.ui.theme.HomeScreenGradientTop
import com.example.plopezmusicapp.ui.theme.SongDetailScreenRoute
import com.example.plopezmusicapp.ui.theme.StrongPurple
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: SongViewModel
) {
    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(true) {
        try {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://music.juanfrausto.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(SongService::class.java)
            val songs = service.getAllSongs()

            viewModel.updateSongs(songs)
            loading = false
        } catch (e: Exception) {
            loading = false
            Log.e("HomeScreen", "Error cargando canciones: ${e.message}")
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(HomeScreenGradientTop, Color.White),
                    start = Offset(0f, 0f),
                    end = Offset(0f, 3000f)
                )
            )
    ) {
        if (loading) {
            // Loading
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = StrongPurple
            )
        } else {
            // Contenido principal
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // Greeting
                Greeting()
                // Texto de álbums y ver más
                SeeMoreLabel("Albums")
                // Lista de álbumes
                AlbumList(
                    viewModel = viewModel,
                    onClick = { song ->
                        song.id.let { id ->
                            navController.navigate(SongDetailScreenRoute(id = id))
                        }
                    }
                )
                // Texto de reproducido recientemente y ver más
                SeeMoreLabel("Recently Played")
                // Lista de reproducido recientemente
                RecentlyPlayed(
                    viewModel = viewModel,
                    onClick = { song ->
                        song.id.let { id ->
                            navController.navigate(SongDetailScreenRoute(id = id))
                        }
                    }
                )
            }
        }
    }
}
