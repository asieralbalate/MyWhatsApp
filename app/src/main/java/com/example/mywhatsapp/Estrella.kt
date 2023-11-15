package com.example.mywhatsapp

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale

@Composable
fun Estrella() {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        AnimatedVectorDrawable4()
    }
}
@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun AnimatedVectorDrawable4(){
    val image =
        AnimatedImageVector.animatedVectorResource(R.drawable.icono_estrella
        )
    var atEnd by remember { mutableStateOf(false) }
    Image(
        painter = rememberAnimatedVectorPainter(image, atEnd),
        contentDescription = "VectorDrawable",
        modifier = Modifier
            .clickable {
                atEnd = !atEnd
            },
        contentScale = ContentScale.Crop,
    )
}
