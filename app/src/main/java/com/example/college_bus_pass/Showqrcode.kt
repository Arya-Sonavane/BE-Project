package com.example.college_bus_pass

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.ImageView
import java.io.File

class Showqrcode : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showqrcode)

        val img = findViewById<ImageView>(R.id.imgqr)
        val bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().absolutePath + File.separator+"test.jpeg")
        img.setImageBitmap(bitmap)


    }
}