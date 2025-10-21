package com.example.mycourse

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mycourse.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        with(binding){

        }
    }

    //function untuk menampilkan menu di dalam action bar
    // untul activity terkait / this
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //setting menu / load menu
        menuInflater.inflate(R.menu.menu_options, menu)
        //kasih tau activity bahwa ada menu yang ingin ditampilkan
        return true
    }

    // function untuk menghandle jika menu dipilih oleh user
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_home -> {
                Toast.makeText(this@MainActivity, "Home Menu Selected", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_materi -> {
                //action untuk materi
                //buat navigasi ke Activity baru (MateriActivity)
                Toast.makeText(this@MainActivity, "Materi Menu Selected", Toast.LENGTH_SHORT).show()

                var intent = Intent(this@MainActivity, MateriActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_quiz -> {
                Toast.makeText(this@MainActivity, "Quiz Menu Selected", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}