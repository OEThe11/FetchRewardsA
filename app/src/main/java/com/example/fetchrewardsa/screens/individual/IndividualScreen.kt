package com.example.fetchrewardsa.screens.individual

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fetchrewardsa.data.Resource
import com.example.fetchrewardsa.database.FetchItemEntity


@Composable
fun IndividualItemScreen(itemId: Int, navController: NavController,
                         viewModel: IndividualItemViewModel = hiltViewModel()) {

LaunchedEffect(itemId) {
    viewModel.fetchEntityById(itemId)
}

    val entity by viewModel.item.collectAsState()
    
    when (entity){
        is Resource.Loading -> {
            CircularProgressIndicator()
        }
        is Resource.Success -> {
          entity.data.let { item ->
              Surface(
                  modifier = Modifier
                      .fillMaxSize(),
                  color = MaterialTheme.colorScheme.primary
              ) {

                  Column(
                      verticalArrangement = Arrangement.Center,
                      horizontalAlignment = Alignment.CenterHorizontally
                  ) {
                      Card(
                          modifier = Modifier
                              .size(width = 300.dp, height = 250.dp)
                              .padding(10.dp),
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
                                      text = "${item?.listId}",
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
                                  item?.name?.let {
                                      Text(
                                          text = it,
                                          style = MaterialTheme.typography.bodyMedium,
                                          fontWeight = FontWeight.Bold
                                      )
                                  }
                              }
                          }
                      }

                  }

              }
              
          } 
        }
        is Resource.Error -> {
            Text(text = "Error ${(entity as Resource.Error).message}")
        }
    }

}

