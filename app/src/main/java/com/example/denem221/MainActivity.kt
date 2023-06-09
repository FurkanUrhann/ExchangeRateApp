package com.example.denem221

import android.os.Bundle
import android.os.StrictMode
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.denem221.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private val xmlResult = XmlResult()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ArrayAdapter.createFromResource(
            this,
            R.array.currencies_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinner.adapter = adapter
        }

        binding.spinner.onItemSelectedListener = this
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        val currency = parent?.getItemAtPosition(pos).toString()

        if (currency == "Seçiniz") {
            binding.ForexBuying.text = "-"
            binding.ForexSelling.text = "-"
            return
        }

        val currenciesList = xmlResult.xmlCurrency()
        val selectedCurrency = currenciesList.find { it.Isim == currency }

        binding.ForexBuying.text = selectedCurrency?.ForexBuying ?: "-"
        binding.ForexSelling.text = selectedCurrency?.ForexSelling ?: "-"
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
}


