package com.jay.randomimage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jay.randomimage.network.NetworkApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class MainActivity : AppCompatActivity() {
    private val compositeDisposable: CompositeDisposable by lazy(::CompositeDisposable)
    private val networkApi by lazy {
        NetworkApi
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        compositeDisposable.addAll(
            networkApi.api.getPhotos()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                }, {

                })
        )

    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}