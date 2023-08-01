package com.example.nasadataservice.ui.view

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.nasadataservice.R
import com.example.nasadataservice.domain.model.RoverManifestUiModel

@Composable
fun ManifestList(
    roverManifestUiModelList: List<RoverManifestUiModel>
) {
    Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            items(roverManifestUiModelList.size, itemContent = {
                Manifest(roverManifestUiModel = roverManifestUiModelList[it])
            })
        }
    }
}

@Composable
fun Manifest(
    roverManifestUiModel: RoverManifestUiModel
) {
    Card(modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()
        .clickable {

        }) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = stringResource(id = R.string.sol, roverManifestUiModel.sol))
            Text(text = stringResource(id = R.string.earth_date, roverManifestUiModel.earthDate))
            Text(
                text = stringResource(
                    id = R.string.number_of_photo,
                    roverManifestUiModel.photoNumber
                )
            )
        }
    }
}