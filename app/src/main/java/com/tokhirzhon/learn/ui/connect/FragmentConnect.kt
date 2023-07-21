package com.tokhirzhon.learn.ui.connect

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tokhirzhon.learn.R

class FragmentConnect : Fragment() {

    companion object {
        fun newInstance() = FragmentConnect()
    }

    private lateinit var viewModel: FragmentConnectViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_connect, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FragmentConnectViewModel::class.java)
        // TODO: Use the ViewModel
    }

}