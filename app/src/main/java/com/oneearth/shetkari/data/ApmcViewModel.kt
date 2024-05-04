//package com.oneearth.shetkari.data
//
//// ApmcViewModel.kt
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import kotlinx.coroutines.launch
//
//class ApmcViewModel : ViewModel() {
//    private val apmcService = APMCApi.apmcInstances
//
//    private val _states = MutableLiveData<List<State>>()
//    val states: LiveData<List<State>> = _states
//
//    private val _districts = MutableLiveData<List<District>>()
//    val districts: LiveData<List<District>> = _districts
//
//    private val _marketPrices = MutableLiveData<List<MarketPrice>>()
//    val marketPrices: LiveData<List<MarketPrice>> = _marketPrices
//
//    fun fetchStatesAndDistricts() {
//        viewModelScope.launch {
//            try {
//                // Fetch states
//                val response = apmcService.getApmc()
//                _states.value = response.states
//
//                // Fetch districts (you can do this based on the selected state)
//                // For simplicity, I'm assuming a method fetchDistricts(stateId: String)
//                fetchDistricts(response.states.first().id)
//            } catch (e: Exception) {
//                // Handle error
//            }
//        }
//    }
//
//    private fun fetchDistricts(stateId: String) {
//        viewModelScope.launch {
//            try {
//                // Fetch districts based on stateId
//                val response = apmcService.getDistricts(stateId)
//                _districts.value = response.districts
//            } catch (e: Exception) {
//                // Handle error
//            }
//        }
//    }
//
//    fun fetchMarketPrices(districtId: String) {
//        viewModelScope.launch {
//            try {
//                // Fetch market prices based on districtId
//                val response = apmcService.getMarketPrices(districtId)
//                _marketPrices.value = response.marketPrices
//            } catch (e: Exception) {
//                // Handle error
//            }
//        }
//    }
//}
