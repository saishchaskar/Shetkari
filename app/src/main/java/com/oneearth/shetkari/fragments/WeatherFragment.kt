package com.oneearth.shetkari.fragments


//import androidx.compose.ui.tooling.data.EmptyGroup.location
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.oneearth.shetkari.R
import com.oneearth.shetkari.databinding.FragmentWeatherBinding



@Suppress("UNREACHABLE_CODE", "DEPRECATION")
class WeatherFragment:  Fragment() {

    private lateinit var binding: FragmentWeatherBinding
    private lateinit var currentLocation: Location
    private lateinit var fusedLocationProvider: FusedLocationProviderClient
    private val LOCATION_REQUEST_CODE = 101
    private val apikey = "886a7d1566beb94eb05472676ea817b0"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather, container, false)

//        binding = DataBindingUtil.setContentView(this, R.layout.fragment_weather)
//
//        fusedLocationProvider = LocationServices.getFusedLocationProviderClient(this)

//        getCurrentLocation()
//
//        binding.locationSearch.setOnEditorActionListener { textView, i, keyEvent ->
//
//            if (i == EditorInfo.IME_ACTION_SEARCH) {
//
//                getCityWeather(binding.locationSearch.text.toString())
//                binding.locationSearch.text.clear()
//                val view = this.currentFocus
//                if (view != null) {
//                    val imm: InputMethodManager = getSystemService(INPUT_METHOD_SERVICE)
//                            as InputMethodManager
//
//                    imm.hideSoftInputFromWindow(view.windowToken, 0)
//                    binding.locationSearch.clearFocus()
//                }
//
//                return@setOnEditorActionListener true
//            } else {
//                return@setOnEditorActionListener false
//            }
//
//
//        }
//
//        binding.currentLocation.setOnClickListener {
//
//            getCurrentLocation()
//
//        }
//
//        binding.searchOption.setOnClickListener {
//
//            binding.locationOptionBar.visibility = View.VISIBLE
//            binding.locationSearchBar.visibility = View.GONE
//        }
//
//        binding.back.setOnClickListener {
//            hidekeyboard(this)
//        }
//    }
//
//    private fun hidekeyboard(fragment: WeatherFragment) {
//
//        val imm = activity!!.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
//
//        var view: View?
//
//        if (view = null) {
//
//            view = View(activity)
//        }
//        imm.hideSoftInputFromWindow(view!!.windowToken, 0)
//
//    }
//
//    private fun getCurrentLocation(){
//
//        if (checkPermissions()){
//
//            if (isLocationEnabled()){
//
//                if (ActivityCompat.checkSelfPermission(this,
//                    Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED
//                    && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
//
//                      requestPermission()
//                    return
//
//                }
//
//                fusedLocationProvider.lastLocation
//                    .addOnSuccessListener {location->
//
//                        if (location!=null){
//
//                            currentLocation=location
//                            binding.progressBar.visibility= View.VISIBLE
//
//                          fetchCurrentLocationWeather(
//                              location.latitude.toString(),
//                              location.longitude.toString()
//
//                          )
//
//                        }
//
//                    }
//
//            }
//
//            else{
//
//                val intent= Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
//                startActivity(intent)
//
//            }
//        }
//        else{
//            requestPermission()
//        }
//    }
//
//private fun getCityWeather(city:String) {
//    binding.progressBar.visibility = View.VISIBLE
//
//    ApiUtilities.getApiInterface()?.getCityWeatherData(city, apikey)
//        ?.enqueue(object : Callback<WeatherModel>, retrofit2.Callback<WeatherModel> {
//            @RequiresApi(Build.VERSION_CODES.0)
//            override fun onResponse(call: Call<WeatherModel>, response: Response<WeatherModel>
//            ) {
//
//                if (response.isSuccessful){
//
//                    binding.locationOptionBar.visibility= View.VISIBLE
//                    binding.locationSearchBar.visibility= View.GONE
//                    binding.progressBar.visibility=View.GONE
//
//                    response.body()?.let {
//
//                        setData(it)
//                    }
//
//
//                }
//                else{
//                    binding.locationOptionBar.visibility= View.VISIBLE
//                    binding.locationSearchBar.visibility= View.GONE
//                    binding.progressBar.visibility=View.GONE
//
//                    Toast.makeText(this@WeatherFragment,"No City Found",Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
//
//            }
//
//
//        })
//
//}
//
//    private fun fetchCurrentLocationWeather(latitude:String,longitude:String){
//
//        ApiUtilities.getApiInterface()?.getCurrentWeatherData(latitude,longitude,apikey)
//            ?.enqueue(object :Callback<WeatherModel>, retrofit2.Callback<WeatherModel> {
//                @RequiresApi(Build.VERSION_CODES.0)
//                override fun onResponse(
//                    call: Call<WeatherModel>,
//                    response: Response<WeatherModel>
//                ) {
//                    if (response.isSuccessful){
//
//                        binding.progressBar.visibility=View.GONE
//
//                        response.body()?.let {
//
//                            setData(it)
//                        }
//                    }
//
//
//                }
//
//                override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
//                }
//
//            })
//    }
//
//    private fun requestPermission(){
//
//        ActivityCompat.requestPermissions(this,
//            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,
//                Manifest.permission.ACCESS_FINE_LOCATION),LOCATION_REQUEST_CODE)
//
//
//    }
//
//    private fun isLocationEnabled():Boolean{
//
//        val locationManager:LocationManager= getSystemService(Context.LOCATION_SERVICE)
//        as LocationManager
//
//        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
//                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
//    }
//
//    private fun checkPermissions():Boolean {
//
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
//            == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                this, Manifest.permission.ACCESS_FINE_LOCATION
//            )== PackageManager.PERMISSION_GRANTED){
//
//
//            return  true
//
//        }
//        return false
//
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ){
//        super.onRequestPermissionsResult(requestCode,permissions,grantResults)
//
//        if (requestCode==LOCATION_REQUEST_CODE){
//
//            if (grantResults.isNotEmpty()&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
//
//                getCurrentLocation()
//        }
//            else{
//
//            }
//
//        }
//    }
//@RequiresApi(Build.VERSION_CODES.O)
//private fun setData(body:WeatherModel){
//
//    binding.apply {
//        selectedLocation.text=body.name
//        weatherTemperature.text=k2c(body?.main?.temp!!)+"°"
//        weatherState.text=body.weather[0]
//    }
//
}


}