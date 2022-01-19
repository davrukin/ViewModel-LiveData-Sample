package com.davrukin.viewmodel_livedata_sample.ui.compose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.davrukin.viewmodel_livedata_sample.App.Companion.getApp
import com.davrukin.viewmodel_livedata_sample.R
import com.davrukin.viewmodel_livedata_sample.databinding.FragmentComposeBinding
import com.davrukin.viewmodel_livedata_sample.db.model.Word
import com.davrukin.viewmodel_livedata_sample.ui.main.MainViewModel
import com.davrukin.viewmodel_livedata_sample.ui.main.MainViewModelFactory
import com.google.android.material.composethemeadapter.MdcTheme

class ComposeFragment : Fragment() {

	private lateinit var binding: FragmentComposeBinding

	private val viewModel: MainViewModel by viewModels {
		MainViewModelFactory(activity?.getApp()?.repoWord)
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		binding = FragmentComposeBinding.inflate(inflater, container, false)
		binding.lifecycleOwner = viewLifecycleOwner

		return binding.root.also(::launchWhenStarted)
	}

	private fun launchWhenStarted(view: View) {
		lifecycleScope.launchWhenStarted {
			initCompose()
		}
	}

	private fun initCompose() {
		// https://stackoverflow.com/a/66560707
		binding.composeView.setContent {
			MdcTheme {
				MyComposeView(viewModel)
			}
		}
	}
}

@Composable
private fun MyComposeView(viewModel: MainViewModel) {
	var text by remember {
		mutableStateOf("")
	}

	Column(
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		ItemListView(viewModel)
		Spacer(Modifier.fillMaxWidth())
		OutlinedTextField(
			value = text,
			onValueChange = { text = it },
			label = { Text(stringResource(R.string.hint_word)) }
		)
		Spacer(Modifier.fillMaxWidth())
		OutlinedButton(
			onClick = {
				val word = Word(text)
				viewModel.insert(word)
				text = ""
			}
		) {
			Text(stringResource(R.string.button_save))
		}
		Spacer(Modifier.fillMaxWidth())
		OutlinedButton(
			onClick = {
				viewModel.deleteAllWords()
			}
		) {
			Text(stringResource(R.string.button_delete))
		}
		Spacer(Modifier.fillMaxWidth())
	}
}

@Composable
private fun ItemListView(viewModel: MainViewModel) {
	val items: List<Word> by viewModel.allWords.observeAsState(listOf())
	ItemList(
		modifier = Modifier
			.padding()
			.fillMaxWidth(0.5F)
			.fillMaxHeight(0.5F),
		items = items.map { it.word }
	)
}

/*@Preview
@Composable
fun PreviewMyComposeView() {
	MyComposeView()
}*/
