package org.sopt.and.ui.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.model.Video

@Composable
fun MainVideoContent(
    mainVideoList: List<Video>,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(pageCount = { mainVideoList.size })

    Column(
        modifier = modifier
            .padding(vertical = 15.dp)
            .height(450.dp)
    ) {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 20.dp),
            pageSpacing = 10.dp,
        ) { index ->
            Surface(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxSize(),
            ) {
                Image(
                    painter = painterResource(mainVideoList[index].image),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
                Box(
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Surface(
                        color = Color.Black,
                        shape = RoundedCornerShape(15.dp),
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(color = Color.White)) {
                                    append("${pagerState.currentPage + 1}")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.Gray,
                                        letterSpacing = 4.sp
                                    )
                                ) {
                                    append("|")
                                }
                                withStyle(style = SpanStyle(color = Color.Gray)) {
                                    append("${pagerState.pageCount}")
                                }
                            },
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreView() {
    val imageList = listOf(
        Video(videoId = 1, image = R.drawable.image1),
        Video(videoId = 2, image = R.drawable.image2),
        Video(videoId = 3, image = R.drawable.image3),
        Video(videoId = 4, image = R.drawable.image4),
        Video(videoId = 5, image = R.drawable.image5),
        Video(videoId = 6, image = R.drawable.image6),
    )

    MainVideoContent(mainVideoList = imageList,)
}
