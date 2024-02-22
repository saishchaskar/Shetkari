package com.oneearth.shetkari.data

/**
 * TODO: document your custom view class.
 */
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
// PriceDataViewModel.kt
class PriceDataViewModel : ViewModel() {

    private val priceDataRepository = PriceDataRepository()

    private val _priceDataList = MutableLiveData<List<PriceData>>()
    val priceDataList: LiveData<List<PriceData>> get() = _priceDataList

    fun getAllPriceData() {
        viewModelScope.launch {
            _priceDataList.value = priceDataRepository.getAllPriceData()
        }
    }
}

