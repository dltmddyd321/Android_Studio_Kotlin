package com.example.postservice.presentation.trackingitems

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.postservice.R
import com.example.postservice.databinding.FragmentTrackingItemsBinding
import com.example.postservice.entity.TrackingInformation
import com.example.postservice.entity.TrackingItem
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.ScopeFragment

class TrackingItemsFragment : ScopeFragment(), TrackingItemsContract.View {
    override fun showLoadingIndicator() {
        TODO("Not yet implemented")
    }

    override fun hideLoadingIndicator() {
        TODO("Not yet implemented")
    }

    override fun showNoDataDescription() {
        TODO("Not yet implemented")
    }

    override fun showTrackingItemInformation(trackingInformation: List<Pair<TrackingItem, TrackingInformation>>) {
        TODO("Not yet implemented")
    }

    override val presenter: TrackingItemsContract.Presenter by inject()

    private var binding : FragmentTrackingItemsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentTrackingItemsBinding.inflate(inflater)
        .also { binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun bindView() {
        binding?.refreshLayout?.setOnRefreshListener {
            presenter.refresh()
        }
        binding?.addTrackingItemButton?.setOnClickListener {
            findNavController().navigate(R.id.to_add_tracking_item)
        }
        binding?.addTrackingItemFloatingActionButton?.setOnClickListener { _ ->
            findNavController().navigate(R.id.to_add_tracking_item)
        }
    }
}