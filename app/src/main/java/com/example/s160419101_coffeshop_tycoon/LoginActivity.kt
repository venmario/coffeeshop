package com.example.s160419101_coffeshop_tycoon

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    val NAME = "ACC"
    val USER = "USER"
    val PASS = "PASS"

    companion object {
        val PLAYER_NAME = "PLAYER_NAME"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var sharedFile = packageName
        var shared: SharedPreferences = getSharedPreferences(sharedFile, Context.MODE_PRIVATE)
        var editor: SharedPreferences.Editor = shared.edit()
        editor.putString(NAME,"Krisma")
        editor.putString(USER, "krismaky")
        editor.putString(PASS, "krismakyjuga")
        editor.apply()

        buttonLogin.setOnClickListener {
            val username = editTextUsername.text.toString()
            val password = editTextPassword.text.toString()

            val usernamelocal = shared.getString(USER,"")
            val passwordlocal = shared.getString(PASS, "")

            if (username == usernamelocal){
                if (password == passwordlocal){
                    val playersname = shared.getString(NAME,"")
                    val intent = Intent(this, PreparationActivity::class.java)
                    intent.putExtra(PLAYER_NAME, playersname.toString())
                    startActivity(intent)
                }
                else errorLogin()
            }
            else
                errorLogin()
        }
    }

    fun errorLogin(){
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Peringatan!")
        alert.setMessage("Username atau password salah")
        alert.setPositiveButton("OK") { _,_ ->}
        alert.show()
    }

    override fun onBackPressed() {
        finish()
    }
}