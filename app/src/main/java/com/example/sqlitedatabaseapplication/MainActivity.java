package com.example.sqlitedatabaseapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name,email,password;
    Button insert,update,view,delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);

        insert=findViewById(R.id.insert);
        update=findViewById(R.id.update);
        view=findViewById(R.id.view);
        delete=findViewById(R.id.delete);

        Database dt=new Database(this);
        //SQLiteDatabase d=dt.getReadableDatabase();
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n=name.getText().toString();
                String e=email.getText().toString();
                String p=password.getText().toString();
                if(n.equals("")||e.equals("")||p.equals("")){
                    Toast.makeText(MainActivity.this, "Enter all the fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean i=dt.insert_data(n,e,p);
                    if(i==true){
                        Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Something went Wrong", Toast.LENGTH_SHORT).show();
                    }
                }
                name.setText("");
                email.setText("");
                password.setText("");
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor t=dt.getData();
                if(t.getCount()==0){
                    Toast.makeText(MainActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                }
                StringBuffer buffer=new StringBuffer();
                while(t.moveToNext()){
                    buffer.append("name:- "+t.getString(1)+"\n");
                    buffer.append("email:- "+t.getString(2)+"\n");
                    buffer.append("password:- "+t.getString(3)+"\n\n\n");
                }
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Data Record");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n=name.getText().toString();
                String e=email.getText().toString();
                String p=password.getText().toString();
                Boolean i=dt.updateData(n,e,p);
                if(i==true){
                    Toast.makeText(MainActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "something went True", Toast.LENGTH_SHORT).show();
                }
                name.setText("");
                email.setText("");
                password.setText("");
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e=email.getText().toString();
                Boolean i=dt.deleteData(e);
                if(i==true){
                    Toast.makeText(MainActivity.this, "Record Deleted", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Something went wrong!!", Toast.LENGTH_SHORT).show();
                }
                name.setText("");
                email.setText("");
                password.setText("");
            }
        });
        
    }
}