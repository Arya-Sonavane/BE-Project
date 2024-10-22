package com.example.college_bus_pass;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class scancode extends AppCompatActivity {

    Button btnscan;
    String mail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scancode);

        Button btnscan = findViewById(R.id.button2);

        btnscan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScanCode();

            }
        });


    }

    private void ScanCode() {

        ScanOptions options = new ScanOptions();
        options.setPrompt("Volume up to flash on");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLaucher.launch(options);

    }

    ActivityResultLauncher<ScanOptions> barLaucher = registerForActivityResult(new ScanContract(), result -> {


        if(result.getContents() !=null)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(scancode.this);
            builder.setTitle("result");
            builder.setMessage(result.getContents());

            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                   dialogInterface.dismiss();

                }
            }).show();
        }
    });
}