package com.example.srimadhan11.madlabexp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Exp1Activity extends AppCompatActivity {

    private int colorSelector;
    private int font;
    private TextView helloText;
    private final int[] color= {
            Color.RED,
            Color.GREEN,
            Color.BLUE,
            Color.CYAN,
            Color.YELLOW,
            Color.MAGENTA
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp1);

        colorSelector = 0;
        font = 30;
        helloText= findViewById(R.id.hello_text);
        Button changeSize= findViewById(R.id.change_size);
        changeSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helloText.setTextSize(font);
                font = ((font == 50) ? 30 : font + 5);
            }
        });

        Button changeColor = findViewById(R.id.change_color);
        changeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helloText.setTextColor(color[colorSelector % color.length]);
                colorSelector = ((colorSelector == color.length - 1) ? 0 : colorSelector + 1);
            }
        });
    }
}
