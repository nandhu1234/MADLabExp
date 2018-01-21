package com.example.srimadhan11.madlabexp;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Exp5Activity extends AppCompatActivity
        implements android.view.View.OnClickListener {

    EditText RollNo, Name, Marks;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp5);

        RollNo = findViewById(R.id.rollno);
        Name = findViewById(R.id.name);
        Marks = findViewById(R.id.marks);

        Button Insert = findViewById(R.id.insert);
        Button Delete = findViewById(R.id.delete);
        Button Update = findViewById(R.id.update);
        Button View = findViewById(R.id.view);
        Button ViewAll = findViewById(R.id.view_all);

        Insert.setOnClickListener(this);
        Delete.setOnClickListener(this);
        Update.setOnClickListener(this);
        View.setOnClickListener(this);
        ViewAll.setOnClickListener(this);

        db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(rollNo VARCHAR, name VARCHAR, marks VARCHAR);");
    }

    public void onClick(View view) {
        String rollno = RollNo.getText().toString().trim();
        String name = Name.getText().toString().trim();
        String marks = Marks.getText().toString().trim();
        Cursor cursor = null;
        String query;

        switch (view.getId()) {
            case R.id.insert:
                if (rollno.isEmpty() || name.isEmpty() || marks.isEmpty()) {
                    showMessage("Error", "Please enter all values");
                    return;
                }
                query = "INSERT INTO student VALUES('" + rollno + "', '" + name + "', '" + marks + "');";
                db.execSQL(query);
                showMessage("Success", "Record added");
                clearText();
                break;
            case R.id.delete:
                if (rollno.isEmpty()) {
                    showMessage("Error", "Please enter rollno");
                    return;
                }
                query = "SELECT * FROM student WHERE rollNo='" + rollno + "'";
                cursor = db.rawQuery(query, null);
                if (cursor.moveToFirst()) {
                    query = "DELETE FROM student WHERE rollNo='" + rollno + "'";
                    db.execSQL(query);
                    showMessage("Success", "Record Deleted");
                } else {
                    showMessage("Error", "Invalid RollNo");
                }
                clearText();
                break;
            case R.id.update:
                if (rollno.isEmpty() || name.isEmpty() || marks.isEmpty()) {
                    showMessage("Error", "Please enter all values");
                    return;
                }
                query = "SELECT * FROM student WHERE rollNo='" + rollno + "'";
                cursor = db.rawQuery(query, null);
                if (cursor.moveToFirst()) {
                    query = "UPDATE student SET name='" + name + "', marks='" + marks + "' " +
                            "WHERE rollNo='" + rollno + "'";
                    db.execSQL(query);
                    showMessage("Success", "Record Modified");
                } else {
                    showMessage("Error", "Invalid RollNo");
                }
                clearText();
                break;
            case R.id.view:
                if (rollno.isEmpty()) {
                    showMessage("Error", "Please enter rollno");
                    return;
                }
                query = "SELECT * FROM student WHERE rollNo='" + rollno + "'";
                cursor = db.rawQuery(query, null);
                if (cursor.moveToFirst()) {
                    Name.setText(cursor.getString(1));
                    Marks.setText(cursor.getString(2));
                } else {
                    showMessage("Error", "Invalid RollNo");
                    clearText();
                }
                break;
            case R.id.view_all:
                query = "SELECT * FROM student;";
                cursor = db.rawQuery(query, null);
                if (cursor.getCount() == 0) {
                    showMessage("Error", "No records found");
                    return;
                }
                StringBuilder buffer = new StringBuilder();
                while (cursor.moveToNext()) {
                    buffer.append("RollNo: ").append(cursor.getString(0)).append("\n");
                    buffer.append("Name: ").append(cursor.getString(1)).append("\n");
                    buffer.append("Marks: ").append(cursor.getString(2)).append("\n\n");
                }
                showMessage("Student Details", buffer.toString());
                break;
        }
        if (cursor != null) {
            cursor.close();
        }
    }

    public void showMessage(String title, String message) {
        Builder builder = new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void clearText() {
        RollNo.setText("");
        Name.setText("");
        Marks.setText("");
        RollNo.requestFocus();
    }
}
