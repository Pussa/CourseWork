package com.example.kursach;

import android.content.Context;

import java.util.Random;

public class Human extends SpaceBody {

    public Human(Context context) {
        Random random = new Random();
        bitmapId = R.drawable.human;
        size = 3;
        y=2;
        x = 9;
        init(context);
    }
    public boolean isCollision(float shipX, float shipY, float shipSize) {
        return !(((x+size) < shipX)||(x > (shipX+shipSize))||((y+size) < shipY)||(y > (shipY+shipSize)));
    }

}
