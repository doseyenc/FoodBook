package com.doseyenc.foodbook.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.doseyenc.foodbook.model.FoodModel
import com.doseyenc.foodbook.service.FoodAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposables
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FoodListViewModel : ViewModel() {


    val foods = MutableLiveData<List<FoodModel>>()
    val foodErrorMessage = MutableLiveData<Boolean>()
    val foodLoading = MutableLiveData<Boolean>()

    private val foodApiService = FoodAPIService()
    private val disposable = CompositeDisposable()

    fun refreshData() {
        getDataFromServer()
    }

    private fun getDataFromServer() {
        foodLoading.value = true
        disposable.add(
            foodApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<FoodModel>>() {
                    override fun onSuccess(t: List<FoodModel>) {
                        foods.value = t
                        foodErrorMessage.value = false
                        foodLoading.value = false
                    }

                    override fun onError(e: Throwable) {
                        foodErrorMessage.value = true
                        foodLoading.value = false
                        e.printStackTrace()
                    }

                })
        )
    }

}