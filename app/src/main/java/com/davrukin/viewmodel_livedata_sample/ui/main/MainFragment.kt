package com.davrukin.viewmodel_livedata_sample.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.davrukin.viewmodel_livedata_sample.App.Companion.getApp
import com.davrukin.viewmodel_livedata_sample.databinding.FragmentMainBinding
import com.davrukin.viewmodel_livedata_sample.db.adapter.WordListAdapter
import com.davrukin.viewmodel_livedata_sample.db.model.Word

class MainFragment : Fragment() {

	companion object {
		fun newInstance() = MainFragment()
	}

	private val viewModel: MainViewModel by viewModels {
		MainViewModelFactory(activity?.getApp()?.repoWord)
	}

	private val adapter = WordListAdapter()

	private lateinit var binding: FragmentMainBinding

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		binding = FragmentMainBinding.inflate(inflater, container, false)
		binding.viewModel = viewModel
		binding.lifecycleOwner = viewLifecycleOwner

		return binding.root.also(::launchWhenStarted)
	}

	private fun launchWhenStarted(view: View) {
		lifecycleScope.launchWhenStarted {
			// TODO: do work here
			setupRecyclerView()
			setupObservers()
			setListeners()
		}
	}

	private fun setupRecyclerView() {
		binding.recyclerView
			.apply {
				adapter = this@MainFragment.adapter
				layoutManager = LinearLayoutManager(context)
			}
	}

	private fun setupObservers() {
		viewModel.allWords.observe(viewLifecycleOwner) { words ->
			Log.d("MainFragment", "observing words: $words")
			adapter.submitList(null)
			adapter.submitList(words)
			// https://stackoverflow.com/a/50062174
		}
	}

	private fun setListeners() {
		binding.saveWord.setOnClickListener {
			Log.d("MainFragment", "clicked saveWord")
			val editWord = binding.editWord

			val text = editWord.text.toString()
			val word = Word(text)
			viewModel.insert(word)
			editWord.setText("", TextView.BufferType.EDITABLE)
		}
	}
}