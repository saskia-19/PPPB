package com.example.mycontactapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mycontactapp.databinding.ItemContactBinding
import com.example.mycontactapp.data.Contact

class ContactViewAdapter(
    private val onEditClick: (Contact) -> Unit,
    private val onDelete: (Contact) -> Unit
) : RecyclerView.Adapter<ContactViewAdapter.ItemContactViewHolder>() {

    private val contacts = mutableListOf<Contact>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemContactViewHolder {
        val binding = ItemContactBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemContactViewHolder, position: Int) {
        holder.bind(contacts[position])
    }

    override fun getItemCount(): Int = contacts.size

    inner class ItemContactViewHolder(val binding: ItemContactBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(contact: Contact) {
            with(binding) {
                txtName.text = contact.name
                txtPhone.text = contact.phone

                btnEdit.setOnClickListener { onEditClick(contact) }
                btnDelete.setOnClickListener { onDelete(contact) }
            }
        }
    }

    fun setItems(newData: List<Contact>) {
        //hapus data sebelumnya
        contacts.clear()
        //tambahkan data baru
        contacts.addAll(newData)
        //notify UI
        notifyDataSetChanged()
    }

}
