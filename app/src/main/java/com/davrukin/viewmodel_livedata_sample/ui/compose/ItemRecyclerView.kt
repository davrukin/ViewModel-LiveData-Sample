package com.davrukin.viewmodel_livedata_sample.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.davrukin.viewmodel_livedata_sample.R

@Composable
fun ItemRecyclerView(
	text: String,
	onClickText: (String) -> Unit,
) {
	Text(
		text = text,
		modifier = Modifier
			.clickable(onClick = { onClickText(text) })
			.fillMaxWidth()
			.background(color = colorResource(android.R.color.holo_orange_light))
			//.background(Color(0xffffbb33))
			.padding(8.dp),
		style = TextStyle(
			color = Color(R.color.black),
			fontSize = 18.sp,
			fontStyle = FontStyle.Normal,
			fontWeight = FontWeight.Black,
			fontFamily = FontFamily.Monospace
		),
	)
	// background color (modifier order is important): https://stackoverflow.com/a/62029311
}

@Preview
@Composable
fun PreviewItemRecyclerView() {
	ItemRecyclerView(text = "Hello", onClickText = {})
}