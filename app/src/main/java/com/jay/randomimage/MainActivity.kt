package com.jay.randomimage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.jay.randomimage.databinding.ActivityMainBinding
import com.jay.randomimage.model.ImageResponse
import com.jay.randomimage.network.NetworkApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class MainActivity : AppCompatActivity() {


    private val compositeDisposable: CompositeDisposable by lazy(::CompositeDisposable)
    private val networkApi by lazy {
        NetworkApi
    }

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val mainAdapter by lazy {
        MainAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initAdapter()

        compositeDisposable.addAll(
            networkApi.api.getPhotos()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { showLoading() }
                .doAfterTerminate { hideLoading() }
                .doOnError { }
                .subscribe(::setItem)
        )

    }

    private fun initAdapter() = with(binding) {
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.adapter = mainAdapter
    }

    private fun setItem(images: List<ImageResponse>) {
        mainAdapter.addItems(images)
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}