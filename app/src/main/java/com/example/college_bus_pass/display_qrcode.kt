package com.example.college_bus_pass

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.telephony.SmsManager
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class display_qrcode : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_qrcode)

        val prefs = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE)

        val number = prefs.getString("number", "")



        val extras = intent.extras
        val name = extras?.getString("name")
        val rollno = extras?.getString("rollno")
        val source = extras?.getString("source")
        val des = extras?.getString("des")
        val charges = extras?.getString("charges")

        val img = findViewById<ImageView>(R.id.imageView)
        Toast.makeText(applicationContext,name.toString(),Toast.LENGTH_LONG).show()

        val sb = StringBuffer()
        sb.append("roll no ").append(name)
        sb.append(System.getProperty("line.separator"))
        sb.append("Name").append(rollno)
        sb.append(System.getProperty("line.separator"))
        sb.append("Source ").append(source)
        sb.append(System.getProperty("line.separator"))
        sb.append("Destination ").append(des)
            sb.append(System.getProperty("line.separator"))
            sb.append("Charges ").append(charges)

            val msg =sb.toString()

            val writer = QRCodeWriter()
            try{
                val bitMatrix = writer.encode(msg, BarcodeFormat.QR_CODE,512,512)
                val width = bitMatrix.width
                val height = bitMatrix.height
                val bmp= Bitmap.createBitmap(width,height, Bitmap.Config.RGB_565)

                for(x in 0 until width){
                    for(y in 0 until height)
                    {
                        bmp.setPixel(x,y, if(bitMatrix[x,y]) Color.BLACK else Color.WHITE)

                    }
                }




                img.setImageBitmap(bmp)
                createImageFile(bmp)




            }catch (e: WriterException)
            {
                e.printStackTrace()
            }

        val btn = findViewById<Button>(R.id.btnpayment)

        btn.setOnClickListener {

            val balance = 2000 - charges!!.toInt()
            println("....................................................."+balance)

            val smsManager = SmsManager.getDefault() as SmsManager
            smsManager.sendTextMessage(number,null,
                "Payment Received For Buss Pass Rs $charges",null,null)

            Toast.makeText(applicationContext, "Your Balance is$balance",Toast.LENGTH_LONG).show()
        }
        }

    private fun createImageFile(bmp: Bitmap?) {

        if (Build.VERSION.SDK_INT >= 30) {
            if (!Environment.isExternalStorageManager()) {
                val getpermission = Intent()
                getpermission.action = Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION
                startActivity(getpermission)
            }
        }

        val bytes = ByteArrayOutputStream()
        bmp?.compress(Bitmap.CompressFormat.JPEG,40,bytes)
//        val filepath = Environment.getExternalStorageDirectory().absolutePath + File.separator + "test.jpeg"

        val filepath = Environment.getExternalStorageDirectory().absolutePath + File.separator + "test.jpeg"
        try {
            val f = File(filepath)
            f.createNewFile()
            val fo = FileOutputStream(f)
            fo.write(bytes.toByteArray())
            fo.close()
        }catch (e:Exception)
        {
            e.printStackTrace()
        }

    }


}
