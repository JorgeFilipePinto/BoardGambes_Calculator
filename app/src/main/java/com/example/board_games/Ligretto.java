package com.example.board_games;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Ligretto extends AppCompatActivity {

    public static boolean inGame = false;
    int shortDuration, longDuration;
    int playersNum, registedPlayers, sent = 0;
    ArrayList<String> playersNames = new ArrayList<String>();
    ArrayList<Integer> playersPoints = new ArrayList<Integer>();

    LinearLayout numPlayer, namePlayer, playerStatus,calculator;
    EditText playerQuantity, playerName, negPoints, posiPoints;
    Button checkNum, register, calculateButton, sendPoints;
    TextView name, points, playerSendName;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ligretto);
        inGame = true;

        playerStatus = findViewById(R.id.player_status);
        numPlayer = findViewById(R.id.layout_numPlayers);
        namePlayer = findViewById(R.id.layout_player_register);
        playerQuantity = findViewById(R.id.player_number);
        playerName = findViewById(R.id.player_name);
        checkNum = findViewById(R.id.check_num_players);
        register = findViewById(R.id.register_button);
        calculateButton = findViewById(R.id.calculate_button);
        sendPoints = findViewById(R.id.sendButton);
        negPoints = findViewById(R.id.negative_points);
        posiPoints = findViewById(R.id.positive_points);
        playerSendName = findViewById(R.id.player_name_to_send);
        name = findViewById(R.id.player_name_apresentation);
        points = findViewById(R.id.player_points_apresentation);
        calculator = findViewById(R.id.layout_points_calculator);

        newGame();

        checkNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playersNum = Integer.parseInt(playerQuantity.getText().toString());
                if(playersNum > 1 && playersNum <= 8)
                {
                    namePlayer.setVisibility(View.VISIBLE);
                    numPlayer.setVisibility(View.GONE);
                }
                else
                {
                    sendToast(R.string.num_players_error, longDuration);
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!playerName.getText().toString().isEmpty())
                {
                    playersNames.add(playerName.getText().toString());
                    playersPoints.add(0);
                    playerName.getText().clear();
                    registedPlayers++;
                    sendToast(R.string.valid_name, shortDuration);
                }
                else
                {
                    sendToast(R.string.invalid_name, longDuration);
                }

                if(registedPlayers == playersNum)
                {
                    namePlayer.setVisibility(View.GONE);
                    playerStatus.setVisibility(View.VISIBLE);
                    calculateStatus();
                }
            }
        });

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerSendName.setText(playersNames.get(sent));
                calculator.setVisibility(View.VISIBLE);
            }
        });

        sendPoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
            calculate(sent, Integer.parseInt(negPoints.getText().toString()), Integer.parseInt(posiPoints.getText().toString()));
            sent++;
            posiPoints.getText().clear();
            negPoints.getText().clear();
            if(sent == playersNames.size())
            {
                calculator.setVisibility(View.GONE);
                sent = 0;
            }
            else
            {
                playerSendName.setText(playersNames.get(sent));
            }
            calculateStatus();
            }
        });
    }

    public void newGame(){
        playersNum = 2;
        registedPlayers = 0;
        numPlayer.setVisibility(View.VISIBLE);
        namePlayer.setVisibility(View.GONE);
        playerStatus.setVisibility(View.GONE);
        calculator.setVisibility(View.GONE);
    }

    public void calculateStatus(){
        String tempNames = "", tempPoints = "";
        for(int i = 0; i<playersNum; i++){
            tempNames += playersNames.get(i) + "\n";
            tempPoints +=playersPoints.get(i) + "\n";
        }
        name.setText(tempNames);
        points.setText(tempPoints);
    }

    public void calculate(int index, int pointsSubtractor, int pointsPositives)
    {
        int newPoints = (playersPoints.get(index) - (2 * pointsSubtractor) + pointsPositives);
        playersPoints.set(index, newPoints);
    }

    public void sendToast(int stringID, int duration)
    {
        Toast.makeText(this, stringID, duration).show();
    }

}