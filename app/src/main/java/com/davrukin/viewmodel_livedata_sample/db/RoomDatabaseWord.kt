package com.davrukin.viewmodel_livedata_sample.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.davrukin.viewmodel_livedata_sample.db.dao.DaoWord
import com.davrukin.viewmodel_livedata_sample.db.model.Word
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class RoomDatabaseWord : RoomDatabase() {

	abstract fun daoWord(): DaoWord

	companion object {
		// singleton prevents multiple instance of the database being open simultaneously

		@Volatile
		private var INSTANCE: RoomDatabaseWord? = null

		fun getDatabase(context: Context, scope: CoroutineScope): RoomDatabaseWord {
			// if the INSTANCE is not null, return it
			// if it is, then create the database

			return INSTANCE ?: synchronized(this) {
				val instance = Room
					.databaseBuilder(
						context.applicationContext,
						RoomDatabaseWord::class.java,
						"database_word"
					)
					.addCallback(CallbackDatabaseWord(scope))
					.build()
				INSTANCE = instance
				instance
			}
		}
	}

	private class CallbackDatabaseWord(private val scope: CoroutineScope) : RoomDatabase.Callback() {

		override fun onCreate(db: SupportSQLiteDatabase) {
			super.onCreate(db)

			INSTANCE?.let { database ->
				scope.launch {
					populateDatabase(database.daoWord())
				}
			}
		}

		suspend fun populateDatabase(daoWord: DaoWord) {
			// delete all content here
			//daoWord.deleteAll()

			// add sample words
			//daoWord.insert(Word(word = "Hello"))
			//daoWord.insert(Word(word = "World"))
		}
	}
}