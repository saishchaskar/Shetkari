package com.oneearth.shetkari.fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.oneearth.shetkari.Adapter.PriceAdapter
import com.oneearth.shetkari.R
import com.oneearth.shetkari.data.ApiService
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

// Add necessary imports

class PriceFragment : Fragment() {

    private lateinit var spinnerState: Spinner
    private lateinit var spinnerDistrict: Spinner
    private lateinit var spinnerMarket: Spinner
    private lateinit var recyclerViewPrices: RecyclerView
    private lateinit var priceAdapter: PriceAdapter // Create a custom adapter for your RecyclerView

    // Retrofit service for API calls
    private val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.data.gov.in/")
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_price, container, false)

        spinnerState = view.findViewById(R.id.spinnerState)
        spinnerDistrict = view.findViewById(R.id.spinnerDistrict)
        spinnerMarket = view.findViewById(R.id.spinnerMarket)
        recyclerViewPrices = view.findViewById(R.id.recyclerViewPrices)

        setupSpinners()
        setupRecyclerView()

        return view
    }

    private fun setupSpinners() {
        // Fetch and populate states
        fetchStates()

        // Setup listeners for district and market spinners
        spinnerState.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedState = spinnerState.selectedItem as String
                fetchDistricts(selectedState)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle nothing selected
            }
        }

        spinnerDistrict.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedDistrict = spinnerDistrict.selectedItem as String
                fetchMarkets(selectedDistrict)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle nothing selected
            }
        }

        spinnerMarket.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedMarket = spinnerMarket.selectedItem as String
                fetchCommodities(selectedMarket)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle nothing selected
            }
        }
    }

    private fun fetchStates() {
        // Make API call to fetch states
        // Update spinnerState with fetched states
    }

    private fun fetchDistricts(selectedState: String) {
        // Make API call to fetch districts for the selected state
        // Update spinnerDistrict with fetched districts
    }

    private fun fetchMarkets(selectedDistrict: String) {
        // Make API call to fetch markets for the selected district
        // Update spinnerMarket with fetched markets
    }

    private fun fetchCommodities(selectedMarket: String) {
        // Make final API call to fetch commodities for the selected market
        // Populate recyclerViewPrices with fetched commodities
    }

    private fun setupRecyclerView() {
        // Initialize RecyclerView and adapter
    }
}
