package com.example.book;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.book.DataBase.DBHandlerBelow;

import java.util.List;

public class ViewStudent extends AppCompatActivity {

    Button search, update, delete,viewall;
    EditText id,student_name,address,contact_number,interested_areas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);

        search = findViewById(R.id.searchs);
        update = findViewById(R.id.updates);
        delete = findViewById(R.id.deletes);
        viewall = findViewById(R.id.viewalls);

        id = findViewById(R.id.sid);
        student_name = findViewById(R.id.sname);
        address = findViewById(R.id.address);
        contact_number = findViewById(R.id.cn);
        interested_areas = findViewById(R.id.sinterested);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHandlerBelow dbHelper = new DBHandlerBelow(getApplicationContext());
                List user = dbHelper.readAllInfo(id.getText().toString());

                if(user.isEmpty()){
                    Toast.makeText(ViewStudent.this, "No User", Toast.LENGTH_SHORT).show();
                    id.setText(null);
                }
                else{
                    Toast.makeText(ViewStudent.this, "User Found..", Toast.LENGTH_SHORT).show();
                    student_name.setText(user.get(0).toString());
                    address.setText(user.get(1).toString());
                    contact_number.setText(user.get(2).toString());
                    interested_areas.setText(user.get(3).toString());

                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHandlerBelow dbHelper = new DBHandlerBelow(getApplicationContext());

                boolean status = dbHelper.updateInfo(id.getText().toString(),student_name.getText().toString(),address.getText().toString(),contact_number.getText().toString(),interested_areas.getText().toString());
                if(status){
                    Toast.makeText(ViewStudent.this, "Successfully Update..", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(ViewStudent.this, "Error..", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHandlerBelow dbHelper = new DBHandlerBelow(getApplicationContext());
                dbHelper.deleteInfo(id.getText().toString());

                Toast.makeText(ViewStudent.this, "Successfully Deleted..", Toast.LENGTH_SHORT).show();

                id.setText(null);
                student_name.setText(null);
                address.setText(null);
                contact_number.setText(null);
                interested_areas.setText(null);

            }
        });

        viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandlerBelow dbHelper = new DBHandlerBelow(getApplicationContext());
                Cursor res = dbHelper.getproductdata();
                if(res.getCount()==0){
                    Toast.makeText(ViewStudent.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("ID :"+res.getString(0)+"\n");
                    buffer.append("Student Name :"+res.getString(1)+"\n");
                    buffer.append("Address :"+res.getString(2)+"\n");
                    buffer.append("Contact Number:"+res.getString(3)+"\n\n");
                    buffer.append("Interested areas:"+res.getString(4)+"\n\n");

                }

                AlertDialog.Builder builder = new AlertDialog.Builder(ViewStudent.this);
                builder.setCancelable(true);
                builder.setTitle("Elder Details ");
                builder.setMessage(buffer.toString());
                builder.show();


            }
        });



    }
}