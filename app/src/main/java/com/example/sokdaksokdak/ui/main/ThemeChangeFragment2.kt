package com.example.sokdaksokdak.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sokdaksokdak.R

class ThemeChangeFragment2 : Fragment() {

    companion object {
        fun newInstance() = ThemeChangeFragment2()
    }

    private lateinit var viewModel: ThemeChangeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_theme_change2, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ThemeChangeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}