package com.example.college_bus_pass

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class dash : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add-> {
                val intent = Intent(applicationContext,Profile::class.java)
                startActivity(intent)

            }
            R.id.phistory -> {
                val intent = Intent(applicationContext,generate_qrcode::class.java)
                startActivity(intent)

            }
            R.id.qrcode -> {
                val intent = Intent(applicationContext,Showqrcode::class.java)
                startActivity(intent)

            }
        }
        return true

    }

}