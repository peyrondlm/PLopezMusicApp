package com.example.plopezmusicapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.plopezmusicapp.models.SongModel
import com.example.plopezmusicapp.ui.theme.StrongPurple

@Composable
fun MiniPlayer(
    album: SongModel?,
    modifier: Modifier = Modifier
) {
    if (album == null) return

    var isPlaying by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(StrongPurple)
            .padding(horizontal = 15.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = album.image,
            contentDescription = album.title,
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(15.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(10.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = album.title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = Color.White
            )
            Text(
                text = album.artist,
                color = Color.Gray,
                fontSize = 14.sp
            )
        }

        IconButton(
            onClick = { isPlaying = !isPlaying },
            modifier = Modifier
                .size(60.dp)
        ) {
            Icon(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.White)
                    .padding(10.dp),
                imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                contentDescription = null,
                tint = Color.Black
            )
        }
    }
}
