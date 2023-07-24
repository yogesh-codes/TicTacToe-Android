package com.example.TicTacToe;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;

public class Box {

    private int value;
    private Button btn;

    public Box(Button btn){
        this.value=2;
        this.btn=btn;
        this.resetColor();
        this.btn.setText("");
    }

    public void resetColor(){
        this.btn.setBackgroundColor(Color.GRAY);
    }
    public int getValue() {
        return value;
    }

    public boolean setValue(int player) {
        boolean n=false;
        if (this.value==2) {this.value=player;n=true;}

        if (this.value==0){this.colorPlayer0(player);}
        if (this.value==1){this.colorPlayer1(player);}

        return n;
    }

    private void colorPlayer0(int player){
        this.btn.setBackgroundColor(Color.GREEN);
        this.btn.setText(player+"");
    }

    private void colorPlayer1(int player){
        this.btn.setBackgroundColor(Color.RED);
        this.btn.setText(player+"");
    }

    public int getBtnId() {
        return btn.getId();
    }

    public void winColor(){
        btn.setBackgroundColor(Color.YELLOW);
    }

}
