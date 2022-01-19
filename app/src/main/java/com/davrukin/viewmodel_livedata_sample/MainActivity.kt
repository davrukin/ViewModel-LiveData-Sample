package com.davrukin.viewmodel_livedata_sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.davrukin.viewmodel_livedata_sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

	private lateinit var binding: ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		supportFragmentManager
			.findFragmentById(R.id.nav_host_fragment_main)
			?.findNavController()
			?.apply {
				if (savedInstanceState != null) {
					setGraph(R.navigation.nav_graph_main, savedInstanceState)
				} else {
					setGraph(R.navigation.nav_graph_main)
				}
			}
	}
}