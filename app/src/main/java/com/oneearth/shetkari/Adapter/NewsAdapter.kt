package com.oneearth.shetkari.Adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.oneearth.shetkari.Models.Article
import com.oneearth.shetkari.R

class NewsAdapter : ListAdapter<Article, NewsAdapter.NewsViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = getItem(position)
        holder.bind(article)
    }

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        private val publishedAtTextView: TextView = itemView.findViewById(R.id.publishedAtTextView)
        private val newsImageView: ImageView = itemView.findViewById(R.id.newsImageView)
        private val urlTextView: TextView = itemView.findViewById(R.id.urlTextView)

        init {
            // Add click listener to the whole item view
            itemView.setOnClickListener {
                val article = getItem(adapterPosition)
                openUrl(itemView.context, article.url)
            }
        }

        fun bind(article: Article) {
            itemView.apply {
                // Display news data
                titleTextView.text = article.title
                descriptionTextView.text = article.description
                publishedAtTextView.text = article.publishedAt

                // Load image using Glide library
                Glide.with(context)
                    .load(article.urlToImage)
                    .placeholder(R.drawable.loading) // Placeholder image
                    .error(R.drawable.ic_unknown) // Error image
                    .diskCacheStrategy(DiskCacheStrategy.DATA) // Cache strategy
                    .into(newsImageView)

                // Set URL text
                urlTextView.text = article.url
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url // Assuming URL is unique for each article
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    private fun openUrl(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }
}
