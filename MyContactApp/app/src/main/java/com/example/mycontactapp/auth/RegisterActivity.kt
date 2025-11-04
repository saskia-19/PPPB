package com.example.mycontactapp.auth

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mycontactapp.MainActivity
import com.example.mycontactapp.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        with(binding) {
            btnRegister.setOnClickListener {
                val username = edtUsername.text.toString()
                val password = edtPassword.text.toString()

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(this@RegisterActivity, "Isi semua kolom!", Toast.LENGTH_SHORT).show()
                } else {
                    sharedPreferences.edit().apply {
                        putString("username", username)
                        putString("password", password)
                        putBoolean("isLoggedIn", true)
                        apply()
                    }
                    startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
                    finish()
                }
            }

            txtLogin.setOnClickListener {
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
            }
        }
    }
}