package com.davrukin.viewmodel_livedata_sample.ui.compose

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ItemList(modifier: Modifier, items: List<String>) {
	LazyColumn(modifier) {
		items(items) { item ->
			ItemRecyclerView(text = item, onClickText = {
				Log.d("ItemList", "Clicked item $item")
			})
		}
	}
	Divider()
}

@Preview
@Composable
fun PreviewItemList() {
	ItemList(Modifier, listOf(
		"hello",
		"world",
		"this",
		"is",
		"a",
		"pizza",
	))
}