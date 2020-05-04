package com.example.citizen;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity{
    Button citizen1,admin1,detective1;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //database and tables

        db=openOrCreateDatabase("NewDatabase", Context.MODE_PRIVATE, null);

        db=openOrCreateDatabase("rachanadb", Context.MODE_PRIVATE, null);

        db.execSQL("CREATE TABLE IF NOT EXISTS detective(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,password VARCHAR,firstname VARCHAR,lastname VARCHAR,address VARCHAR,phonenumber INT,email VARCHAR,dateOfJoin date,caseAssigned INT,caseClosed INT,branchDetails VARCHAR);");

        db.execSQL("CREATE TABLE IF NOT EXISTS  CaseRegistration(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,caseId VARCHAR,Status VARCHAR,Type1 VARCHAR,VName VARCHAR,CName VARCHAR,Complaintname VARCHAR,Mobile VARCHAR,Place VARCHAR,Date1 VARCHAR,Time1 VARCHAR,assigned INT);");

        db.execSQL("CREATE TABLE IF NOT EXISTS Admin(email VARCHAR,password VARCHAR);");
        db.execSQL("INSERT INTO Admin(email,password) VALUES('" +"admin@admin.com"+"','" +"12345"+"');");

        db.execSQL("CREATE TABLE IF NOT EXISTS detcase(det_id INTEGER,case_id INTEGER PRIMARY KEY,details VARCHAR, FOREIGN KEY(det_id)REFERENCES detective(ID) )");
        //db.execSQL("INSERT INTO CaseRegistration (Status,Type1,VName,CName,Complaintname,Mobile,Place,Date1,Time1,assigned) VALUES('open','robery','car','xyz','xwvsyib','9481046831','tum','05/05/20','9:20',0);");

        setContentView(R.layout.activity_main);
        citizen1=(Button)findViewById(R.id.citizen);
        admin1=(Button)findViewById(R.id.admin);
        detective1=(Button)findViewById(R.id.detective);
        citizen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startcitizen=new Intent(MainActivity.this,ComplaintRegistration.class);
                startActivity(startcitizen);
            }
        });
        admin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startadmin=new Intent(MainActivity.this, LoginAdminActivity.class);
                startActivity(startadmin);
            }
        });
        detective1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startadmin=new Intent(MainActivity.this, DetectiveLogin.class);
                startActivity(startadmin);
            }
        });


    }
}
