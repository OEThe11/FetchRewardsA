package com.example.fetchrewardsa.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fetchrewardsa.database.FetchItemEntity

@Composable
//The Container
fun FetchRewardsCard(fetchItem: FetchItemEntity = FetchItemEntity(4, 56, "ty"), modifier: Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(110.dp)
            .padding(16.dp),
        shape = RoundedCornerShape(size = 20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onBackground
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Column(
                modifier = Modifier.padding(
                    start = 16.dp, top = 10.dp,
                    bottom = 10.dp, end = 10.dp
                )
            ) {
                Text(
                    text = "ListId:",
                    style = MaterialTheme.typography.headlineMedium,
                    fontStyle = FontStyle.Italic
                )
                Text(
                    text = "${fetchItem.listId}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            Column(
                modifier = Modifier.padding(
                    start = 10.dp, top = 10.dp,
                    bottom = 10.dp, end = 16.dp
                )
            ) {
                Text(
                    text = "Name:",
                    modifier = Modifier.align(Alignment.End),
                    style = MaterialTheme.typography.headlineMedium,
                    fontStyle = FontStyle.Italic
                )
                Text(
                    text = fetchItem.name,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }

    }
}