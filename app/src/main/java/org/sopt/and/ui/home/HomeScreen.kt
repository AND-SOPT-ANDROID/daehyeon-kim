package org.sopt.and.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cast
import androidx.compose.material.icons.filled.LiveTv
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import org.sopt.and.model.Top20Video
import org.sopt.and.model.Video
import org.sopt.and.ui.home.component.HomeTextButton
import org.sopt.and.ui.home.component.MainVideoContent
import org.sopt.and.ui.home.component.SubVideoContent
import org.sopt.and.ui.home.component.Top20VideoContent

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val mainVideoList = viewModel.mainVideoList
    val subVideoList = viewModel.subVideoList
    val top20VideoList = viewModel.top20VideoList

    HomeContent(
        modifier = Modifier.padding(),
        mainVideoList = mainVideoList,
        subVideoList = subVideoList,
        top20VideoList = top20VideoList
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeTopAppBar() {
    TopAppBar(
        title = {
            Text(
                text = "Waave",
                color = Color.White,
                fontSize = 25.sp
            )
        },
        actions = {
            IconButton(
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Default.Cast,
                    contentDescription = Icons.Default.Cast.name,
                    tint = Color.White,
                )
            }
            IconButton(
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Default.LiveTv,
                    contentDescription = Icons.Default.Cast.name,
                    tint = Color.White,
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(Color.Black),
        windowInsets = TopAppBarDefaults.windowInsets,
    )
}

@OptIn(ExperimentalFoundationApi::class)
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

        item {
            HomeTopAppBar()
        }

        stickyHeader {
            val categories = listOf("뉴클래식", "드라마", "예능", "영화", "애니", "해외시리즈", "시사교양", "키즈")
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black),
                contentPadding = PaddingValues(
                    vertical = 10.dp,
                    horizontal = 15.dp,
                )
            ) {
                items(categories) { categories ->
                    HomeTextButton(text = categories, onClick = {})
                }
            }
        }

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

        item {
            Spacer(modifier = Modifier.height(120.dp))
        }
    }
}

@Preview
@Composable
private fun PreviewHomeScreen() {
    HomeScreen()
}
