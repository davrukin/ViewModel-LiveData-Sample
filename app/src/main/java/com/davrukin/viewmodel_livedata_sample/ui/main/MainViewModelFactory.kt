package com.davrukin.viewmodel_livedata_sample.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.davrukin.viewmodel_livedata_sample.db.repo.RepositoryWord

class MainViewModelFactory(private val repo: RepositoryWord?) : ViewModelProvider.Factory {

	@Suppress("UNCHECKED_CAST")
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		if (repo != null && modelClass.isAssignableFrom(MainViewModel::class.java)) {
			return MainViewModel(repo) as T
		}
		throw IllegalArgumentException("Unknown ViewModel Class")
	}

}