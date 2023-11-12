package com.example.mywhatsapp

import android.annotation.SuppressLint
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.mywhatsapp.ui.theme.VerdeNormal
import com.example.mywhatsapp.ui.theme.VerdeOscuro
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Portada() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        rememberTopAppBarState()
    )
    Scaffold(
        topBar = { MyTopBar(scrollBehavior) },
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        floatingActionButton = {
            MyFAB()
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding())
            ) {
                MyTabs()
            }
        }
    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(scrollBehavior: TopAppBarScrollBehavior) {
    var showMenu by remember { mutableStateOf(false) }
    TopAppBar(
        title = { Text(text = "MyWhatsApp", color = Color.White) },
        colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = VerdeNormal),
        actions = {
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null,
                    tint = Color.White
                )
            }
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@Composable
fun MyFAB() {
    FloatingActionButton(
        onClick = { /*TODO*/ },
        containerColor = VerdeOscuro,
        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(),
        modifier = Modifier.size(60.dp)
    ) {
        AnimatedVectorDrawable()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyTabs() {
    val scope = rememberCoroutineScope()
    val titles = listOf("Chats", "Novedades", "Llamadas")
    val pagerState = rememberPagerState(initialPage = 0, initialPageOffsetFraction = 0f){3}
    Column {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            containerColor = VerdeNormal,
            contentColor = Color.White
        ) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = { scope.launch { pagerState.animateScrollToPage(page = index) } },
                    text = { Text(text = title) }
                )
            }
        }
        HorizontalPager(state = pagerState) { page ->
            when (page) {
                0 -> Chats()
                1 -> Novedades()
                2 -> Llamadas()
            }
        }
    }
}

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun AnimatedVectorDrawable(){
    val image =
        AnimatedImageVector.animatedVectorResource(R.drawable.icono_animado
        )
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





