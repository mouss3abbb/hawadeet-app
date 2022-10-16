package com.example.hawadeet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.hadoota_item.view.*

class HawadeetAdapter(
    val hawadeetData: List<Hadoota>
): RecyclerView.Adapter<HawadeetAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val hadootaStatus = view.hadoota_status
        val hadootaBody = view.hadoota_body
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.hadoota_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            hadootaStatus.text = hawadeetData[position].status
            hadootaBody.text = hawadeetData[position].body
        }
    }

    override fun getItemCount(): Int = hawadeetData.size
}