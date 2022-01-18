package com.davrukin.viewmodel_livedata_sample.db.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.davrukin.viewmodel_livedata_sample.R
import com.davrukin.viewmodel_livedata_sample.db.model.Word

class WordListAdapter : ListAdapter<Word, WordListAdapter.WordViewHolder>(WordsComparator()) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
		return WordViewHolder(parent)
	}

	override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
		val word = getItem(position).word
		Log.d("WordListAdapter", "onBindViewHolder; word=$word")
		holder.bind(word)
	}

	class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		private val wordItemView: TextView = itemView.findViewById(R.id.textView)

		constructor(parent: ViewGroup) : this(
			LayoutInflater
				.from(parent.context)
				.inflate(R.layout.item_recyclerview, parent, false)
		)

		fun bind(text: String?) {
			wordItemView.text = text
		}
	}

	class WordsComparator : DiffUtil.ItemCallback<Word>() {
		override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
			return oldItem === newItem
		}

		override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
			return oldItem.word == newItem.word
		}
	}
}