package com.davrukin.viewmodel_livedata_sample.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_word")
data class Word(

	@ColumnInfo(name = "word")
	val word: String,

	@PrimaryKey(autoGenerate = true)
	val id: Int = 0,
)
