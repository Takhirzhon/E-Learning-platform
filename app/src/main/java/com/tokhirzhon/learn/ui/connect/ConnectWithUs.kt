package com.tokhirzhon.learn.ui.connect

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tokhirzhon.learn.R

class ConnectWithUs : Fragment() {

    companion object {
        fun newInstance() = ConnectWithUs()
    }

    private lateinit var viewModel: ConnectWithUsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_connect_with_us, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ConnectWithUsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}