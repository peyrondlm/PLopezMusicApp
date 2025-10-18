package com.example.plopezmusicapp.components

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.plopezmusicapp.models.SongModel
import com.example.plopezmusicapp.models.SongViewModel
import com.example.plopezmusicapp.ui.theme.ItemBackground

@Composable
fun AlbumList(
    viewModel: SongViewModel,
    onClick: (SongModel) -> Unit
) {
    LazyRow (
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp)
    ){
        items(items = viewModel.songs){ item ->
            Box (
                modifier = Modifier
                    .width(225.dp)
                    .height(225.dp)
                    .clip(RoundedCornerShape(20))
                    .background(Color.White)
                    .clickable { onClick(item) },
                contentAlignment = Alignment.BottomCenter
            ){
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize(),
                    model = item.image,
                    contentDescription = item.description,
                    contentScale = ContentScale.Crop
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
                    Column (
                        modifier = Modifier.weight(1f)
                    ){
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
}