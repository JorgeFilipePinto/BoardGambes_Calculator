package com.example.board_games;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button ligrettoGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(Ligretto.inGame){
            setContentView(R.layout.activity_ligretto);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ligrettoGame = findViewById(R.id.ligreto);

        ligrettoGame.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent LigretoNewGame = new Intent(MainActivity.this, Ligretto.class);
                startActivity(LigretoNewGame);
            }
        });

    }

}