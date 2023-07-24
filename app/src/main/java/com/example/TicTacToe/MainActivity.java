package com.example.TicTacToe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn1;
    boolean gameOver=false;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    Button btn9;
    Button btnRefresh;
    List<Box> boxes;
    int player=0;
    List<Button> buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        btn5 = findViewById(R.id.button5);
        btn6 = findViewById(R.id.button6);
        btn7 = findViewById(R.id.button7);
        btn8 = findViewById(R.id.button8);
        btn9 = findViewById(R.id.button9);
        btnRefresh = findViewById(R.id.btnRefresh);


        refreshGame();
    }




    private void refreshGame(){
        gameOver=false;
        buttons = new ArrayList<>();
        buttons.add(btn1);
        buttons.add(btn2);
        buttons.add(btn3);
        buttons.add(btn4);
        buttons.add(btn5);
        buttons.add(btn6);
        buttons.add(btn7);
        buttons.add(btn8);
        buttons.add(btn9);

        boxes=new ArrayList<>();
        for (Button btn:buttons){
            boxes.add(new Box(btn));
            btn.setOnClickListener(this);

        btnRefresh.setOnClickListener(this);

        }
    }


    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.btnRefresh){
            refreshGame();
        }

        if (checkGameOver()) {
            Toast.makeText(this, "Game is Over", Toast.LENGTH_SHORT).show();
            return;
        };
        //Toast.makeText(this, "You clicked", Toast.LENGTH_SHORT).show();
        for (Box box: boxes){
            if (view.getId()==box.getBtnId()){
                //Toast.makeText(this,"Found the box",Toast.LENGTH_SHORT).show();
                if (box.setValue(player)){
                    if (checkWin()) return;
                    //Otherwise next turn
                    player=(player+1)%2;
                    //Toast.makeText(this,"Ok. Next is player "+player+"'s turn",Toast.LENGTH_SHORT).show();
                    break;
                }
                else{
                    //System.out.println("player tried to overwrite.");
                    return;
                }

            }

        }
    }

    private boolean checkWin(){
        if (checkValuesEqual(1,2,3)) return true;
        if (checkValuesEqual(4,5,6)) return true;
        if (checkValuesEqual(7,8,9)) return true;

        if (checkValuesEqual(1,5,9)) return true;
        if (checkValuesEqual(3,5,7)) return true;

        if (checkValuesEqual(1,4,7)) return true;
        if (checkValuesEqual(2,5,8)) return true;
        if (checkValuesEqual(3,6,9)) return true;

        return false;
    }

    private boolean checkValuesEqual(int positionA,int positionB,int positionC){
        positionA-=1;
        positionB-=1;
        positionC-=1;
        if (boxes.get(positionA).getValue()==2) return false;
        if (boxes.get(positionB).getValue()==2) return false;
        if (boxes.get(positionC).getValue()==2) return false;

        if (boxes.get(positionA).getValue()!=boxes.get(positionB).getValue()) return false;
        if (boxes.get(positionB).getValue()!=boxes.get(positionC).getValue()) return false;

        boxes.get(positionA).winColor();
        boxes.get(positionB).winColor();
        boxes.get(positionC).winColor();
        Toast.makeText(this, "Player"+boxes.get(positionA).getValue()+" wins!", Toast.LENGTH_SHORT).show();
        gameOver=true;
        return true;
    }

    private boolean checkGameOver() {
        if (gameOver) return true;
        for (Box box : boxes) {
            if (box.getValue() == 2) return false;
        }
        return true;
    }


}