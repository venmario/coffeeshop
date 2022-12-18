package com.example.s160419101_coffeshop_tycoon

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_preparation.*
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    val NAME = "ACC"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        var dayke = GlobalData.day
        textViewDayResult.text = "Day $dayke Result"
        var totalcup = intent.getStringExtra(PreparationActivity.JUMLAH_CUP).toString()
        var sellingprice = intent.getStringExtra(PreparationActivity.HARGA_PER_CUP).toString()
        var totalharga = intent.getStringExtra(SimulationActivity.TOTAL_HARGA).toString()

        var modal = intent.getStringExtra(PreparationActivity.MODAL).toString()

        var profit = Integer.parseInt(totalharga) - Integer.parseInt(modal)

        textViewResultTotalCup.text = totalcup.toString()
        textViewResultSellingPrice.text = sellingprice.toString()
        textViewResultTotalPrice.text = totalharga.toString()
        textViewResultModal.text = modal.toString()

        textViewResultProfit.text = profit.toString()

        GlobalData.balance = GlobalData.balance - Integer.parseInt(modal) + Integer.parseInt(totalharga)

        buttonStartNewDay.setOnClickListener {
            GlobalData.day = GlobalData.day + 1
            var sharedFile = packageName
            var shared: SharedPreferences = getSharedPreferences(sharedFile, Context.MODE_PRIVATE)
            var editor: SharedPreferences.Editor = shared.edit()
            val playersname = shared.getString(NAME,"")
            val intent = Intent(this, PreparationActivity::class.java)
            intent.putExtra(LoginActivity.PLAYER_NAME, playersname.toString())
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        finish()
    }
}