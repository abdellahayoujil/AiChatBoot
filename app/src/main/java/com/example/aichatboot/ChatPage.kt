package com.example.aichatboot

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aichatboot.ui.theme.ColorModelMessage
import com.example.aichatboot.ui.theme.ColorUserMessage
import com.example.aichatboot.ui.theme.Purple80

@Composable
fun ChatPage(modifier: Modifier = Modifier, viewModel: ViewModel) {
    Column(modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        AppHeader()
        MessageList(
            modifier = Modifier.weight(1f).padding(horizontal = 8.dp),
            messageList = viewModel.messageList
        )
        Divider(color = Color.Gray.copy(alpha = 0.3f))
        MessageInput(onMessageSend = viewModel::sendMessage)
    }
}

@Composable
fun MessageList(modifier: Modifier = Modifier, messageList: List<MessageModel>) {
    if (messageList.isEmpty()) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier.size(60.dp),
                painter = painterResource(id = R.drawable.outline_assignment_returned_24),
                contentDescription = "Icon",
                tint = Purple80
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Ask me anything", fontSize = 20.sp, fontWeight = FontWeight.Medium)
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize().padding(vertical = 8.dp),
            reverseLayout = true
        ) {
            items(messageList.reversed()) {
                AnimatedVisibility(visible = true, enter = fadeIn(), exit = fadeOut()) {
                    MessageRow(messageModel = it)
                }
            }
        }
    }
}

@Composable
fun MessageRow(messageModel: MessageModel) {
    val isModel = messageModel.role == "model"
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isModel) Arrangement.Start else Arrangement.End
    ) {
        Box(
            modifier = Modifier
                .padding(6.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(if (isModel) ColorModelMessage else ColorUserMessage)
                .padding(12.dp)
                .widthIn(max = 300.dp)
        ) {
            SelectionContainer {
                Text(
                    text = messageModel.message,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun MessageInput(onMessageSend: (String) -> Unit) {
    var message by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(16.dp)),
            value = message,
            onValueChange = { message = it },
            placeholder = { Text("Type a message...") },
            maxLines = 5,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = Color.Gray
            )
        )
        Spacer(modifier = Modifier.width(6.dp))
        IconButton(
            onClick = {
                if (message.isNotBlank()) {
                    onMessageSend(message.trim())
                    message = ""
                }
            },
            modifier = Modifier
                .size(48.dp)
                .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(12.dp))
        ) {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = "Send",
                tint = Color.White
            )
        }
    }
}

@Composable
fun AppHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(vertical = 20.dp, horizontal = 16.dp)
    ) {
        Text(
            text = "AI ChatBot by Abdellah",
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
