package com.example.postservice.presentation.trackingitems

import com.example.postservice.entity.TrackingInformation
import com.example.postservice.entity.TrackingItem
import com.example.postservice.repository.TrackingItemRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

class TrackingItemsPresenter(private val view : TrackingItemsContract.View, private val trackingItemRepository: TrackingItemRepository) : TrackingItemsContract.Presenter{
    override var trackingItemInformation: List<Pair<TrackingItem, TrackingInformation>> = emptyList()

    override fun refresh() {
        TODO("Not yet implemented")
    }

    override val scope: CoroutineScope = MainScope()

    override fun onViewCreated() {
        TODO("Not yet implemented")
    }

    override fun onDestroyView() {
        TODO("Not yet implemented")
    }
}