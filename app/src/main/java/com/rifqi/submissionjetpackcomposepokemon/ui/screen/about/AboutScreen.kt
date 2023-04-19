package com.rifqi.submissionjetpackcomposepokemon.ui.screen.about

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.rifqi.submissionjetpackcomposepokemon.R
import com.rifqi.submissionjetpackcomposepokemon.ui.theme.SubmissionJetpackComposePokemonTheme

@Composable
fun AboutScreen() {

    Column( horizontalAlignment = Alignment.CenterHorizontally, modifier  = Modifier) {
        AsyncImage(model = stringResource(R.string.url_image), contentDescription = null,Modifier.fillMaxWidth().clip(
            RoundedCornerShape(16.dp)
        ))
        Text(text = stringResource(R.string.my_name), fontWeight = FontWeight.Bold )
        Text(text = stringResource(R.string.my_email))

    }

}

@Preview
@Composable
fun About() {

 SubmissionJetpackComposePokemonTheme() {
     AboutScreen()

 }

}