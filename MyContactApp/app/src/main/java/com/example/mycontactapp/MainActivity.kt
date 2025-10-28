package com.example.mycontactapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycontactapp.data.AppDatabase
import com.example.mycontactapp.data.Contact
import com.example.mycontactapp.data.ContactDao
import com.example.mycontactapp.databinding.ActivityMainBinding
import com.example.mycontactapp.databinding.FormContactBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    //untuk mengambil dan mengelola data contact butuh interface atau model yang sudah dibuat
    private val contactDao by lazy { AppDatabase.get(this).contactDao() }

    private lateinit var contactViewAdapter: ContactViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contactViewAdapter = ContactViewAdapter(
            onEditClick = {contact -> showEditDialog(contact)},
            onDelete = {contact -> showDeleteDialog(contact)}
        )
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding){
            rvContact.apply {
                adapter = contactViewAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
            }

            //show form
            btnAdd.setOnClickListener {
                showAddDialog()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        refreshList()
    }

    private fun refreshList() {
        lifecycleScope.launch {
            //ambil data dari getAll tapi gunakan background thread ynag ditandai dispatcher
            var items = withContext(Dispatchers.IO) {
                contactDao.getAll()
            }

            //update data ke adapter
            contactViewAdapter.setItems(items)
        }
    }

    //fungsi untuk menampilkan dialog add contact
    private fun showAddDialog(){
        //binding ui form
        val binding = FormContactBinding.inflate(layoutInflater)
        //buat dialog
        var builder = AlertDialog.Builder(this@MainActivity)
        //setting view agar muncul formnya
        builder.setView(binding.root)
        //bikin positive button
        builder.setPositiveButton("Save") {dialog, which ->
            val name = binding.edtName.text.toString().trim()
            val phone = binding.edtPhone.text.toString().trim()

            if (name.isNotEmpty() && phone.isNotEmpty()) {
                //tambahkan ke database
                lifecycleScope.launch (Dispatchers.IO) {
                    contactDao.insert(Contact(name = name, phone = phone))
                    withContext(Dispatchers.Main){
                        refreshList()
                    }
                }
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "Nama dan Phone harus diisi",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        builder.setNeutralButton("Cancel"){dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }
    //fungsi untuk menampilkan edit form
    private fun showEditDialog(contact: Contact){
        var binding = FormContactBinding.inflate(layoutInflater)

        //isi form dengan data existing contact
        binding.edtName.setText(contact.name)
        binding.edtPhone.setText(contact.phone)

        var builder = AlertDialog.Builder(this@MainActivity)
        builder.setTitle("Edit Contact")
        builder.setView(binding.root)

        builder.setPositiveButton("Save") { dialog, which ->
            //ambil name dan phone dari form
            var name = binding.edtName.text.toString().trim()
            var phone = binding.edtPhone.text.toString().trim()

            if (name.isNotEmpty() && phone.isNotEmpty()) {
                lifecycleScope.launch(Dispatchers.IO) {
                    // update ke dabase
                    contactDao.update(contact.copy(name = name, phone = phone))

                    // minta UI untuk refresh data
                    withContext(Dispatchers.Main) { refreshList() }
                }

                dialog.dismiss()
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "Nama dan Phone harus diisi",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        builder.setNeutralButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        // dialog di show
        val dialog = builder.create()
        dialog.show()
    }
    //fungsi untuk menampilkan delete dialog
    private fun showDeleteDialog(contact: Contact){
        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setTitle("Delete ${contact.name}?")

        builder.setPositiveButton("Delete") { dialog, which ->
            lifecycleScope.launch(Dispatchers.IO) {
                contactDao.delete(contact)
                withContext(Dispatchers.Main) { refreshList() }
            }
            dialog.dismiss()
        }

        builder.setNeutralButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }
}