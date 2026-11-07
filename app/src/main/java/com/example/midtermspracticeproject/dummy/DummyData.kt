package com.example.midtermspracticeproject.dummy

import com.example.midtermspracticeproject.R
import com.example.midtermspracticeproject.models.Message
import com.example.midtermspracticeproject.models.MessageType

val dummyMessagesList = listOf(
    Message(1, MessageType.TEXT, "Alice", "Smith", "10:42", 2, "Hey, are we still on for our meeting?", R.drawable.profile),
    Message(2, MessageType.IMAGE, "Bob", "Johnson", "10:40", 0, null, R.drawable.profile),
    Message(3, MessageType.TEXT, "Charlie", "Brown", "10:35", 5, "Check out this document when you get a chance.", R.drawable.profile),
    Message(4, MessageType.VIDEO, "Diana", "Prince", "09:50", 1, null, R.drawable.profile),
    Message(5, MessageType.TEXT, "Ethan", "Hunt", "Yesterday", 0, "Mission accomplished. Heading back now.", R.drawable.profile),
    Message(6, MessageType.IMAGE, "Fiona", "Gallagher", "Yesterday", 3, null, R.drawable.profile),
    Message(7, MessageType.TEXT, "George", "Clooney", "Nov 5", 0, "Let's catch up over coffee next week.", R.drawable.profile),
    Message(8, MessageType.VIDEO, "Hannah", "Montana", "Nov 4", 2, null, R.drawable.profile),
    Message(9, MessageType.TEXT, "Ian", "Malcolm", "Nov 2", 0, "Life finds a way.", R.drawable.profile),
    Message(10, MessageType.IMAGE, "Julia", "Roberts", "Oct 28", 1, null, R.drawable.profile)
)