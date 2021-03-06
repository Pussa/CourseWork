package com.example.kursach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    public static boolean isLeftPressed = false; // нажата левая кнопка
    public static boolean isRightPressed = false; // нажата правая кнопка

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameView gameView = new gameView(this); // создаём gameView
        LinearLayout gameLayout = (LinearLayout) findViewById(R.id.gameLayout); // находим gameLayout
        gameLayout.addView(gameView); // и добавляем в него gameView
        gameLayout.setBackgroundResource(R.drawable.background);
        Button leftButton = (Button) findViewById(R.id.leftButton); // находим кнопки
        Button rightButton = (Button) findViewById(R.id.rightButton);
        leftButton.setOnTouchListener(this); // и добавляем этот класс как слушателя (при нажатии сработает onTouch)
        rightButton.setOnTouchListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        Intent intent = new Intent(this, menu.class);
        startActivity(intent);
    }

    public boolean onTouch(View button, MotionEvent motion) {
        switch(button.getId()) { // определяем какая кнопка
            case R.id.leftButton:
                switch (motion.getAction()) { // определяем нажата или отпущена
                    case MotionEvent.ACTION_DOWN:
                        isLeftPressed = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        isLeftPressed = false;
                        break;
                }
                break;
            case R.id.rightButton:
                switch (motion.getAction()) { // определяем нажата или отпущена
                    case MotionEvent.ACTION_DOWN:
                        isRightPressed = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        isRightPressed = false;
                        break;
                }
                break;
        }
        return true;
    }
}





