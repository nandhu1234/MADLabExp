package com.example.srimadhan11.madlabexp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Exp3Activity extends AppCompatActivity implements View.OnClickListener {

    private StringBuilder successor, predecessor;
    private char operation;
    private boolean isPredecessor, isPositive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp3);

        successor = new StringBuilder();
        predecessor = new StringBuilder();
        isPredecessor = true;
        isPositive = true;
        operation = '+';

        findViewById(R.id._0).setOnClickListener(this);
        findViewById(R.id._1).setOnClickListener(this);
        findViewById(R.id._2).setOnClickListener(this);
        findViewById(R.id._3).setOnClickListener(this);
        findViewById(R.id._4).setOnClickListener(this);
        findViewById(R.id._5).setOnClickListener(this);
        findViewById(R.id._6).setOnClickListener(this);
        findViewById(R.id._7).setOnClickListener(this);
        findViewById(R.id._8).setOnClickListener(this);
        findViewById(R.id._9).setOnClickListener(this);
        findViewById(R.id.dot).setOnClickListener(this);
        findViewById(R.id.plus).setOnClickListener(this);
        findViewById(R.id.minus).setOnClickListener(this);
        findViewById(R.id.asterisk).setOnClickListener(this);
        findViewById(R.id.backslash).setOnClickListener(this);
        findViewById(R.id.equal).setOnClickListener(this);
        findViewById(R.id.ac).setOnClickListener(this);
        findViewById(R.id.c).setOnClickListener(this);
        findViewById(R.id.plusOrMinus).setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id._0:
                (isPredecessor ? predecessor : successor).append("0");
                break;
            case R.id._1:
                (isPredecessor ? predecessor : successor).append("1");
                break;
            case R.id._2:
                (isPredecessor ? predecessor : successor).append("2");
                break;
            case R.id._3:
                (isPredecessor ? predecessor : successor).append("3");
                break;
            case R.id._4:
                (isPredecessor ? predecessor : successor).append("4");
                break;
            case R.id._5:
                (isPredecessor ? predecessor : successor).append("5");
                break;
            case R.id._6:
                (isPredecessor ? predecessor : successor).append("6");
                break;
            case R.id._7:
                (isPredecessor ? predecessor : successor).append("7");
                break;
            case R.id._8:
                (isPredecessor ? predecessor : successor).append("8");
                break;
            case R.id._9:
                (isPredecessor ? predecessor : successor).append("9");
                break;
            case R.id.dot:
                if (isPredecessor) {
                    if (!predecessor.toString().contains("."))
                        predecessor.append(".");
                } else {
                    if (!successor.toString().contains("."))
                        successor.append(".");
                }
                break;
            case R.id.plus:
                predecessor.reverse()
                        .append(isPositive ? '+' : '-')
                        .reverse();
                isPredecessor = false;
                isPositive = true;
                operation = '+';
                break;
            case R.id.minus:
                predecessor.reverse()
                        .append(isPositive ? '+' : '-')
                        .reverse();
                isPredecessor = false;
                isPositive = true;
                operation = '-';
                break;
            case R.id.asterisk:
                predecessor.reverse()
                        .append(isPositive ? '+' : '-')
                        .reverse();
                isPredecessor = false;
                isPositive = true;
                operation = '*';
                break;
            case R.id.backslash:
                predecessor.reverse()
                        .append(isPositive ? '+' : '-')
                        .reverse();
                isPredecessor = false;
                isPositive = true;
                operation = '/';
                break;
            case R.id.equal:
                try {
                    successor.reverse()
                            .append(isPositive ? '+' : '-')
                            .reverse();
                    float pre = Float.parseFloat(predecessor.toString());
                    float suc = Float.parseFloat(successor.toString());
                    float result = 0;
                    switch (operation){
                        case '+':
                            result = pre + suc;
                            break;
                        case '-':
                            result = pre - suc;
                            break;
                        case '*':
                            result = pre * suc;
                            break;
                        case '/':
                            result = pre / suc;
                            break;
                    }
                    ((TextView) findViewById(R.id.result)).setText(Float.toString(result));
                } catch (Exception e) {
                    Toast.makeText(this, "Exception occurred!", Toast.LENGTH_SHORT).show();
                    Log.e(getResources().getString(R.string.nav_item_3), e.getMessage());
                }
                predecessor.setLength(0);
                predecessor.trimToSize();
                successor.setLength(0);
                successor.trimToSize();
                operation = '+';
                isPositive = true;
                isPredecessor = true;
                break;
            case R.id.ac:
                predecessor.setLength(0);
                predecessor.trimToSize();
                successor.setLength(0);
                successor.trimToSize();
                operation = '+';
                isPositive = true;
                isPredecessor = true;
                break;
            case R.id.c:
                (isPredecessor ? predecessor : successor).setLength(0);
                (isPredecessor ? predecessor : successor).trimToSize();
                isPositive = true;
                break;
            case R.id.plusOrMinus:
                isPositive = !isPositive;
                break;
        }
        String expression = predecessor.toString() + (isPredecessor ? "" : (operation + successor.toString()));
        ((TextView) findViewById(R.id.expression)).setText(expression);
    }
}
