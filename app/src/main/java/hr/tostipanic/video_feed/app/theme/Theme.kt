package hr.tostipanic.video_feed.app

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray

private val colorPalette = darkColors(
    primary = Color.Red,
    background = DarkGray,
    onBackground = Color.White,
    onPrimary = DarkGray
)


@Composable
fun AppTheme(content: @Composable() () -> Unit) {
    val colors = colorPalette

    MaterialTheme(
        colors = colors,
        typography = Typography,
        content = content
    )
}