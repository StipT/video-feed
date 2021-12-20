package hr.tostipanic.video_feed.app

sealed class Screen(val route: String) {
    object VideoFeedScreen: Screen("video_feed_screen")
    object VideoPlayerScreen: Screen("video_player_screen")
}
