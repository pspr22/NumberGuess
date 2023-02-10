package com.example.numberguess;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    Button bstart;
    RadioButton radio1,radio2,radio3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        bstart = findViewById(R.id.start);
        radio1 = findViewById(R.id.twodigit);
        radio2 = findViewById(R.id.threedigit);
        radio3 = findViewById(R.id.fourdigit);

        bstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,GameActivity.class);

                if(!radio1.isChecked() && !radio2.isChecked() && !radio3.isChecked()){
                    Snackbar.make(view,"Please select a number of digits",Snackbar.LENGTH_LONG).show();
                }
                else{
                    if(radio1.isChecked())
                        intent.putExtra("two",true);
                    if(radio2.isChecked())
                        intent.putExtra("three",true);
                    if(radio3.isChecked())
                        intent.putExtra("four",true);

                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onBackPressed(){
        AlertDialog.Builder alertdialog = new AlertDialog.Builder(this);
        alertdialog.setTitle("Exit Application?")
                .setMessage("Click yes to exit!")
                .setCancelable(false)
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);

                    }
                }).show();
        alertdialog.create();
    }

}