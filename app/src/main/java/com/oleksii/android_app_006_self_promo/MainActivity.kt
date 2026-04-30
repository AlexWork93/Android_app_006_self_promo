package com.oleksii.android_app_006_self_promo

import android.R
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.oleksii.android_app_006_self_promo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonPreviewMessage.setOnClickListener {
            val message = getValues()
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            openPreviewActivity(message)
        }
    }

    private fun getValues(): String {
        val userName = binding.editTextContactName.text?.toString()
        val userPhone = binding.editTextContactNumber.text?.toString()
        val position = binding.spinnerDropdown.selectedItem?.toString()
        val isJunior = binding.checkboxIsJunior.isChecked
        val startFrom = binding.editTextStartFrom.text?.toString()

        val res = userPhone?.toIntOrNull()
        if (res != null) {
            Toast.makeText(this, res.toString(), Toast.LENGTH_LONG).show()
        }

        return "Name: $userName\n Phone: $userPhone\n ${if (isJunior) "Junior" else "Senior"}"
    }
    private fun openPreviewActivity(message: String){
        val intent = Intent(this, MessagePreviewActivity::class.java)
        intent.putExtra("MESSAGE",message)
        startActivity(intent)
    }

}