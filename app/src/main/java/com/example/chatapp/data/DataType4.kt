package com.example.chatapp.data

data class UserData(
    val userId: String? = "",
    val name: String? = "",
    val imageUrl: String? = "",
    val number: String? = "",
    val status: String? = ""
) {
    fun toMap() = mapOf(
        "userId" to userId,
        "name" to name,
        "imageUrl" to imageUrl,
        "number" to number,

        )
}

data class ChatData(
    val chatId: String? = "",
    val user1: ChatUser = ChatUser(),
    val user2: ChatUser = ChatUser(),
)

data class ChatUser(
    val userId: String? = "",
    val name: String? = "",
    val imageUrl: String? = "",
    val number: String? = ""
)

data class Message(
    val sentBy: String? = "",
    val message: String? = "",
    val timestamp: String = "",
)

data class Status(
    val user: ChatUser? = null,
    val imageUrl: String? = null,
    val timestamp: Long? = null
)