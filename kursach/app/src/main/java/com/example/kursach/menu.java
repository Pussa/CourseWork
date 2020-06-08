package com.example.kursach;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class menu extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuu);
        TextView quest = findViewById(R.id.TextQuest);
        quest.setText("Задеть 5 клеток");
    }

    @Override
    public void onClick(View v) {
        TextView quest = findViewById(R.id.TextQuest);
        Button Quest = findViewById(R.id.Quest);
        switch(v.getId()) {
            case R.id.newGame:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.Quest:
                if (quest.getTextColors().getDefaultColor() ==Color.parseColor("#00000000")){
                    quest.setTextColor(Color.parseColor("#FFFFFF"));
                    Quest.setText("Убрать задание");
                }else{
                    quest.setTextColor(Color.parseColor("#00000000"));
                    Quest.setText("Показать задание");
                }
                break;
        }
    }
}
