package com.ceyda.androidbootcampodev.tvtimeproto.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ceyda.androidbootcampodev.tvtimeproto.R
import com.ceyda.androidbootcampodev.tvtimeproto.model.Content

class ContentAdapter<T : Content>(private var contentList: List<T>) : 
    RecyclerView.Adapter<ContentAdapter<T>.ContentViewHolder>() {
    
    inner class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_content, parent, false)
        return ContentViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        val content = contentList[position]

        // Basit görsel yükleme - varsayılan görsel kullan
        holder.imageView.setImageResource(R.drawable.ic_launcher_background)

        // Başlık metnini ayarla
        holder.titleTextView.text = content.title
    }
    
    override fun getItemCount(): Int = contentList.size
    
    fun updateData(newContentList: List<T>) {
        contentList = newContentList
        notifyDataSetChanged()
    }
}
