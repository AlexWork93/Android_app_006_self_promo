package com.oleksii.android_app_006_self_promo

import android.R
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.oleksii.android_app_006_self_promo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var listOfPositions = listOf("Android developer", "Software tester", "Janitor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupListener()

        val positionsAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listOfPositions)
        binding.spinnerDropdown.adapter = positionsAdapter
    }

    private fun setupListener() {
        binding.buttonPreviewMessage.setOnClickListener {
            val message = getValues()
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            openPreviewActivity(message)
        }
    }

    private fun setupBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    private fun getValues(): String {
        val userName = binding.editTextContactName.text?.toString()
        val userPhone = binding.editTextContactNumber.text?.toString()
        val userDisplayName = binding.editTextContactDisplayName.text?.toString()
        val isJunior = binding.checkboxIsJunior.isChecked
        val position = binding.spinnerDropdown.selectedItem?.toString()
        val startFrom = binding.editTextStartFrom.text?.toString()


        return " Name: $userName\n Phone: $userPhone\n ${if (isJunior) "Junior" else "Senior"}\n Position: $position"
    }
    private fun openPreviewActivity(message: String){
        val intent = Intent(this, MessagePreviewActivity::class.java)
        intent.putExtra("MESSAGE",message)
        startActivity(intent)
    }

}