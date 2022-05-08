package com.example.book;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.book.DataBase.DBHandlerAbove;

public class MainActivity extends AppCompatActivity {

    Button below,above,add,view;
    EditText customer_name,address,contact_number,customer_designation,interested_area;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        below = findViewById(R.id.cbelow);
        above = findViewById(R.id.cabove);

        below = findViewById(R.id.cbelow);
        above = findViewById(R.id.cabove);

        customer_name = findViewById(R.id.cname);
        address = findViewById(R.id.addy);
        contact_number = findViewById(R.id.phone);
        customer_designation = findViewById(R.id.desig);
        interested_area = findViewById(R.id.interested);

        add = findViewById(R.id.cadd);
        view = findViewById(R.id.cview);

        below.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddStudent.class);
                startActivity(intent);
            }
        });

        above.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }

        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewElder.class);
                startActivity(intent);
            }


        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(contact_number.length() != 10) {
                    Toast.makeText(MainActivity.this, "Invalid phone number", Toast.LENGTH_SHORT).show();

                }
                else{
                    DBHandlerAbove dbHelper = new DBHandlerAbove(getApplicationContext());
                    long newID = dbHelper.addInfo(customer_name.getText().toString(),address.getText().toString(),contact_number.getText().toString(),customer_designation.getText().toString(),interested_area.getText().toString());
                    Toast.makeText(MainActivity.this,"User Successfully Added..Customer ID is " + newID, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);

                    customer_name.setText(null);
                    address.setText(null);
                    contact_number.setText(null);
                    customer_designation.setText(null);
                    interested_area.setText(null);

                }

                }
            }


        );




    }
}