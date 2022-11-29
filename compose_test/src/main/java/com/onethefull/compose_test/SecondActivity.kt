package com.onethefull.compose_test

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.onethefull.compose_test.ui.theme.DiceRollerTheme

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/11/28 5:43 PM
 * @desc
 */
class SecondActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerTheme {
//                Surface(modifier = Modifier.fillMaxSize()) {
//                    MessageCard(msg = Message("Android","Jetpack"))
//                }
                Surface(modifier = Modifier.fillMaxSize()) {
                    Conversation(message = SampleData.conversationSample)
                }
            }

        }
    }
}

data class Message(
    val author: String,
    val body: String
)

@Composable
fun MessageCard(msg: Message){
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape)
        )

        Spacer(modifier = Modifier.width(8.dp))

        var isExpanded by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier.clickable {
                isExpanded = !isExpanded
            }
        ) {
            Text(
                text = msg.author,
                color = MaterialTheme.colorScheme.surfaceVariant,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))

            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.animateContentSize().padding(1.dp)


            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

        }
    }

}

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PreviewMessageCardTwo(){
    DiceRollerTheme {
        Surface {
            MessageCard(msg = Message("Colleague", "Jetpack Compose"))
        }
    }
}

@Composable
fun Conversation(message: List<Message>){
    LazyColumn{
        message.map { 
            item { MessageCard(msg = it) }
        }
    }
}

@Preview
@Composable
fun PreviewConversation(){
    DiceRollerTheme {
        Conversation(message = SampleData.conversationSample)
    }
}
