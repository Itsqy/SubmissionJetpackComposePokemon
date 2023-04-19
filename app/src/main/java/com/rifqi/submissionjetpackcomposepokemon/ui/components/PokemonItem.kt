package com.rifqi.submissionjetpackcomposepokemon.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun PokemonItem(
    title: String,
    photo: String,
    type: String,
    modifier: Modifier
) {

    Row(
        modifier = modifier
    ) {

        AsyncImage(
            model = photo,
            contentDescription = title,
            modifier = Modifier
                .size(200.dp, 180.dp)
                .clip(RoundedCornerShape(4.dp))
        )

        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.weight(1f),

            ) {
            Text(
                text = "Name : $title",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )
            Text(text = "type : $type")
        }

    }


}

