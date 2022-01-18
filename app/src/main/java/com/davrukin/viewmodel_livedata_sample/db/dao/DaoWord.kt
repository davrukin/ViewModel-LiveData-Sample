package com.davrukin.viewmodel_livedata_sample.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.davrukin.viewmodel_livedata_sample.db.model.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoWord {

	@Query("SELECT * FROM table_word ORDER BY word ASC")
	fun getAlphabetizedWords(): Flow<List<Word>>

	@Insert(onConflict = OnConflictStrategy.IGNORE)
	suspend fun insert(word: Word)

	@Query("DELETE FROM table_word")
	suspend fun deleteAll()

}