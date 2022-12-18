package com.example.s160419101_coffeshop_tycoon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_simulation.*

class SimulationActivity : AppCompatActivity() {
    companion object {
        val TOTAL_HARGA = "TOTAL_HARGA"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simulation)

        val lm: LinearLayoutManager = LinearLayoutManager(this).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
        with(recyclerView){
            layoutManager = lm
            setHasFixedSize(true)
            adapter = SimulationAdapter(this@SimulationActivity)
        }

        textViewDaySimulation.text = GlobalData.day.toString()
        textViewWeatherSimulation.text = GlobalData.weather.toString()

        var grandtotal = intent.getStringExtra(PreparationActivity.MODAL).toString()
        var totalcupcoffee = intent.getStringExtra(PreparationActivity.JUMLAH_CUP).toString()
        var sellingPrice = intent.getStringExtra(PreparationActivity.HARGA_PER_CUP).toString()
        var totalHarga = Integer.parseInt(totalcupcoffee) * Integer.parseInt(sellingPrice)
//        var totalHarga =0
            Log.d("grandtotal", grandtotal.toString())
            Log.d("total", totalcupcoffee.toString())
            Log.d("sell", sellingPrice.toString())
        buttonResult.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra(PreparationActivity.MODAL, grandtotal.toString())
            intent.putExtra(PreparationActivity.JUMLAH_CUP, totalcupcoffee.toString())
            intent.putExtra(PreparationActivity.HARGA_PER_CUP, sellingPrice.toString())
            intent.putExtra(TOTAL_HARGA, totalHarga.toString())
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        finish()
    }
}