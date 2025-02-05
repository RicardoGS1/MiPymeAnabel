package com.virtualworld.mipymeanabel.data.notification

import kotlinx.coroutines.suspendCancellableCoroutine

actual class NotificationService {
    actual suspend fun getToken(): String? = suspendCancellableCoroutine { continuation ->
//        dispatch_async(dispatch_get_main_queue()) {
//            FIRMessaging.messaging().tokenWithCompletion { token, error ->
//                if (error != null) {
//                    continuation.resume(null)
//                } else if (token != null) {
//                    continuation.resume(token)
//                } else {
//                    continuation.resume(null)
//                }
//            }
//        }
    }
}