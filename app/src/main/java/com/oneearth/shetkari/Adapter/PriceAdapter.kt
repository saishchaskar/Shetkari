package com.oneearth.shetkari.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.oneearth.shetkari.R
import com.oneearth.shetkari.data.Record

class PriceAdapter(private val records: List<Record>) :
    RecyclerView.Adapter<PriceAdapter.PriceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PriceViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_price, parent, false)
        return PriceViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PriceViewHolder, position: Int) {
        val currentItem = records[position]
        holder.commodityTextView.text = currentItem.commodity
        holder.minPriceTextView.text = "Min: ${currentItem.min_price}"
        holder.maxPriceTextView.text = "Max: ${currentItem.max_price}"
    }

    override fun getItemCount() = records.size

    class PriceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val commodityTextView: TextView = itemView.findViewById(R.id.comodity)
        val minPriceTextView: TextView = itemView.findViewById(R.id.min)
        val maxPriceTextView: TextView = itemView.findViewById(R.id.max)
    }
}
