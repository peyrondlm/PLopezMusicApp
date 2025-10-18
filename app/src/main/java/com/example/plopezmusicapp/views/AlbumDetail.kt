package com.example.plopezmusicapp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.plopezmusicapp.models.SongViewModel
import com.example.plopezmusicapp.ui.theme.GreetingGradientBottom
import com.example.plopezmusicapp.ui.theme.GreetingGradientTop
import com.example.plopezmusicapp.ui.theme.HomeScreenGradientTop
import com.example.plopezmusicapp.ui.theme.StrongPurple

@Composable
fun AlbumDetail(
    id: String,
    viewModel: SongViewModel = viewModel()
) {
    val album = viewModel.getSongById(id)
    Column(
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
        // Card del album
        Box(
            modifier = Modifier
                .height(350.dp)
                .fillMaxWidth()
                .padding(top = 25.dp, start = 25.dp, end = 25.dp)
                .clip(RoundedCornerShape(30.dp))
        ) {
            AsyncImage(
                model = album?.image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .matchParentSize()
                    .clip(RoundedCornerShape(30.dp))
            )
            Box (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ){
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    // Parte superior
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(Color.Black.copy(0.4f))
                                .padding(6.dp),
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                        Icon(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(Color.Black.copy(0.4f))
                                .padding(6.dp),
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }

                    // Parte inferior
                    Column {
                        Text(
                            text = album?.title ?: "Desconocido",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                        Text(
                            text = album?.artist ?: "Desconocido",
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                        Row(
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Icon(
                                modifier = Modifier
                                    .padding(start = 5.dp)
                                    .clip(CircleShape)
                                    .background(
                                        brush = Brush.linearGradient(
                                            colors = listOf(
                                                GreetingGradientBottom,
                                                GreetingGradientTop
                                            ),
                                            start = Offset(100f, 0f),
                                            end = Offset(0f, 0f)
                                        )
                                    )
                                    .padding(14.dp),
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = null,
                                tint = Color.White
                            )
                            Icon(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(Color.White)
                                    .padding(14.dp),
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = null,
                                tint = Color.Black
                            )
                        }
                    }
                }
            }
        }
        // Card de información
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 25.dp, start = 25.dp, end = 25.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
                .padding(10.dp)
        ) {
            Text(
                text = "About this album",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                color = StrongPurple
            )
            Text(
                text = album?.description ?: "Desconocida",
                color = Color.Gray,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium
            )
        }
        // Card de artista
        Row(
            modifier = Modifier
                .padding(top = 15.dp, start = 25.dp, end = 25.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(Color.White)
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Artist: ",
                fontWeight = FontWeight.SemiBold,
                color = StrongPurple
            )
            Text(
                text = album?.artist ?: "Desconocido",
                color = Color.Gray,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        ) {
            items(10) { i ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp, vertical = 10.dp)
                        .clip(RoundedCornerShape(25.dp))
                        .background(Color.White)
                        .padding(start = 10.dp, end = 25.dp, bottom = 10.dp, top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        AsyncImage(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .height(60.dp)
                                .width(60.dp),
                            model = album?.image ?: "Sin imagen",
                            contentDescription = null,
                            contentScale = ContentScale.Fit
                        )
                        Column (
                            modifier = Modifier
                                .padding(start = 10.dp)
                        ){
                            Text(
                                text = "${album?.title ?: "Desconocido"} • Track ${i + 1}",
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = album?.artist ?: "Desconocido",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Gray
                            )
                        }
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