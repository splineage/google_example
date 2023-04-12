package com.onethefull.compose_test

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2023/04/11 6:32 PM
 * @desc
 */

@Composable
fun PuppyListItem(puppy: Puppy, navigateToProfile: (Puppy) -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Row(
            Modifier.clickable { navigateToProfile(puppy) }
        ) {
            PuppyImage(puppy = puppy)
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = puppy.title, style = typography.titleLarge)
                Text(text = "VIEW DETAIL", style = typography.bodyMedium)
            }
        }
    }
}

@Composable
private fun PuppyImage(puppy: Puppy) {
    Image(
        painter = painterResource(id = puppy.puppyImageId),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(84.dp)
            .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
        )
}

@Preview
@Composable
fun PreviewPuppyItem() {
    val puppy = DataProvider.puppyList
    PuppyListItem(puppy = puppy[0], navigateToProfile = { })
}