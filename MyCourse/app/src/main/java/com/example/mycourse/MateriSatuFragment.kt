package com.example.mycourse

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MateriSatuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MateriSatuFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<DataMateriSatu>
    private lateinit var judul: Array<String>
    private lateinit var deskripsi: Array<String>
    private lateinit var tanggal: Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_materi_satu, container, false)

        recyclerView = view.findViewById(R.id.rv_materi)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

        dataList = arrayListOf<DataMateriSatu>()
        getData()
        return view
    }

    private fun getData() {
        judul = arrayOf(
            "Pengenalan Activity dalam Android",
            "Siklus Hidup Activity (Lifecycle)",
            "Intent dan Navigasi antar Activity",
            "Konfigurasi Changes dan State Management",
            "Activity Launch Modes",
            "Shared Preferences dalam Activity",
            "Permissions Handling di Activity",
            "Activity Result API",
            "Fragments dalam Activity",
            "Best Practices Activity Development"
        )

        deskripsi = arrayOf(
            "Memahami konsep dasar Activity sebagai komponen fundamental...",
            "Mempelajari seluruh tahapan lifecycle Activity...",
            "Menggunakan Intent untuk berpindah antar Activity...",
            "Menangani perubahan konfigurasi dan menyimpan state...",
            "Memahami berbagai launch mode...",
            "Implementasi penyimpanan data sederhana...",
            "Meminta dan menangani runtime permissions...",
            "Mengganti startActivityForResult dengan Activity Result API...",
            "Implementasi dan komunikasi antara Fragment dan Activity...",
            "Pola desain dan best practices dalam pengembangan Activity..."
        )

        tanggal = arrayOf(
            "15 Januari 2024", "17 Januari 2024", "20 Januari 2024",
            "22 Januari 2024", "25 Januari 2024", "28 Januari 2024",
            "30 Januari 2024", "2 Februari 2024", "5 Februari 2024", "8 Februari 2024"
        )

        for (i in judul.indices) {
            val data = DataMateriSatu(judul[i], deskripsi[i], tanggal[i])
            dataList.add(data)
        }
        recyclerView.adapter = AdapterClass(dataList)
    }
}
