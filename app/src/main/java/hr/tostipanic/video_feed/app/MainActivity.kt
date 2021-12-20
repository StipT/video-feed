package hr.tostipanic.video_feed.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import hr.tostipanic.video_feed.app.theme.AppTheme
import hr.tostipanic.video_feed.features.video_feed.presentation.video_feed_list.VideoFeedScreen
import hr.tostipanic.video_feed.features.video_feed.presentation.video_player.VideoPlayerScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.VideoFeedScreen.route
                    ) {
                        composable(
                            route = Screen.VideoFeedScreen.route
                        ) {
                            VideoFeedScreen(navController)
                        }
                        composable(
                            route = Screen.VideoPlayerScreen.route + "/{videoId}"
                        ) {
                            VideoPlayerScreen()
                        }
                    }
                }
            }
        }
    }
}
