package com.alanvan.gues_the_breed.common

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.alanvan.gues_the_breed.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BreedImagePager(modifier: Modifier, images: List<String>) {
    val pagerState = rememberPagerState { images.size }
    Box(modifier = modifier.wrapContentHeight()) {
        HorizontalPager(
            state = pagerState
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(0.8f)
                    .clip(RoundedCornerShape(12.dp)),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(images[it])
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.image_placeholder),
                contentDescription = "Breed image",
                contentScale = ContentScale.Crop
            )
        }
        HorizontalPagerIndicator(
            modifier = Modifier.align(Alignment.BottomCenter),
            pagerState = pagerState
        )
    }
}
