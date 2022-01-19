package com.davrukin.viewmodel_livedata_sample.ui.selection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.davrukin.viewmodel_livedata_sample.R
import com.davrukin.viewmodel_livedata_sample.databinding.FragmentSelectionBinding

class SelectionFragment : Fragment() {

	private lateinit var binding: FragmentSelectionBinding

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
		binding = FragmentSelectionBinding.inflate(inflater, container, false)
		binding.lifecycleOwner = viewLifecycleOwner

		return binding.root.also(::launchWhenStarted)
	}

	private fun launchWhenStarted(view: View) {
		lifecycleScope.launchWhenStarted {
			setListeners()
		}
	}

	private fun setListeners() {
		binding.buttonCompose.setOnClickListener {
			findNavController().navigate(R.id.composeFragment)
		}

		binding.buttonMain.setOnClickListener {
			findNavController().navigate(R.id.mainFragment)
		}

		binding.buttonOther.setOnClickListener {
			//findNavController().navigate(R.id.composeFragment)
		}
	}
}