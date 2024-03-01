package com.oneearth.shetkari

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.oneearth.shetkari.Adapter.ApmcAdapter
import com.oneearth.shetkari.data.APMCApi
import com.oneearth.shetkari.data.APMCCustomRecords
import com.oneearth.shetkari.data.APMCMain
import com.oneearth.shetkari.databinding.FragmentApmcBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import android.view.View as View1

/**
 * A simple [Fragment] subclass.
 * Use the [ApmcFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ApmcFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var adapter: ApmcAdapter
    var indexSpinner1: Int? = null
    var indexSpinner2: Int? = null
    var someMap: Map<Any, Array<String>>? = null
    var states: Array<String>? = null
    private lateinit var binding: FragmentApmcBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View1 {
        binding = FragmentApmcBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View1, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Access views using binding
        binding.progressApmc.visibility = View1.GONE
        binding.loadingTextAPMC.visibility = View1.GONE

        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar?.title = "APMC"

        binding.dateValueTextApmc.text = SimpleDateFormat("dd/MM/yyyy").format(Date()).toString()

        states = arrayOf(
            "Click Here",
            "Andhra Pradesh",
            "Bihar",
            "Chandigarh",
            "Gujarat",
            "Haryana",
            "Kerala",
            "Madhya Pradesh",
            "Maharashtra",
            "Odisha",
            "Punjab",
            "Rajasthan",
            "Tamil Nadu",
            "Telangana",
            "Uttar Pradesh",
            "Uttrakhand",
            "West Bengal"
        )

        var districtInGujarat: Array<String> = arrayOf(
            "Click Here to Select District",
            "Ahmedabad",
            "Amreli",
            "Anand",
            "Aravalli",
            "Banaskantha",
            "Bharuch",
            "Bhavnagar",
            "Botad",
            "Chhota Udepur",
            "Dahod",
            "Dangs",
            "Devbhoomi Dwarka",
            "Gandhinagar",
            "Gir Somnath",
            "Jamnagar",
            "Junagadh",
            "Kachchh",
            "Kheda",
            "Mahisagar",
            "Mehsana",
            "Morbi",
            "Narmada",
            "Navsari",
            "Panchmahal",
            "Patan",
            "Porbandar",
            "Rajkot",
            "Sabarkantha",
            "Surat",
            "Surendranagar",
            "Tapi",
            "Vadodara",
            "Valsad"

        )
        var districtInMaha: Array<String> = arrayOf(
            "Click Here to Select District",
            "Ahmednagar",
            "Akola",
            "Amravati",
            "Aurangabad",
            "Beed",
            "Bhandara",
            "Buldhana",
            "Chandrapur",
            "Dhule",
            "Gadchiroli",
            "Gondia",
            "Hingoli",
            "Jalgaon",
            "Jalana",
            "Kolhapur",
            "Latur",
            "Mumbai",
            "Nagpur",
            "Nanded",
            "Nandurbar",
            "Nashik",
            "Osmanabad",
            "Palghar",
            "Parbhani",
            "Pune",
            "Raigad",
            "Ratnagiri",
            "Sangli",
            "Satara",
            "Sindhudurg",
            "Solapur",
            "Thane",
            "Wardha",
            "Washim",
            "Yavatmal"
        )

        var districtInRajasthan: Array<String> = arrayOf(
            "Click Here to Select District",
            "Ajmer",
            "Alwar",
            "Banswara",
            "Baran",
            "Barmer",
            "Bharatpur",
            "Bhilwara",
            "Bikaner",
            "Bundi",
            "Chittorgarh",
            "Churu",
            "Dausa",
            "Dholpur",
            "Dungarpur",
            "Hanumangarh",
            "Jaipur",
            "Jaisalmer",
            "Jalore",
            "Jhalawar",
            "Jhunjhunu",
            "Jodhpur",
            "Karauli",
            "Kota",
            "Nagaur",
            "Pali",
            "Pratapgarh",
            "Rajsamand",
            "Sawai Madhopur",
            "Sikar",
            "Sirohi",
            "Sri Ganganagar",
            "Tonk",
            "Udaipur"
        )

        var districtInUttarPradesh: Array<String> = arrayOf(
            "Click Here to Select District",
            "Agra",
            "Aligarh",
            "Allahabad",
            "Ambedkar Nagar",
            "Amethi",
            "Amroha",
            "Auraiya",
            "Azamgarh",
            "Baghpat",
            "Bahraich",
            "Ballia",
            "Balrampur",
            "Banda",
            "Barabanki",
            "Bareilly",
            "Basti",
            "Bhadohi",
            "Bijnor",
            "Budaun",
            "Bulandshahr",
            "Chandauli",
            "Chitrakoot",
            "Deoria",
            "Etah",
            "Etawah",
            "Faizabad",
            "Farrukhabad",
            "Fatehpur",
            "Firozabad",
            "Gautam Buddha Nagar",
            "Ghaziabad",
            "Ghazipur",
            "Gonda",
            "Gorakhpur",
            "Hamirpur",
            "Hapur",
            "Hardoi",
            "Hathras",
            "Jalaun",
            "Jaunpur",
            "Jhansi",
            "Kannauj",
            "Kanpur Dehat",
            "Kanpur Nagar",
            "Kanshiram Nagar",
            "Kaushambi",
            "Kushinagar",
            "Lakhimpur - Kheri",
            "Lalitpur",
            "Lucknow",
            "Maharajganj",
            "Mahoba",
            "Mainpuri",
            "Mathura",
            "Mau",
            "Meerut",
            "Mirzapur",
            "Moradabad",
            "Muzaffarnagar",
            "Pilibhit",
            "Pratapgarh",
            "RaeBareli",
            "Rampur",
            "Saharanpur",
            "Sambhal",
            "Sant Kabir Nagar",
            "Shahjahanpur",
            "Shamali",
            "Shravasti",
            "Siddharth Nagar",
            "Sitapur",
            "Sonbhadra",
            "Sultanpur",
            "Unnao",
            "Varanasi"
        )

        var districtInWestBengal: Array<String> = arrayOf(
            "Click Here to Select District",
            "Alipurduar",
            "Bankura",
            "Birbhum",
            "Cooch Behar",
            "Dakshin Dinajpur",
            "Darjeeling",
            "Hooghly",
            "Howrah",
            "Jalpaiguri",
            "Jhargram",
            "Kalimpong",
            "Kolkata",
            "Malda",
            "Murshidabad",
            "Nadia",
            "North 24 Parganas",
            "Paschim Medinipur",
            "Paschim Burdwan",
            "Purba Burdwan",
            "Purba Medinipur",
            "Purulia",
            "South 24 Parganas",
            "Uttar Dinajpur"
        )

        var districtInKerala: Array<String> = arrayOf(
            "Click Here to Select District",
            "Alappuzha",
            "Ernakulam",
            "Idukki",
            "Kannur",
            "Kasaragod",
            "Kollam",
            "Kottayam",
            "Kozhikode",
            "Malappuram",
            "Palakkad",
            "Pathanamthitta",
            "Thiruvananthapuram",
            "Thrissur",
            "Wayanad"
        )

        var districtInAndhraPradesh: Array<String> = arrayOf(
            "Click Here to Select District",
            "Anantapur",
            "Chittoor",
            "East Godavari",
            "Guntur",
            "Krishna",
            "Kurnool",
            "Prakasam",
            "Srikakulam",
            "Sri Potti Sriramulu Nellore",
            "Visakhapatnam",
            "Vizianagaram",
            "West Godavari",
            "Kadapa"
        )

        var districtInChandigarh: Array<String> = arrayOf(
            "Click Here to Select District",
            "Chandigarh"
        )
        var districtInBihar: Array<String> = arrayOf(
            "Click Here to Select District",
            "Muzaffarpur",
            "Bhojpur"
        )
        var districtInMadhyaPradesh: Array<String> = arrayOf(
            "Click Here to Select District",
            "Seoni",
            "Dhar",
            "Narsinghpur",
            "Rajgarh",
            "Chhatarpur",
            "Chhindwara"
        )
        var districtInHaryana: Array<String> = arrayOf(
            "Click Here to Select District",
            "Rewari"
        )
        var districtInOdisha: Array<String> = arrayOf(
            "Click Here to Select District",
            "Balasore",
            "Boudh",
            "Dhenkanal",
            "Bolangir"
        )
        var districtInPunjab: Array<String> = arrayOf(
            "Click Here to Select District",
            "Amritsar",
            "Bhatinda",
            "Moga",
            "Mohali",
            "Ropar (Rupnagar)",
            "Tarntaran"
        )
        var districtInTamilNadu: Array<String> = arrayOf(
            "Click Here to Select District",
            "Dindigul",
            "Coimbatore"
        )
        var districtInTelangana: Array<String> = arrayOf(
            "Click Here to Select District",
            "Karimnagar"
        )
        var districtInUttrakhand: Array<String> = arrayOf(
            "Click Here to Select District",
            "Dehradoon"
        )


        var emptyDistricts : Array<String> = arrayOf("Click Here to Select District")

        var aa = ArrayAdapter(
            activity!!.applicationContext,
            android.R.layout.simple_spinner_dropdown_item,
            states!!
        )

        binding.spinner1.adapter = aa


        someMap = mapOf(
            "Andhra Pradesh" to districtInAndhraPradesh,
            "Gujarat" to districtInGujarat,
            "Kerala" to districtInKerala,
            "Maharashtra" to districtInMaha,
            "Rajasthan" to districtInRajasthan,
            "Uttar Pradesh" to districtInUttarPradesh,
            "West Bengal" to districtInWestBengal,
            "Bihar" to districtInBihar,
            "Madhya Pradesh" to districtInMadhyaPradesh,
            "Chandigarh" to districtInChandigarh,
            "Haryana" to districtInHaryana,
            "Odisha" to districtInOdisha,
            "Punjab" to districtInPunjab,
            "Tamil Nadu" to districtInTamilNadu,
            "Telangana" to districtInTelangana,
            "Uttrakhand" to districtInUttrakhand
        )


        binding.spinner1.onItemSelectedListener = object :
            AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View1?, p2: Int, p3: Long) {
                if (p2 == 0) {
                    binding.textAPMCWarning.text = "Please Select State and District"
                  binding.recycleAPMC.visibility = View1.GONE
                    binding.textAPMCWarning.visibility = View1.VISIBLE
                } else {
                    var aa2 = ArrayAdapter(
                        activity!!.applicationContext,
                        android.R.layout.simple_spinner_dropdown_item,

                        someMap!![states!![p2]]!!
                    )

                    indexSpinner1 = p2
                    binding.spinner2.adapter = aa2
                    aa2.notifyDataSetChanged()
                }
            }

            override fun onItemClick(p0: AdapterView<*>?, p1: View1?, p2: Int, p3: Long) {
            }
        }

        binding.spinner2.onItemSelectedListener = object :
            AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View1?, p2: Int, p3: Long) {
                if (p2 == 0) {
                    binding.textAPMCWarning.text = "Please Select District"
                    binding.recycleAPMC.visibility = View1.GONE
                    binding.textAPMCWarning.visibility = View1.VISIBLE
                } else {
                    binding.textAPMCWarning.visibility = View1.GONE
                    if (p2 != 0) {
                        getApmc("${someMap!![states!![indexSpinner1!!]]!![p2]}")
                    }
                    indexSpinner2 = p2
                    binding.progressApmc.visibility = View1.VISIBLE
                    binding.loadingTextAPMC.visibility = View1.VISIBLE
                }
            }

            override fun onItemClick(p0: AdapterView<*>?, p1: View1?, p2: Int, p3: Long) {
            }
        }

    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ApmcFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    private fun getApmc(district: String) {
        val apmc1: Call<APMCMain> = APMCApi.apmcInstances.getapmc(20)
        var apmc2: Call<APMCMain>? = null
        if (indexSpinner2 != 0) {

            apmc2 = APMCApi.apmcInstances.getSomeData(district)
            Log.d("APMC District", district)

            apmc2.enqueue(object : Callback<APMCMain> {
                override fun onFailure(call: Call<APMCMain>, t: Throwable) {
                    Log.d("fetch", "Failed", t)
                    binding.progressApmc.visibility = View1.GONE
                    binding.loadingTextAPMC.visibility = View1.GONE
                }

                override fun onResponse(
                    call: Call<APMCMain>,
                    response: Response<APMCMain>
                ) {

                    val apmcdata = response.body()
                    if (apmcdata != null) {

                        val calendar = Calendar.getInstance()
                        val updatedYear = calendar.get(Calendar.YEAR)
                        val updatedMonth = calendar.get(Calendar.MONTH) + 1 // Note: Calendar months are 0-indexed
                        val updatedDate = calendar.get(Calendar.DAY_OF_MONTH)


                        binding.dateValueTextApmc.text = "$updatedDate/$updatedMonth/$updatedYear"
                        if (apmcdata.records.size == 0) {
                            binding.progressApmc.visibility = View1.GONE
                            binding.loadingTextAPMC.visibility = View1.GONE
                            binding.textAPMCWarning.visibility = View1.VISIBLE
                            binding.recycleAPMC.visibility = View1.GONE
                            binding.textAPMCWarning.text = "No records found!"
                        } else {
                            binding.textAPMCWarning.visibility = View1.GONE
                            binding.recycleAPMC.visibility = View1.VISIBLE
                            Log.d("APMCFrag", apmcdata.records.toString())

                            val totalRecords = apmcdata.records.size
                            var firstMarket = ""
                            if (!apmcdata.records[0].market.isNullOrEmpty()) {
                                firstMarket = apmcdata.records[0].market.toString()
                            }

                            val customRecords = ArrayList<APMCCustomRecords>()

                            val list1 = mutableListOf<String>()
                            val list2 = mutableListOf<String>()
                            val list3 = mutableListOf<String>()
                            list1.add(apmcdata.records[0].commodity)
                            list2.add(apmcdata.records[0].min_price)
                            list3.add(apmcdata.records[0].max_price)

                            var previousRecord = APMCCustomRecords(
                                apmcdata.records[0].state,
                                apmcdata.records[0].district,
                                apmcdata.records[0].market,
                                list1,
                                list2,
                                list3
                            )


                            Log.d("PreREc", previousRecord.toString())

                            if (totalRecords == 1) {
                                customRecords.add(previousRecord)
                            } else {
                                var count = 0
                                for (i in 1..totalRecords - 1) {

                                    if (apmcdata.records[i].market == previousRecord.market) {
                                        previousRecord.commodity.add(apmcdata.records[i].commodity)
                                        previousRecord.min_price.add(apmcdata.records[i].min_price)
                                        previousRecord.max_price.add(apmcdata.records[i].max_price)
                                        count = 1
                                    } else {
                                        count = 0
                                        customRecords.add(previousRecord)
                                        list1.add(apmcdata.records[i].commodity)
                                        list2.add(apmcdata.records[i].min_price)
                                        list3.add(apmcdata.records[i].max_price)
                                        previousRecord = APMCCustomRecords(
                                            apmcdata.records[i].state,
                                            apmcdata.records[i].district,
                                            apmcdata.records[i].market,
                                            list1,
                                            list2,
                                            list3
                                        )
                                    }
                                }
                                if (count == 1) {
                                    Log.d("LastRec", "Yes")
                                    customRecords.add(previousRecord)
                                }
                            }

                            Log.d("New APMC Data", customRecords.toString())
                            Log.d("Old APMC Data", apmcdata.toString())

                            adapter = ApmcAdapter(activity!!.applicationContext, customRecords)
                            binding.recycleAPMC.adapter = adapter
                            binding.recycleAPMC.layoutManager =
                                LinearLayoutManager(activity!!.applicationContext)
                            binding.progressApmc.visibility = View1.GONE
                            binding.loadingTextAPMC.visibility = View1.GONE
                            Log.d("bharat222", apmcdata.toString())
                        }

                    }
                }

            })

        }
    }
}