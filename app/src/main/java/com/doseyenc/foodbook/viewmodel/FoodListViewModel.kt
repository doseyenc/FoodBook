package com.doseyenc.foodbook.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.doseyenc.foodbook.model.FoodModel
import com.doseyenc.foodbook.service.FoodAPIService
import com.doseyenc.foodbook.service.FoodDatabase
import com.doseyenc.foodbook.util.SharedPref
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposables
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class FoodListViewModel(application: Application) : BaseViewModel(application) {


    val foods = MutableLiveData<List<FoodModel>>()
    val foodErrorMessage = MutableLiveData<Boolean>()
    val foodLoading = MutableLiveData<Boolean>()

    private val foodApiService = FoodAPIService()
    private val disposable = CompositeDisposable()

    private val SharedPref = SharedPref(getApplication())

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
                        saveRoom(t)
                    }

                    override fun onError(e: Throwable) {
                        foodErrorMessage.value = true
                        foodLoading.value = false
                        e.printStackTrace()
                    }

                })
        )
    }

    private fun showFoods(t: List<FoodModel>) {
        foods.value = t
        foodErrorMessage.value = false
        foodLoading.value = false
    }

    private fun saveRoom(t: List<FoodModel>) {
        launch {
            val dao = FoodDatabase(getApplication()).getFoodDao()
            dao.deleteAllFoods()
            val idList = dao.insertAllFoods(*t.toTypedArray())
            var i = 0
            while (i < t.size) {
                t[i].id = idList[i].toInt()
                i++
            }
            showFoods(t)
        }
        SharedPref.saveTime(System.nanoTime())
    }

}