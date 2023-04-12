package com.onethefull.compose_test

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2023/04/12 10:48 AM
 * @desc
 */

@Composable
fun ProfileScreen(puppy: Puppy, onNavIconPressed: () -> Unit = {}){
    val scrollState = rememberScrollState()
    Column(modifier = Modifier.fillMaxSize()) {
        BoxWithConstraints(modifier = Modifier.weight(1f)) {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                ) {
                    ProfileHeader(
                        scrollState = scrollState,
                        puppy = puppy,
                        containerHeight = this@BoxWithConstraints.maxHeight
                    )
                    ProfileContent(puppy = puppy, containerHeight = this@BoxWithConstraints.maxHeight)
                }
            }

        }
    }
}

@Composable
private fun ProfileHeader(
    scrollState: ScrollState,
    puppy: Puppy,
    containerHeight: Dp
){
    val offset = (scrollState.value / 2)
    val offsetDp = with(LocalDensity.current){ offset.toDp() }
    Image(
        modifier = Modifier
            .heightIn(max = containerHeight / 2)
            .fillMaxWidth()
            .padding(top = offsetDp),
        painter = painterResource(id = puppy.puppyImageId),
        contentScale = ContentScale.Crop,
        contentDescription = null
    )
}

@Composable
private fun ProfileContent(puppy: Puppy, containerHeight: Dp){
    Column{
        Spacer(modifier = Modifier.height(8.dp))
        Name(puppy = puppy)
        ProfileProperty(label = stringResource(id = R.string.sex), value = puppy.sex)
        Spacer(Modifier.height((containerHeight - 320.dp).coerceAtLeast(0.dp)))
    }
}


@Composable
private fun Name(puppy: Puppy){
    Column(
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp, bottom = 15.dp)
    ) {
        Name(puppy = puppy, modifier = Modifier.paddingFromBaseline(top = 32.dp))
    }
}

@Composable
private fun Name(puppy: Puppy, modifier: Modifier = Modifier) {
    Text(
        text = puppy.title,
        modifier = modifier,
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun ProfileProperty(label: String, value: String, isLink: Boolean = false){
    Column(
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp, bottom = 15.dp)
    ){
        Divider()
        CompositionLocalProvider{
            Text(
                text = label,
                modifier = Modifier.paddingFromBaseline(top = 24.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }
        val style = if(isLink){
            MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.primary)
        }else{
            MaterialTheme.typography.bodyLarge
        }
        Text(
            text = value,
            modifier = Modifier.paddingFromBaseline(24.dp),
            style = style
        )
    }
}

@Preview
@Composable
fun ProfileScreenPreview(){
    val puppy = DataProvider.puppyList
    ProfileScreen(puppy = puppy[0])
}