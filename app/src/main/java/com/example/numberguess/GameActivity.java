package com.example.numberguess;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    TextView lastNum,remainingch,hint;
    EditText etguess;
    Button guess;
    boolean twoDigits,threeDigits,fourDigits;

    Random r = new Random();
    int random;
    int remr = 10;
    ArrayList<Integer> guesseslist = new ArrayList<>();
    int userAttempt =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        lastNum = findViewById(R.id.lastnum);
        remainingch = findViewById(R.id.remainingch);
        hint = findViewById(R.id.guessinde);
        etguess = findViewById(R.id.etguess);
        guess = findViewById(R.id.guess);

        twoDigits = getIntent().getBooleanExtra("two",false);
        threeDigits = getIntent().getBooleanExtra("three",false);
        fourDigits = getIntent().getBooleanExtra("four",false);

        if(twoDigits){
            random = r.nextInt(90)+10;
        }
        if(threeDigits){
            random = r.nextInt(900)+100;
        }
        if(fourDigits){
            random = r.nextInt(9000)+1000;
        }

        guess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gue = etguess.getText().toString();
                if(gue.equals(""))
                    Toast.makeText(GameActivity.this, "Please enter a guess", Toast.LENGTH_LONG).show();
                else{

                    remr--;
                    userAttempt++;

                    int userGuess = Integer.parseInt(gue);
                    guesseslist.add(userGuess);

                    lastNum.setText("Your Last Guess is: "+gue);
                    remainingch.setText("Chances Remaining: "+remr);
                    lastNum.setVisibility(View.VISIBLE);
                    hint.setVisibility(View.VISIBLE);
                    remainingch.setVisibility(View.VISIBLE);

                    if(random == userGuess){
                        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                        builder.setTitle("Number Guessing Gancel");
                        builder.setCancelable(false);
                        builder.setMessage("Congratulations, My number was "+random
                                +"\n\n You know my number in "+userAttempt
                                + " attempts. \n\n Your Guesses : "+guesseslist
                                +"\n\n Would you like to play again");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(GameActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        });
                        builder.create().show();


                    }
                    if(random < userGuess){
                        hint.setText("Decrease your Guess");

                    }
                    if(random > userGuess){
                        hint.setText("Increase your Guess");
                    }

                    if(remr == 0){
                        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                        builder.setTitle("Number Guessing Gancel");
                        builder.setCancelable(false);
                        builder.setMessage("Sorry!!!, My number was "+random
                                +"\n\n Your Guesses : "+guesseslist
                                +"\n\n Would you like to play again");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(GameActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        });
                        builder.create().show();
                    }
                    etguess.setText("");

                }
            }
        });



    }
}