package org.sopt.and.ui.home

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import org.sopt.and.R
import org.sopt.and.model.Top20Video
import org.sopt.and.model.Video

class HomeViewModel : ViewModel() {

    private val _mainVideoList = mutableStateListOf(
        Video(videoId = 1, image = R.drawable.image1),
        Video(videoId = 2, image = R.drawable.image2),
        Video(videoId = 3, image = R.drawable.image3),
        Video(videoId = 4, image = R.drawable.image4),
        Video(videoId = 5, image = R.drawable.image5),
        Video(videoId = 6, image = R.drawable.image6),
    )
    val mainVideoList: List<Video> = _mainVideoList

    private val _subVideoList = mutableStateListOf(
        Video(videoId = 1, image = R.drawable.image1),
        Video(videoId = 2, image = R.drawable.image2),
        Video(videoId = 3, image = R.drawable.image3),
        Video(videoId = 4, image = R.drawable.image4),
        Video(videoId = 5, image = R.drawable.image5),
        Video(videoId = 6, image = R.drawable.image6),
    )
    val subVideoList: List<Video> = _subVideoList

    private val _top20VideoList = mutableStateListOf(
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
    val top20VideoList: List<Top20Video> = _top20VideoList

}
