package com.example.myapplication

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import org.greenrobot.eventbus.EventBus
import java.util.concurrent.Flow.Subscriber

//fun <T> EventBus.asFlow(): Flow<T> = callbackFlow {
//    val eventBusSubscriber = object : Subscriber<String> {
//        override fun onEvent(event: Any) {
//            trySend(event as T)
//        }
//    }
//    EventBus.getDefault().register(eventBusSubscriber)
//    awaitClose {
//        EventBus.getDefault().unregister(eventBusSubscriber)
//    }
//}