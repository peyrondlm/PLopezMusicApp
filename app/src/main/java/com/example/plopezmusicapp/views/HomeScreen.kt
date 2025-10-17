package com.example.plopezmusicapp.views

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.Image
import coil3.compose.AsyncImage
import com.example.plopezmusicapp.models.SongViewModel
import com.example.plopezmusicapp.services.SongService
import com.example.plopezmusicapp.ui.theme.GreetingGradientBottom
import com.example.plopezmusicapp.ui.theme.GreetingGradientTop
import com.example.plopezmusicapp.ui.theme.HomeScreenGradientTop
import com.example.plopezmusicapp.ui.theme.ItemBackground
import com.example.plopezmusicapp.ui.theme.PLopezMusicAppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import okhttp3.internal.connection.RouteDatabase
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
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(HomeScreenGradientTop, Color.White),
                    start = Offset(0f, 0f),
                    end = Offset(0f, 3000f)
                )
            )
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp)
                .clip(RoundedCornerShape(35.dp))
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(GreetingGradientBottom, GreetingGradientTop),
                        start = Offset(0f, 0f),
                        end = Offset(0f, 3000f)
                    )
                )
                .padding(30.dp)
        ){
            Column {
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = null,
                        tint = Color.White
                    )
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
                Column {
                    Text(
                        text = "Good Morning!",
                        color = Color.White
                    )
                    Text(
                        text = "Pedro López",
                        color = Color.White
                    )
                }
            }
        }
        // Texto de álbums y ver más
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "Albums"
            )
            Text(
                text = "See more"
            )
        }
        // Lista de álbumes
        LazyRow (
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
        ){
            items(items = viewModel.songs){ item ->
                Box (
                    modifier = Modifier
                        .width(250.dp)
                        .height(175.dp)
                        .clip(RoundedCornerShape(30))
                        .background(Color.White),
                    contentAlignment = Alignment.BottomCenter
                ){
                    AsyncImage(
                        model = item.image,
                        contentDescription = item.description,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp, bottom = 16.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(ItemBackground.copy(alpha = 0.6f))
                            .padding(horizontal = 10.dp, vertical = 6.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Column {
                            Text(
                                text = item.title,
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = item.artist,
                                color = Color.LightGray
                            )
                        }
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = null,
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(Color.White)
                                .height(35.dp)
                                .width(35.dp)
                        )
                    }
                }
            }
        }
        // Texto de reproducido recientemente y ver más
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 30.dp, bottom = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "Recently Played"
            )
            Text(
                text = "See more"
            )
        }
        // Lista de reproducido recientemente
        LazyColumn (
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
        ){
            items(items = viewModel.songs) { item ->
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp)
                        .height(80.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.White)
                        .padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    AsyncImage(
                        model = item.image,
                        contentDescription = item.description,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(RoundedCornerShape(15.dp))
                            .size(80.dp)
                    )
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Column {
                            Text(
                                text = item.title,
                                color = Color.Black,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = "${item.artist} • Popular Song",
                                color = Color.Gray,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = null,
                            tint = Color.Gray
                        )
                    }
                }
            }
        }
    }
}