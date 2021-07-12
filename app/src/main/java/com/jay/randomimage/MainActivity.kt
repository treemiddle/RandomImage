package com.jay.randomimage

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.jay.randomimage.databinding.ActivityMainBinding
import com.jay.randomimage.model.ImageResponse
import com.jay.randomimage.network.NetworkApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {


    private val compositeDisposable: CompositeDisposable by lazy(::CompositeDisposable)
    private val querySubject: BehaviorSubject<String> = BehaviorSubject.createDefault("")

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


        bindRx()
        initEditListener()
        initAdapter()
        loadingShimmer(true)
        refresh()
    }

    private fun initEditListener() = with(binding) {
        etQuery.addTextChangedListener {
            querySubject.onNext(it.toString())
        }
    }

    private fun initAdapter() = with(binding) {
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.adapter = mainAdapter
    }

    private fun bindRx() {
        compositeDisposable.addAll(
            querySubject.debounce(700, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::getImages)
        )
    }

    private fun getImages(query: String? = null) {
        addDisposable(
            networkApi.api.getPhotos(query)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loadingProgressbar(true) }
                .doAfterTerminate { loadingProgressbar(false) }
                .doOnError(::log)
                .subscribe(::setItem)
        )
    }

    private fun setItem(images: List<ImageResponse>) {
        mainAdapter.addItems(images)
        loadingRecyclerView(true)
        loadingShimmer(false)
        hideRefreshLoading()
    }

    private fun refresh() {
        binding.swipeRefresh.setOnRefreshListener {
            getImages(querySubject.value ?: "")
        }
    }

    private fun hideRefreshLoading() {
        binding.swipeRefresh.isRefreshing = false
    }

    private fun loadingProgressbar(result: Boolean) {
        binding.progressBar.visibility = if (result) {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }
    }

    private fun loadingRecyclerView(result: Boolean) {
        binding.recyclerView.visibility = if (result) {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }
    }

    private fun loadingShimmer(result: Boolean) {
        binding.shimmerLayout.visibility = if (result) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    private fun log(t: Throwable) {
        if (BuildConfig.DEBUG) {
            Log.d(javaClass.simpleName, t.localizedMessage ?: "error")
            loadingRecyclerView(false)
            loadingShimmer(false)
        }
    }

    private fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}