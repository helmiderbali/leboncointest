package com.example.leboncoin.presentation.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Advert
import com.example.leboncoin.databinding.ActivityMainBinding
import com.example.leboncoin.presentation.model.MainUiState
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private var rvAdapter: AdvertListAdapter? = null

    @Inject
    lateinit var picasso: Picasso

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUi()
        observeData()
    }

    private fun setupUi() {
        binding.swiperefresh.setOnRefreshListener {
            binding.emptyMessage.visibility = View.GONE
            mainViewModel.getAdverts()
            binding.swiperefresh.isRefreshing = false;
        }

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.rvList.layoutManager = layoutManager
        rvAdapter = AdvertListAdapter(listOf(), picasso)
        binding.rvList.adapter = rvAdapter
    }

    private fun observeData() {
        mainViewModel.uiState.observe(this, Observer {
            handleUiState(it)
        })
    }

    private fun handleUiState(uiState: MainUiState<List<Advert>>) {
        if (!uiState.advertItems.isNullOrEmpty()) {
            rvAdapter?.updateData(uiState.advertItems)
        } else if (!uiState.isLoading) {
            binding.emptyMessage.visibility = View.VISIBLE
        }
    }
}