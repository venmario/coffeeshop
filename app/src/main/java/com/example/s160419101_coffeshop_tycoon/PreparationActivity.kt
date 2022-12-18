package com.example.s160419101_coffeshop_tycoon

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_preparation.*

class PreparationActivity : AppCompatActivity() {
    val universityprice: Int = 100000
    val businessprice: Int = 350000
    val beachprice: Int = 500000

    var balance = GlobalData.balance
    val coffeeprice: Int = 500
    val milkprice: Int = 1000
    val waterprice: Int = 200
    var water: Int = 0
    var coffee: Int = 0
    var milk: Int = 0
    var sellingPrice: Int =0
    var totalcupcoffee: Int =0
    var locationprice: Int = 100000
    var grandtotal:Int = 0
    var pricencupofcoffee: Int =0
    var priceacupofcoffee: Int =0
    var cupterjual: Int = 0

    companion object {
        val MODAL = "MODAL"
        val JUMLAH_CUP = "JUMLAH_CUP"
        val HARGA_PER_CUP = "HARGA_PER_CUP"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preparation)

        var weathers:ArrayList<String> = arrayListOf("Thunderstorm", "Sunny Day" ,"Rainy Day")
        val rand = (0..2).random()
        var weather =  weathers[rand]
        GlobalData.weather = weather

        textViewWeather.text = weather.toString()
        var dayke = GlobalData.day
        textViewDay.text = "Day $dayke"


        val locations = arrayOf("University","Business District","Beach")
        val arrayAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item, locations)

        spinnerLocation.adapter = arrayAdapter

        var playersname: String = intent.getStringExtra(LoginActivity.PLAYER_NAME).toString()
        textViewNama.text = "Welcome, $playersname"

        textViewBalance.text = balance.toString()

        editTextCoffee.setText(coffee.toString())
        editTextMilk.setText(milk.toString())
        editTextWater.setText(water.toString())

        buttonAddCoffee.setOnClickListener {
            coffee= Integer.parseInt(editTextCoffee.text.toString())
            coffee = coffee + 1
            editTextCoffee.setText(coffee.toString())
            getGrandTotalPrice()
        }

        buttonMinCoffee.setOnClickListener {
            coffee= Integer.parseInt(editTextCoffee.text.toString())
            if (coffee != 0){
                coffee = coffee - 1
                editTextCoffee.setText(coffee.toString())
                getGrandTotalPrice()
            }
        }

        buttonAddMilk.setOnClickListener {
            milk = Integer.parseInt(editTextMilk.text.toString())
            milk = milk + 1
            editTextMilk.setText(milk.toString())
            getGrandTotalPrice()
        }
        buttonMinMilk.setOnClickListener {
            milk = Integer.parseInt(editTextMilk.text.toString())
            if (milk != 0){
                milk = milk - 1
                editTextMilk.setText(milk.toString())
                getGrandTotalPrice()
            }
        }

        buttonAddWater.setOnClickListener {
            water = Integer.parseInt(editTextWater.text.toString())
            water = water + 1
            editTextWater.setText(water.toString())
            getGrandTotalPrice()
        }
        buttonMinWater.setOnClickListener {
            water = Integer.parseInt(editTextWater.text.toString())
            if (water != 0){
                water = water - 1
                editTextWater.setText(water.toString())
                getGrandTotalPrice()
            }
        }

        editTextCoffee.addTextChangedListener {
            if (editTextCoffee.text.toString().isNotEmpty()){
                coffee = Integer.parseInt(editTextCoffee.text.toString())
                getGrandTotalPrice()
            }
        }

        editTextMilk.addTextChangedListener {
            if (editTextMilk.text.toString().isNotEmpty()){
                milk = Integer.parseInt(editTextMilk.text.toString())
                getGrandTotalPrice()
            }
        }

        editTextWater.addTextChangedListener {
            if (editTextWater.text.toString().isNotEmpty()){
                water = Integer.parseInt(editTextWater.text.toString())
                getGrandTotalPrice()
            }
        }

        editTextTotalCup.addTextChangedListener {
            if (editTextTotalCup.text.toString().isNotEmpty()){
                totalcupcoffee = Integer.parseInt(editTextTotalCup.text.toString())
                getGrandTotalPrice()
            }
        }

        editTextSetSellingPrice.addTextChangedListener {
            if (editTextSetSellingPrice.text.toString().isNotEmpty()){
                sellingPrice = Integer.parseInt(editTextSetSellingPrice.text.toString())
                getGrandTotalPrice()
            }
        }

        sellingPrice = Integer.parseInt(editTextSetSellingPrice.text.toString())
        totalcupcoffee = Integer.parseInt(editTextTotalCup.text.toString())

        spinnerLocation.onItemSelectedListener = object :

            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (locations[p2] == "University"){
                    locationprice = universityprice
                    textViewLocationRent.setText(locations[p2])
                }
                else if(locations[p2] == "Business District"){
                    locationprice = businessprice
                    textViewLocationRent.setText(locations[p2])
                }
                else{
                    locationprice = beachprice
                    textViewLocationRent.setText(locations[p2])
                }
                getGrandTotalPrice()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }

        buttonStart.setOnClickListener {
            GlobalData.simulation.clear()
            cupterjual = totalcupcoffee
            for (i in 7..18){
                val rnds = (0..10).random()
                val _strFormat = String.format("%02d", i)
                var terjual : Int = 0
                var customers: String = ""
                if (cupterjual >= rnds){
                    terjual = rnds
                    cupterjual = cupterjual - terjual
                    if (rnds > 0)
                        customers = "$terjual customers"
                    else
                        customers = "No customer"
                }else {
                    customers = "Out of stock"
                }
                Log.d("total" , cupterjual.toString())
                val strsimulation = "$_strFormat.00 - $customers"
                GlobalData.simulation.add(strsimulation)
            }
            val intent = Intent(this, SimulationActivity::class.java)
            intent.putExtra(MODAL, grandtotal.toString())
            intent.putExtra(JUMLAH_CUP, (totalcupcoffee - cupterjual).toString())
            intent.putExtra(HARGA_PER_CUP, sellingPrice.toString())
            startActivity(intent)
//            Log.d("grandtotal", grandtotal.toString())
//            Log.d("total", totalcupcoffee.toString())
//            Log.d("sell", sellingPrice.toString())
        }
    }

    fun getGrandTotalPrice(){
        priceacupofcoffee = (coffee * coffeeprice) + (milk * milkprice) + (water * waterprice)
        textViewTotalPriceCoffee.text = priceacupofcoffee.toString()
        textViewEachPriceOfCoffee.text = "@IDR $priceacupofcoffee"
        textViewTotalCup.text = "$totalcupcoffee "

        pricencupofcoffee= totalcupcoffee * priceacupofcoffee
        textViewTotalPrice.text = "IDR $pricencupofcoffee"

        textViewLocationPrice.text = "IDR $locationprice"

        grandtotal = pricencupofcoffee + locationprice
        textViewGrandTotal.text = "IDR $grandtotal"

        buttonStart.isEnabled = grandtotal <= balance
    }

    override fun onBackPressed() {
        finish()
    }
}