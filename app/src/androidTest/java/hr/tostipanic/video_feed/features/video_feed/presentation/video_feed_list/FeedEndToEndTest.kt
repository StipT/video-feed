package hr.tostipanic.video_feed.features.video_feed.presentation.video_feed_list

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import hr.tostipanic.video_feed.app.MainActivity
import hr.tostipanic.video_feed.app.Screen
import hr.tostipanic.video_feed.app.theme.AppTheme
import hr.tostipanic.video_feed.di.AppModule
import hr.tostipanic.video_feed.features.video_feed.presentation.video_player.VideoPlayerScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class FeedEndToEndTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @ExperimentalAnimationApi
    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.setContent {
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

    @Test
    fun clickOnFeedItem_navigateToVideoPlayerScreen() {
        composeRule.onRoot(useUnmergedTree = true).printToLog("TAG")
        composeRule.onNodeWithTag("feedLazyColumn").assertIsEnabled()
        Thread.sleep(3000)
        composeRule.onNodeWithTag("feedItem1").assertIsDisplayed()
        composeRule.onNodeWithTag("feedItem1").performClick()
        composeRule
            .onNodeWithTag("videPlayer")
            .assertIsDisplayed()

    }
}