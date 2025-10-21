package com.example.mycourse

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mycourse.DataMateriSatu

class AdapterClass(private val dataList: ArrayList<DataMateriSatu>): RecyclerView.Adapter<AdapterClass.ViewHolderClass>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.materi_item, parent, false)
        return ViewHolderClass(itemView)
    }

    override fun onBindViewHolder(
        holder: ViewHolderClass,
        position: Int
    ) {
        val currentItem = dataList[position]
        holder.rvJudul.text = currentItem.dataJudul
        holder.rvDeskripsi.text = currentItem.dataDeskripsi
        holder.rvTanggal.text = currentItem.dataTanggal
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolderClass(itemView: View): RecyclerView.ViewHolder(itemView){
        val rvJudul: TextView = itemView.findViewById(R.id.judul)
        val rvDeskripsi: TextView = itemView.findViewById(R.id.deskripsi)
        val rvTanggal: TextView = itemView.findViewById(R.id.tanggal)
    }

}