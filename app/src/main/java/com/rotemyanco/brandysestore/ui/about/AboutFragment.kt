package com.rotemyanco.brandysestore.ui.about

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rotemyanco.brandysestore.R

class AboutFragment : Fragment() {

	companion object {
		fun newInstance() = AboutFragment()
	}

	private lateinit var viewModel: AboutViewModel

	override fun onAttach(context: Context) {
		super.onAttach(context)
		viewModel = ViewModelProvider(this)[AboutViewModel::class.java]

	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return inflater.inflate(R.layout.fragment_about, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
	}


}