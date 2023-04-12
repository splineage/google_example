package com.onethefull.compose_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.onethefull.compose_test.ui.theme.DiceRollerTheme

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2023/04/11 4:36 PM
 * @desc
 */
class PuppyActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerTheme{
                PuppyApp{
                    startActivity(ProfileActivity.newIntent(this, it))
                }
            }
        }
    }
}

/**
 * Scaffold : Compose 에서 기본 Material Design 을 구현한 레이아웃
 */
@Composable
fun PuppyApp(navigateToProfile: (Puppy) -> Unit){
    Scaffold(
        content = {
            BarkHomeContent(navigateToProfile = navigateToProfile)
        }
    )
}

/**
 * remember : Compose 에서 사용하는 데이터를 저장하는 함수
 */
@Preview
@Composable
fun BarkHomeContent(navigateToProfile: (Puppy) -> Unit){
    val puppies = remember { DataProvider.puppyList }
    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)){ // RecyclerView 와 같은 역할
        items(
            items = puppies,
            itemContent = { puppy ->
                PuppyListItem(puppy = puppy, navigateToProfile = navigateToProfile)
            }
        )
    }
}