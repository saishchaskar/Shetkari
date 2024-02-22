package com.oneearth.shetkari.data

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import java.text.SimpleDateFormat
import java.util.Locale

class PriceDataFragment : Fragment() {

    private lateinit var viewModel: PriceDataViewModel
    private lateinit var lineChart: LineChart

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewModel = ViewModelProvider(this).get(PriceDataViewModel::class.java)

        viewModel.priceDataList.observe(viewLifecycleOwner, Observer { priceDataList ->
            // Update your line chart with the new data
            updateLineChart(priceDataList)
        })

        viewModel.getAllPriceData()
    }

    private fun updateLineChart(priceDataList: List<PriceData>) {
        val entries = mutableListOf<Entry>()

        // Assuming priceDate is in a format that can be converted to a timestamp
        priceDataList.forEachIndexed { _, priceData ->
            val timestamp = convertStringToTimestamp(priceData.priceDate)
            entries.add(Entry(timestamp.toFloat(), priceData.modalPrice.toFloat()))
        }

        val dataSet = LineDataSet(entries, "Modal Price")
        val lineData = LineData(dataSet)

        // Customize chart appearance if needed
        // ...

        lineChart.data = lineData
        lineChart.invalidate() // Refresh the chart
    }

    private fun convertStringToTimestamp(dateString: String): Long {
        // You need to implement the logic to convert your date string to a timestamp
        // This is just a placeholder, and you may need to adjust it based on your date format
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = dateFormat.parse(dateString)
        return date?.time ?: 0
    }

}
