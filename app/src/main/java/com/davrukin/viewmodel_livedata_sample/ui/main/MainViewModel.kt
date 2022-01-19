package com.davrukin.viewmodel_livedata_sample.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.davrukin.viewmodel_livedata_sample.db.model.Word
import com.davrukin.viewmodel_livedata_sample.db.repo.RepositoryWord
import kotlinx.coroutines.*

class MainViewModel(private val repo: RepositoryWord) : ViewModel() {

	private val viewModelJob: CompletableJob = Job()
	val viewModelScope: CoroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

	// Using LiveData and caching what allWords returns has several benefits:
	// - We can put an observer on the data (instead of polling for changes) and only update the
	//   the UI when the data actually changes.
	// - Repository is completely separated from the UI through the ViewModel.
	val allWords: LiveData<List<Word>> = repo.allWords.asLiveData(viewModelScope.coroutineContext)

	/**
	 * Launching a new coroutine to insert the data in a non-blocking way
	 */
	fun insert(word: Word) = viewModelScope.launch {
		repo.insert(word)
	}

	fun deleteAllWords() = viewModelScope.launch {
		repo.deleteAllWords()
	}

	override fun onCleared() {
		viewModelScope.cancel("calling onCleared()")
		super.onCleared()
	}

}