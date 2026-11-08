package com.example.midtermspracticeproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.midtermspracticeproject.dummy.dummyMessagesList
import com.example.midtermspracticeproject.models.Message
import com.example.midtermspracticeproject.navigation.Routes
import com.example.midtermspracticeproject.ui.theme.AccentTeal
import com.example.midtermspracticeproject.ui.theme.BackgroundPrimary
import com.example.midtermspracticeproject.ui.theme.MidtermsPracticeProjectTheme
import com.example.midtermspracticeproject.ui.theme.TextPrimary
import com.example.midtermspracticeproject.ui.theme.TextSecondary

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MidtermsPracticeProjectTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Routes.MessagesList) {
                    composable<Routes.MessagesList> {
                        MessagesListScreen(dummyMessagesList, { id ->
                            navController.navigate(route = Routes.SelectedMessage(id))
                        })
                    }
                    composable<Routes.SelectedMessage> { backStackEntry ->
                        val message: Routes.SelectedMessage = backStackEntry.toRoute()
                        val selectedMessage by remember { mutableStateOf(dummyMessagesList.find { it.id == message.selectedMessageId }) }

                        selectedMessage?.let { msg ->
                            SelectedMessageScreen(msg, { navController.popBackStack() })
                        } ?: run {
                            Text(text = "Message Not Found")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MessagesListScreen(
    messages: List<Message>, onSelectMessage: (id: Int) -> Unit, modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundPrimary)
            .padding(vertical = 16.dp)
    ) {
        items(items = messages, key = { item -> item.id }) { item ->
            MessageItem(item, { id -> onSelectMessage(id) })
        }
    }
}

@Composable
fun SelectedMessageScreen(
    message: Message, onBackPress: () -> Unit, modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundPrimary)
            .padding(vertical = 16.dp)
    ) {
        IconButton(onClick = { onBackPress() }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                contentDescription = "Back",
                tint = TextPrimary
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        MessageItem(message, {})
    }
}

@Composable
fun MessageItem(
    message: Message, onSelectMessage: (id: Int) -> Unit, modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .fillMaxWidth()
            .clickable { onSelectMessage(message.id) },
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            painter = painterResource(id = message.image),
            contentDescription = message.userName,
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = message.userName,
                color = TextPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            if (message.lastMessage != null) {
                Text(
                    text = message.lastMessage,
                    modifier = Modifier,
                    color = TextSecondary,
                    fontSize = 14.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                )
            }
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = message.timeSent,
                modifier = Modifier.wrapContentSize(),
                color = if (message.unreadCount > 0) AccentTeal else TextSecondary,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            if (message.unreadCount > 0) {
                Box(
                    modifier = modifier
                        .background(color = AccentTeal, shape = CircleShape)
                        .size(20.dp),
                    contentAlignment = Alignment.Center // Centers the text perfectly
                ) {
                    Text(
                        text = "${message.unreadCount}", color = BackgroundPrimary, fontSize = 12.sp
                    )
                }
            }
        }
    }
}




