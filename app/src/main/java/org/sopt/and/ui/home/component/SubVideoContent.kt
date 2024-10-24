package org.sopt.and.ui.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.model.Video

@Composable
fun SubVideoContent(
    contentTitle: String,
    subVideos: List<Video>,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier.padding(bottom = 20.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp, horizontal = 15.dp)
        ) {
            Text(
                text = contentTitle,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                contentDescription = Icons.AutoMirrored.Filled.ArrowForwardIos.name,
                tint = Color.Gray
            )
        }
        LazyRow(
            contentPadding = PaddingValues(horizontal = 10.dp),
            modifier = Modifier.height(150.dp)
        ) {
            items(
                items = subVideos,
                key = { item -> item.videoId }
            ) { subVideo ->
                Surface(
                    modifier = Modifier.padding(horizontal = 5.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Image(
                        painter = painterResource(subVideo.image),
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewVideoContent() {
    val subVideos = listOf(
        Video(videoId = 1, image = R.drawable.image1),
        Video(videoId = 2, image = R.drawable.image2),
        Video(videoId = 3, image = R.drawable.image3),
        Video(videoId = 4, image = R.drawable.image4),
        Video(videoId = 5, image = R.drawable.image5),
        Video(videoId = 6, image = R.drawable.image6),
    )

    SubVideoContent(
        contentTitle = "믿고 보는 웨이브 에디터 추천작",
        subVideos = subVideos
    )
}
