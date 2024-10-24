package org.sopt.and.ui.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.model.Top20Video

@Composable
fun Top20VideoContent(
    contentTitle: String,
    top20Videos: List<Top20Video>,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier.padding(bottom = 10.dp)
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
        }
        LazyRow(
            contentPadding = PaddingValues(horizontal = 10.dp),
            modifier = Modifier.height(250.dp)
        ) {
            items(
                items = top20Videos,
                key = { item: Top20Video -> item.videoId }
            ) { top20Videos ->
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Column {
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 5.dp)
                        ) {
                            Image(
                                painter = painterResource(top20Videos.image),
                                contentDescription = "",
                                contentScale = ContentScale.Crop
                            )
                        }
                        Spacer(modifier = Modifier.weight(0.15f))
                    }
                    Text(
                        text = "${top20Videos.rank}",
                        modifier = Modifier.padding(start = 5.dp),
                        fontSize = 60.sp,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    val top20Videos = listOf(
        Top20Video(videoId = 1, rank = 1, image = R.drawable.image1),
        Top20Video(videoId = 2, rank = 2, image = R.drawable.image2),
        Top20Video(videoId = 3, rank = 3, image = R.drawable.image3),
        Top20Video(videoId = 4, rank = 4, image = R.drawable.image4),
        Top20Video(videoId = 5, rank = 5, image = R.drawable.image5),
        Top20Video(videoId = 6, rank = 6, image = R.drawable.image6),
        Top20Video(videoId = 7, rank = 7, image = R.drawable.image1),
        Top20Video(videoId = 8, rank = 8, image = R.drawable.image2),
        Top20Video(videoId = 9, rank = 9, image = R.drawable.image3),
        Top20Video(videoId = 10, rank = 10, image = R.drawable.image4),
        Top20Video(videoId = 11, rank = 11, image = R.drawable.image5),
        Top20Video(videoId = 12, rank = 12, image = R.drawable.image6),
        Top20Video(videoId = 13, rank = 13, image = R.drawable.image1),
        Top20Video(videoId = 14, rank = 14, image = R.drawable.image2),
        Top20Video(videoId = 15, rank = 15, image = R.drawable.image3),
        Top20Video(videoId = 16, rank = 16, image = R.drawable.image4),
        Top20Video(videoId = 17, rank = 17, image = R.drawable.image5),
        Top20Video(videoId = 18, rank = 18, image = R.drawable.image6),
        Top20Video(videoId = 19, rank = 19, image = R.drawable.image1),
        Top20Video(videoId = 20, rank = 20, image = R.drawable.image2),
    )
    Top20VideoContent(
        contentTitle = "오늘의 Top 20",
        top20Videos = top20Videos
    )
}
