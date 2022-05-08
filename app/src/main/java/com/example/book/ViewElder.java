package com.example.book;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.book.DataBase.DBHandlerAbove;

import java.util.List;

public class ViewElder extends AppCompatActivity {

    Button search, update, delete, viewall;
    EditText id, customer_name, address, contact_number, customer_designation, interested_area;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_elder);

        search = findViewById(R.id.search);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        viewall = findViewById(R.id.viewall);

        id = findViewById(R.id.id);
        customer_name = findViewById(R.id.cname);
        address = findViewById(R.id.addy);
        contact_number = findViewById(R.id.phone);
        customer_designation = findViewById(R.id.desig);
        interested_area = findViewById(R.id.interested);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandlerAbove dbHelper = new DBHandlerAbove(getApplicationContext());

                List user = dbHelper.readAllInfo(id.getText().toString());

                if (user.isEmpty()) {
                    Toast.makeText(ViewElder.this, "No User", Toast.LENGTH_SHORT).show();
                    id.setText(null);
                } else {
                    Toast.makeText(ViewElder.this, "User Found..", Toast.LENGTH_SHORT).show();

                    customer_name.setText(user.get(0).toString());
                    address.setText(user.get(1).toString());
                    contact_number.setText(user.get(2).toString());
                    customer_designation.setText(user.get(3).toString());
                    interested_area.setText(user.get(4).toString());

                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHandlerAbove dbHelper = new DBHandlerAbove(getApplicationContext());

                boolean status = dbHelper.updateInfo(id.getText().toString(), customer_name.getText().toString(), address.getText().toString(), contact_number.getText().toString(), customer_designation.getText().toString(), interested_area.getText().toString());
                if (status) {
                    Toast.makeText(ViewElder.this, "Successfully Updated..", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ViewElder.this, "Error..", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHandlerAbove dbHelper = new DBHandlerAbove(getApplicationContext());
                dbHelper.deleteInfo(id.getText().toString());

                Toast.makeText(ViewElder.this, "Successfully Deleted..", Toast.LENGTH_SHORT).show();

                id.setText(null);
                customer_name.setText(null);
                address.setText(null);
                contact_number.setText(null);
                customer_designation.setText(null);
                interested_area.setText(null);

            }
        });

        viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandlerAbove dbHelper = new DBHandlerAbove(getApplicationContext());
                Cursor res = dbHelper.getproductdata();
                if (res.getCount() == 0) {
                    Toast.makeText(ViewElder.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("ID :" + res.getString(0) + "\n");
                    buffer.append("Customer Name :" + res.getString(1) + "\n");
                    buffer.append("Address :" + res.getString(2) + "\n");
                    buffer.append("Contact Number:" + res.getString(3) + "\n");
                    buffer.append("Customer Designation:" + res.getString(4) + "\n");
                    buffer.append("Interested areas:" + res.getString(5) + "\n\n");

                }

                AlertDialog.Builder builder = new AlertDialog.Builder(ViewElder.this);
                builder.setCancelable(true);
                builder.setTitle("Elder Details ");
                builder.setMessage(buffer.toString());
                builder.show();


            }

        });

    }
}

