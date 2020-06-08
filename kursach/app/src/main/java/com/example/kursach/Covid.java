package com.example.kursach;

import android.content.Context;

public class Covid extends SpaceBody{
    private int radius = 2; // радиус
    public Covid(Context context) {
        bitmapId = R.drawable.covid; // определяем начальные параметры
        size = radius*2;
        x=7;
        y= 15;
        speed = (float) 0.3;

        init(context); // инициализируем корабль
    }

    @Override
    public void update() { // перемещаем корабль в зависимости от нажатой кнопки
        if(MainActivity.isLeftPressed && x >= 0){
            x -= speed;
        }
        if(MainActivity.isRightPressed && x <= gameView.maxX - size){
            x += speed;
        }
    }



}