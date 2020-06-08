package com.example.kursach;

import android.content.Context;

import java.util.Random;

public class Cell extends SpaceBody {

    private int radius = 2; // радиус
    public Cell(Context context) {
        Random random = new Random();
        bitmapId = R.drawable.cell;
        y=random.nextInt(gameView.maxY) - radius;
        x = random.nextInt(gameView.maxX) - radius;
        size = radius*2;
        init(context);
    }


    public boolean isCollision(float shipX, float shipY, float shipSize) {
        return !(((x+size) < shipX)||(x > (shipX+shipSize))||((y+size) < shipY)||(y > (shipY+shipSize)));
    }

}
