package com.example.plopezmusicapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.plopezmusicapp.models.SongViewModel
import com.example.plopezmusicapp.ui.theme.HomeScreenRoute
import com.example.plopezmusicapp.ui.theme.PLopezMusicAppTheme
import com.example.plopezmusicapp.ui.theme.SongDetailScreenRoute
import com.example.plopezmusicapp.views.AlbumDetail
import com.example.plopezmusicapp.views.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val songViewModel: SongViewModel = viewModel()
            PLopezMusicAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = HomeScreenRoute
                    ){
                        composable<HomeScreenRoute> {
                            HomeScreen(
                                navController,
                                viewModel = songViewModel
                            )
                        }
                        composable<SongDetailScreenRoute> { backStack ->
                            val args = backStack.toRoute<SongDetailScreenRoute>()
                            AlbumDetail(id = args.id, viewModel = songViewModel)
                        }
                    }
                }
            }
        }
    }
}