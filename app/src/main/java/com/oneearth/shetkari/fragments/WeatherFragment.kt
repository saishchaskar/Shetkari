package com.oneearth.shetkari.fragments

import android.Manifest.permission
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.oneearth.shetkari.Models.WeatherModel
import com.oneearth.shetkari.R
import com.oneearth.shetkari.databinding.FragmentWeatherBinding
import com.oneearth.shetkari.utilities.ApiUtilities
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.math.RoundingMode

class WeatherFragment : Fragment() {

    private lateinit var binding: FragmentWeatherBinding
    private lateinit var fusedLocationProvider: FusedLocationProviderClient
    private val LOCATION_REQUEST_CODE = 101
    private val apikey = "886a7d1566beb94eb05472676ea817b0"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)
        val view = binding.root

        fusedLocationProvider = LocationServices.getFusedLocationProviderClient(requireActivity())

        getCurrentLocation()

        binding.locationSearch.setOnEditorActionListener { textview, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                getCityWeather(binding.locationSearch.text.toString())
                binding.locationSearch.text.clear()
                hideKeyboard(requireActivity())
                binding.locationSearch.clearFocus()
                true
            } else {
                false
            }
        }

        binding.currentLocation.setOnClickListener {
            getCurrentLocation()
        }

        binding.searchOption.setOnClickListener {
            binding.locationOptionBar.visibility = View.GONE
            binding.locationSearchBar.visibility = View.VISIBLE
        }

        binding.back.setOnClickListener {
            hideKeyboard(this.requireActivity())
            binding.locationOptionBar.visibility = View.VISIBLE
            binding.locationSearchBar.visibility = View.GONE
        }

        return view
    }

    private fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val currentFocus = activity.currentFocus
        currentFocus?.let {
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    private fun getCurrentLocation() {
        val context = requireContext()
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                if (ActivityCompat.checkSelfPermission(
                        context,
                        android.Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        context,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    // Handle missing permissions
                    return
                }
                fusedLocationProvider.lastLocation
                    .addOnSuccessListener { location ->
                        location?.let {
                            fetchCurrentLocationWeather(it.latitude.toString(), it.longitude.toString())
                        }
                    }
            } else {
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                requireActivity().startActivity(intent)
            }
        } else {
            requestPermission()
        }
    }


    private fun getCityWeather(city: String) {
        binding.progressBar.visibility = View.VISIBLE
        ApiUtilities.getApiInterface()?.getCityWeatherData(city, apikey)
            ?.enqueue(object : Callback<WeatherModel> {
                override fun onResponse(call: Call<WeatherModel>, response: Response<WeatherModel>) {
                    if (response.isSuccessful) {
                        binding.progressBar.visibility = View.GONE
                        response.body()?.let {
                            setData(it)
                        }
                    } else {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), "No City Found", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                    // Handle failure
                }
            })
    }

    private fun fetchCurrentLocationWeather(latitude: String, longitude: String) {
        ApiUtilities.getApiInterface()?.getCurrentWeatherData(latitude.toDouble(), longitude.toDouble(), apikey)
            ?.enqueue(object : Callback<WeatherModel> {
                override fun onResponse(call: Call<WeatherModel>, response: Response<WeatherModel>) {
                    if (response.isSuccessful) {
                        binding.progressBar.visibility = View.GONE
                        response.body()?.let {
                            setData(it)
                        }
                    }
                }

                override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                    // Handle failure
                }
            })
    }

    private fun requestPermission() {
        activity?.let { activity ->
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                LOCATION_REQUEST_CODE
            )
        }
    }


    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager = requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    private fun checkPermissions(): Boolean {
        val context = requireContext()
        return (ActivityCompat.checkSelfPermission(context,  permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation()
            }
        }
    }

    private fun setData(body: WeatherModel) {
        binding.apply {
            selectedLocation.text = body.name
            weatherTemperature.text = "${k2c(body.main?.temp!!)}°"
            weatherState.text = body.weather[0].main
            weatherHumidity.text = "${body.main?.humidity}%"
            weatherWindSpeed.text = "${body.wind?.speed}m/s"
        }
        updateUI(body.weather[0].id)
    }

    private fun k2c(temp: Double): Double {
        return (temp - 273.15).toBigDecimal().setScale(1, RoundingMode.UP).toDouble()
    }

    private fun updateUI(id: Int) {
        val drawableId = when (id) {
            in 200..232 -> R.drawable.ic_storm_weather
            in 300..321 -> R.drawable.ic_few_clouds
            in 500..531 -> R.drawable.ic_rainy_weather
            in 600..622 -> R.drawable.ic_snow_weather
            in 700..781 -> R.drawable.ic_broken_clouds
            800 -> R.drawable.ic_clear_day
            in 801..804 -> R.drawable.ic_cloudy_weather
            else -> R.drawable.ic_unknown
        }
        binding.weatherLogo.setImageResource(drawableId)
    }
}
