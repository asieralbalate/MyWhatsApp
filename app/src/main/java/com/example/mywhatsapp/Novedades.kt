package com.example.mywhatsapp

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.mywhatsapp.ui.theme.VerdeOscuro

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun Novedades() {
    Column(modifier = Modifier.fillMaxSize()) {
        FloatingActionButton(
            onClick = { /*TODO*/ },
            containerColor = VerdeOscuro,
            elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(),
            modifier = Modifier.size(60.dp)
        ) {
            val image =
                AnimatedImageVector.animatedVectorResource(R.drawable.icono_trans)
            var atEnd by remember { mutableStateOf(false) }
            Image(
                painter = rememberAnimatedVectorPainter(image, atEnd),
                contentDescription = "VectorDrawable",
                modifier = Modifier.size(32.dp).clickable {
                    atEnd = !atEnd
                },
                contentScale = ContentScale.Crop,
            )
        }
    }
}