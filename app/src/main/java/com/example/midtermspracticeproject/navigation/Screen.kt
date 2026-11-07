package com.example.midtermspracticeproject.navigation

import com.example.midtermspracticeproject.models.Message
import kotlinx.serialization.Serializable

interface Routes {
    @Serializable
    data object MessagesList

    @Serializable
    data class SelectedMessage(
        val selectedMessageId: Int,
    )

}