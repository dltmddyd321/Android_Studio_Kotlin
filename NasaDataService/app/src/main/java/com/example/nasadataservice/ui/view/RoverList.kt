package com.example.nasadataservice.ui.view

import android.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nasadataservice.R
import com.example.nasadataservice.domain.model.roverUiModelList
import com.example.nasadataservice.ui.theme.BackgroundCustom

@Composable
@Preview
fun RoverPreview() {
    Rover("Perseverance", R.drawable.perseverance, "18 February 2021", "12.56 km")
}

@Composable
fun RoverList() {
    Surface(color = BackgroundCustom, modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            items(count = roverUiModelList.size, itemContent = { index ->
                Rover(
                    name = roverUiModelList[index].name,
                    img = roverUiModelList[index].image,
                    landingDate = roverUiModelList[index].landingDate,
                    distance = roverUiModelList[index].distance
                )
            })
        }
    }
}

@Composable
fun Rover(name: String, img: Int, landingDate: String, distance: String) {
    Card(modifier = Modifier.padding(16.dp), backgroundColor = MaterialTheme.colors.primaryVariant) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = name,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )
            Image(painter = painterResource(id = img), contentDescription = null)
            Text(text = "Credit : Nasa/JPL", fontSize = 8.sp)
            Text(text = "Landing date : $landingDate", fontSize = 12.sp)
            Text(text = "Distance traveled: $distance", fontSize = 12.sp)
        }
    }
}