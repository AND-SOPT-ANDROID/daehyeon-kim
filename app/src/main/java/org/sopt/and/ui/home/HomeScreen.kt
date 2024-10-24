package org.sopt.and.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.and.model.Top20Video
import org.sopt.and.model.Video
import org.sopt.and.ui.home.component.HomeTextButton
import org.sopt.and.ui.home.component.MainVideoContent
import org.sopt.and.ui.home.component.SubVideoContent
import org.sopt.and.ui.home.component.Top20VideoContent

@Composable
fun HomeScreen(
    padding: PaddingValues,
    viewModel: HomeViewModel = viewModel()
) {
    val mainVideoList = viewModel.mainVideoList
    val subVideoList = viewModel.subVideoList
    val top20VideoList = viewModel.top20VideoList

    Scaffold(
        modifier = Modifier.padding(padding),
        topBar = { HomeTopAppBar() },
        containerColor = Color.Black,
        content = { innerPadding ->
            HomeContent(
                modifier = Modifier.padding(innerPadding),
                mainVideoList = mainVideoList,
                subVideoList = subVideoList,
                top20VideoList = top20VideoList
            )
        }
    )
}

@Composable
fun HomeTopAppBar() {
    val categories = listOf("뉴클래식", "드라마", "예능", "영화", "애니", "해외시리즈", "시사교양", "키즈")

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
             items(categories) { categories ->
                 HomeTextButton(text = categories, onClick = {})
             }
        }
    }
}

@Composable
private fun HomeContent(
    modifier: Modifier,
    mainVideoList: List<Video>,
    subVideoList: List<Video>,
    top20VideoList: List<Top20Video>
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        item { MainVideoContent(mainVideoList = mainVideoList) }

        item {
            SubVideoContent(
                contentTitle = "믿고 보는 웨이브 에디터 추천작",
                subVideos = subVideoList
            )
        }

        item {
            Top20VideoContent(
                contentTitle = "오늘의 TOP 20",
                top20Videos = top20VideoList
            )
        }

        item {
            SubVideoContent(
                contentTitle = "실시간 인기 콘텐츠",
                subVideos = subVideoList
            )
        }
    }
}

@Preview
@Composable
private fun PreviewHomeScreen() {
    HomeScreen(padding = PaddingValues())
}
