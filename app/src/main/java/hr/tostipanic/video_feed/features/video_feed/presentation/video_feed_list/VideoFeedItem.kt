package hr.tostipanic.video_feed.features.video_feed.presentation.video_feed_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import hr.tostipanic.video_feed.features.video_feed.domain.model.Video

@Composable
fun VideoFeedItem(
    videoPost: Video,
    onItemClick: (Video) -> Unit,
    index: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clickable { onItemClick(videoPost) }
            .padding(20.dp)
            .testTag("feedItem${index}"),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Column(
            modifier = Modifier
                .weight(2f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${videoPost.description}",
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp,
                ),
                overflow = TextOverflow.Ellipsis,
                maxLines = 3,
                modifier = Modifier.weight(2f).testTag("feedItemTitle${index}")
            )
            Text(
                text = "Views: ${videoPost.views}",
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 10.dp)
                    .weight(2f).testTag("feedItemViews${index}"),
                maxLines = 2,
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    fontStyle = FontStyle.Italic,
                ),
                overflow = TextOverflow.Ellipsis
            )
        }
        Image(
            painter = rememberImagePainter(data = videoPost.poster),
            contentDescription = "thumbnail",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .weight(2f).testTag("feedItemImage${index}"),
        )


    }
}
