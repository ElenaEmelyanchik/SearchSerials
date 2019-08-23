package com.example.searchserials

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchViewModel(val repository: Repository) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val listSerials = MutableLiveData<ArrayList<Serial>>()

    fun search(newText: String) {
        compositeDisposable.add(repository.searchSerials(newText).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(
                {
                    listSerials.value = it
                },
                {
                    Log.e(this.javaClass.name, it.message)
                }
            ))
    }

    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    fun serials() = listSerials
}