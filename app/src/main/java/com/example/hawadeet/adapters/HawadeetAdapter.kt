package com.example.hawadeet.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.hawadeet.Hadoota
import com.example.hawadeet.R
import kotlinx.android.synthetic.main.hadoota_item.view.*

class HawadeetAdapter(
    val hawadeetData: List<Hadoota>
): RecyclerView.Adapter<HawadeetAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val hadootaStatus = view.hadoota_status
        val hadootaBody = view.hadoota_body
        val shareButton = view.share_button
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
            if (hadootaStatus.text == ""){
                shareButton.visibility = View.INVISIBLE
            }
            shareButton.setOnClickListener {
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    type = "text/plain"
                    putExtra(Intent.EXTRA_SUBJECT,"Share Hadoota")
                    putExtra(Intent.EXTRA_TEXT,hadootaBody.text.toString()+"\n\nFeeling "+hadootaStatus.text.toString())
                }
                startActivity(it.context, Intent.createChooser(shareIntent,null),null)
            }
        }

    }

    override fun getItemCount(): Int = hawadeetData.size
}