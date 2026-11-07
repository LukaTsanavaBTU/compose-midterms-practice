package com.example.midtermspracticeproject.models

data class Message(
    val id: Int,
    val type: MessageType,
    val firstName: String,
    val lastName: String,
    val timeSent: String,
    val unreadCount: Int,
    val lastMessage: String? = null,
    val image: Int
) {
    val userName: String
        get() = "$firstName $lastName"
}