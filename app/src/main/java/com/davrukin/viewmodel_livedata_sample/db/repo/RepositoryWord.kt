package com.davrukin.viewmodel_livedata_sample.db.repo

import androidx.annotation.WorkerThread
import com.davrukin.viewmodel_livedata_sample.db.dao.DaoWord
import com.davrukin.viewmodel_livedata_sample.db.model.Word
import kotlinx.coroutines.flow.Flow

// only pass in DAO since don't need whole db
class RepositoryWord(private val daoWord: DaoWord) {

	// Room executes all queries on a separate thread
	// Observed Flow will notify the observer when data has changed
	val allWords: Flow<List<Word>> = daoWord.getAlphabetizedWords()

	// by default, Room runs suspend queries off the main thread, therefore we don't need to
	// implement anything else to ensure we're not doing long-running database work
	// off the main thread
	@WorkerThread
	suspend fun insert(word: Word) {
		daoWord.insert(word)
	}
}