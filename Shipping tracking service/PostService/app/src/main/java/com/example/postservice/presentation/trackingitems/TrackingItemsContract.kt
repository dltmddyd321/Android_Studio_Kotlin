package com.example.postservice.presentation.trackingitems

import com.example.postservice.entity.TrackingInformation
import com.example.postservice.entity.TrackingItem
import com.example.postservice.presentation.BasePresenter
import com.example.postservice.presentation.BaseView

class TrackingItemsContract {

    interface View : BaseView<Presenter> {

        fun showLoadingIndicator()

        fun hideLoadingIndicator()

        fun showNoDataDescription()

        fun showTrackingItemInformation(trackingInformation: List<Pair<TrackingItem, TrackingInformation>>)
    }

    interface Presenter : BasePresenter {
        var trackingItemInformation : List<Pair<TrackingItem, TrackingInformation>>

        fun refresh()
    }
}