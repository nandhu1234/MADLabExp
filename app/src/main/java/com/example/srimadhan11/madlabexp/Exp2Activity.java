package com.example.srimadhan11.madlabexp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class Exp2Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp2);

        final EditText Name = findViewById(R.id.name);
        final EditText Reg = findViewById(R.id.reg);
        final Spinner Dept = findViewById(R.id.dept);
        String[] dept_array={
                "CSE",
                "ECE",
                "IT",
                "Mech",
                "Civil"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, dept_array);
        Dept.setAdapter(adapter);

        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Name.getText().toString();
                String reg = Reg.getText().toString();
                String dept = Dept.getSelectedItem().toString();
                LinearLayout result = findViewById(R.id.result);
                result.setVisibility(View.VISIBLE);

                LinearLayout form = findViewById(R.id.form);
                form.setVisibility(View.GONE);

                ((TextView) findViewById(R.id.name_receive)).setText(name);
                ((TextView) findViewById(R.id.reg_receive)).setText(reg);
                ((TextView) findViewById(R.id.dept_receive)).setText(dept);
            }
        });
        Button back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout result = findViewById(R.id.result);
                result.setVisibility(View.GONE);

                LinearLayout form = findViewById(R.id.form);
                form.setVisibility(View.VISIBLE);

                Name.setText("");
                Reg.setText("");
                Dept.setSelection(0);
            }
        });

    }
}
