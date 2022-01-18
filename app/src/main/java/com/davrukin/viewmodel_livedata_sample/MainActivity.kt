package com.davrukin.viewmodel_livedata_sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.davrukin.viewmodel_livedata_sample.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		if (savedInstanceState == null) {
			supportFragmentManager
				.beginTransaction()
				.replace(R.id.container, MainFragment.newInstance())
				.commitNow()
		}
	}
}