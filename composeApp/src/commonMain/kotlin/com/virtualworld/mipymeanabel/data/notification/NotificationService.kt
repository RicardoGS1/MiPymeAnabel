package com.virtualworld.mipymeanabel.data.notification

expect class NotificationService() {
    suspend fun getToken(): String?
}