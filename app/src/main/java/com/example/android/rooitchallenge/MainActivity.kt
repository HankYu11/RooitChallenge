package com.example.android.rooitchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.android.rooitchallenge.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding

    private val viewModel by viewModels<MainViewModel>()
    private val adapter by lazy { NewsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        setupViews()
        setupObservers()
    }

    private fun setupViews() {
        with(viewBinding) {
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter
            swipeRefreshLayout.setOnRefreshListener {
                viewModel.refreshNews()
            }
        }
    }

    private fun setupObservers() {
        viewModel.newsList.observe(this) {
            adapter.submitList(it)
            viewBinding.swipeRefreshLayout.isRefreshing = false
        }
    }
}