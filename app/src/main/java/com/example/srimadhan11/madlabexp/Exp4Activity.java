package com.example.srimadhan11.madlabexp;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Exp4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp4);

        Bitmap background = Bitmap.createBitmap(720, 1280, Bitmap.Config.ARGB_8888);
        findViewById(R.id.imageView).setBackground(new BitmapDrawable(getResources(), background));

        Canvas canvas = new Canvas(background);
        Paint paint = new Paint();
        paint.setTextSize(50);

        canvas.drawText("Rectangle", 420, 150, paint);
        canvas.drawRect(400, 200, 650, 700, paint);
        paint.setColor(Color.BLUE);

        canvas.drawText("Circle", 120, 150, paint);
        canvas.drawCircle(200, 350, 150, paint);
        paint.setColor(Color.RED);

        canvas.drawText("Square", 120, 800, paint);
        canvas.drawRect(50, 850, 350, 1150, paint);
        paint.setColor(Color.BLUE);

        canvas.drawText("Line", 480, 800, paint);
        canvas.drawLine(520, 850, 520, 1150, paint);
        paint.setColor(Color.BLUE);
    }
}
