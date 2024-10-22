package com.example.college_bus_pass

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class generate_qrcode : AppCompatActivity() {

    var edname: EditText?=null
    var edprofile: EditText?=null
    var edcriteria: EditText?=null
    var edtime: EditText?=null
    var edhrname: EditText?=null
    var edcontact: EditText?=null
    var edaddress: EditText?=null

    var category:String?=null
    var name:String?=null
    var description:String?=null
    var price:String?=null
    var charges:String?=null


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_qrcode)

        edname = findViewById<EditText>(R.id.edtype)
        edprofile = findViewById<EditText>(R.id.edname)
        edcriteria = findViewById<EditText>(R.id.eddes)
        edtime = findViewById<EditText>(R.id.edprice)

        val qrcode = findViewById<Button>(R.id.btnqrcode)



        qrcode.setOnClickListener {


            val intent = Intent(applicationContext, display_qrcode::class.java)

            intent.putExtra("name", category)
            intent.putExtra("rollno", name)
            intent.putExtra("source", description)
            intent.putExtra("des", price)
            intent.putExtra("charges", charges)

            startActivity(intent)
//
        }
    }



    fun UploadData(view: View?) {
         category = edname!!.text.toString()
         name = edprofile!!.text.toString()
         description = edcriteria!!.text.toString()
         price = edtime!!.text.toString()


        val value = price+description

        val myMap: Map<String, Int> = mapOf<String,Int>("katrajnigdi" to 20 , "punestationnagar" to 33)

        val result = myMap.getValue(value)

        val km = result * 50
         charges = km.toString()

        val data = FirebaseDatabase.getInstance().reference.child("pass")
        val service = Student(category,name,description,price,charges)


        data.push().setValue(service)

        Toast.makeText(applicationContext,"Uploaded", Toast.LENGTH_LONG).show()





    }

}