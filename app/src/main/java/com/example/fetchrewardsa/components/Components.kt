package com.example.fetchrewardsa.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.fetchrewardsa.R

@Composable
fun CircularIndeterminateProgressBar(
    isDisplayed: Boolean
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.primary
    ) {
        if (isDisplayed) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 90.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(125.dp)
                        .padding(20.dp),
                    strokeWidth = 10.dp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }

    }
}


@Composable
fun FetchAppBar(
    elevation: Dp = 0.dp
) {
    val image = if (isSystemInDarkTheme()) {
        painterResource(id = R.drawable.light_fetch)
    } else {
        painterResource(id = R.drawable.dark_fetch)
    }
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primary,
        tonalElevation = elevation,
    ) {
        Row(
            Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = image,
                contentDescription = "Themed Image",
                contentScale = ContentScale.Fit
            )
        }
    }
}


@Composable
fun BackButton(modifier: Modifier) {
    Icon(
        imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
        contentDescription = "Arrow Back",
        tint = MaterialTheme.colorScheme.onBackground,
        modifier = modifier
            .size(40.dp)
            .padding(start = 10.dp)
    )
}