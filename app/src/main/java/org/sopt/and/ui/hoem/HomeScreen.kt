package org.sopt.and.ui.hoem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cast
import androidx.compose.material.icons.filled.LiveTv
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.ui.hoem.component.MainVideoContent
import org.sopt.and.ui.hoem.component.SubVideoContent
import org.sopt.and.ui.hoem.component.Top20VideoContent

@Composable
fun HomeScreen(
    padding: PaddingValues
) {
    Scaffold(
        modifier = Modifier.padding(padding),
        topBar = { HomeTopAppBar() },
        containerColor = Color.Black,
        content = { innerPadding ->
            HomeContent(modifier = Modifier.padding(innerPadding))
        }
    )
}

@Composable
fun HomeTopAppBar() {
    Column(
        verticalArrangement = Arrangement.spacedBy(15.dp),
        modifier = Modifier.padding(vertical = 10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        ) {
            Text(
                text = "Waave",
                color = Color.White,
                fontSize = 25.sp
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(18.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Default.Cast,
                    contentDescription = Icons.Default.Cast.name,
                    tint = Color.White
                )
                Icon(
                    imageVector = Icons.Default.LiveTv,
                    contentDescription = Icons.Default.Cast.name,
                    tint = Color.White
                )
            }
        }
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 15.dp)
        ) {
            item {
                HomeTextButton("뉴클래식")
                HomeTextButton("드라마")
                HomeTextButton("예능")
                HomeTextButton("영화")
                HomeTextButton("애니")
                HomeTextButton("해외시리즈")
                HomeTextButton("시사교양")
                HomeTextButton("키즈")
            }
        }
    }
}

@Composable
private fun HomeContent(
    modifier: Modifier
) {

    val imageList = listOf(
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image3,
        R.drawable.image4,
        R.drawable.image5,
        R.drawable.image6,
    )

    val pagerState = rememberPagerState(
        pageCount = { imageList.size }
    )

    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        item {
            MainVideoContent(
                imageList = imageList,
                pagerState = pagerState
            )
        }

        item {
            SubVideoContent(
                contentTitle = "믿고 보는 웨이브 에디터 추천작",
                imageList = imageList
            )
        }

        item {
            Top20VideoContent(
                contentTitle = "오늘의 TOP 20",
                imageList = imageList
            )
        }

        item {
            SubVideoContent(
                contentTitle = "실시간 인기 콘텐츠",
                imageList = imageList
            )
        }
    }
}

@Composable
fun HomeTextButton(
    text: String,
    onClick: () -> Unit = {}
) {
    Text(
        text = text,
        fontSize = 15.sp,
        color = Color.Gray,
        modifier = Modifier
            .padding(horizontal = 6.dp)
            .clickable { onClick() }
    )
}

@Preview
@Composable
private fun PreviewHomeScreen() {
    HomeScreen(
        padding = PaddingValues()
    )
}
