package com.oneearth.shetkari

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PricesAdapter(private val prices: ArrayList<PriceItem>) : RecyclerView.Adapter<PricesAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val stateTextView: TextView = itemView.findViewById(R.id.stateTextView)
        val districtTextView: TextView = itemView.findViewById(R.id.districtTextView)
        val marketTextView: TextView = itemView.findViewById(R.id.marketTextView)
        val commodityTextView: TextView = itemView.findViewById(R.id.commodityTextView)
        val varietyTextView: TextView = itemView.findViewById(R.id.varietyTextView)
        val gradeTextView: TextView = itemView.findViewById(R.id.gradeTextView)
        val arrivalDateTextView: TextView = itemView.findViewById(R.id.arrivalDateTextView)
        val minPriceTextView: TextView = itemView.findViewById(R.id.minPriceTextView)
        val maxPriceTextView: TextView = itemView.findViewById(R.id.maxPriceTextView)
        val modalPriceTextView: TextView = itemView.findViewById(R.id.modalPriceTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_price, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val price = prices[position]
        holder.stateTextView.text = "State: ${price.state}"
        holder.districtTextView.text = "District: ${price.district}"
        holder.marketTextView.text = "Market: ${price.market}"
        holder.commodityTextView.text = "Commodity: ${price.commodity}"
        holder.varietyTextView.text = "Variety: ${price.variety}"
        holder.gradeTextView.text = "Grade: ${price.grade}"
        holder.arrivalDateTextView.text = "Arrival Date: ${price.arrivalDate}"
        holder.minPriceTextView.text = "Min Price: ${price.minPrice}"
        holder.maxPriceTextView.text = "Max Price: ${price.maxPrice}"
        holder.modalPriceTextView.text = "Modal Price: ${price.modalPrice}"
    }

    override fun getItemCount(): Int {
        return prices.size
    }
}
