package com.example.plopezmusicapp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.plopezmusicapp.ui.theme.GreetingGradientBottom
import com.example.plopezmusicapp.ui.theme.GreetingGradientTop
import com.example.plopezmusicapp.ui.theme.HomeScreenGradientTop
import com.example.plopezmusicapp.ui.theme.PLopezMusicAppTheme

@Composable
fun HomeScreen(
    navController: NavController
) {
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
        Row {

        }
        // Texto de reproducido recientemente y ver más
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
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
        LazyColumn {

        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    PLopezMusicAppTheme {
        HomeScreen()
    }
}