package com.example.book;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.book.DataBase.DBHandlerAbove;
import com.example.book.DataBase.DBHandlerBelow;

public class AddStudent extends AppCompatActivity {

    Button below,above,add,view;
    EditText student_name,address,contact_number,interested_areas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        below = findViewById(R.id.cbelow);
        above = findViewById(R.id.cabove);

        below = findViewById(R.id.cbelow);
        above = findViewById(R.id.cabove);

        student_name = findViewById(R.id.sname);
        address = findViewById(R.id.saddress);
        contact_number = findViewById(R.id.cn);
        interested_areas = findViewById(R.id.sinterested);

        add = findViewById(R.id.sadd);
        view = findViewById(R.id.sview);

        below.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddStudent.this, AddStudent.class);
                startActivity(intent);
            }
        });

        above.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddStudent.this, MainActivity.class);
                startActivity(intent);
            }

        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddStudent.this, ViewStudent.class);
                startActivity(intent);
            }


        });

        add.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {

                                       if(contact_number.length() != 10) {
                                           Toast.makeText(AddStudent.this, "Invalid phone number", Toast.LENGTH_SHORT).show();

                                       }
                                       else{
                                           DBHandlerBelow dbHelper = new DBHandlerBelow(getApplicationContext());
                                           long newID = dbHelper.addInfo(student_name.getText().toString(),address.getText().toString(),contact_number.getText().toString(),interested_areas.getText().toString());
                                           Toast.makeText(AddStudent.this,"User Successfully Added..Student ID is " + newID, Toast.LENGTH_SHORT).show();

                                           Intent intent = new Intent(getApplicationContext(),AddStudent.class);
                                           startActivity(intent);

                                           student_name.setText(null);
                                           address.setText(null);
                                           contact_number.setText(null);
                                           interested_areas.setText(null);

                                       }

                                   }
                               }


        );


    }
}