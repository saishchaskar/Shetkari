package com.oneearth.shetkari.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataViewModel(private val repository: DataRepository) : ViewModel() {

    private val _data = MutableLiveData<List<Record>>()
    val data: LiveData<List<Record>> = _data

    fun fetchData(apiUrl: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val response = repository.fetchData(apiUrl)
            _data.value = response?.records
        }
    }
}
