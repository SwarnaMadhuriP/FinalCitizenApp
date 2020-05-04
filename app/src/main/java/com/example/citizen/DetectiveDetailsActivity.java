package com.example.citizen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class DetectiveDetailsActivity extends AppCompatActivity {
    String detide,no_of_cases;
    TextView id,first,second,mob,email,address,assign,close,runon;
    SQLiteDatabase dbr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detective_details);
        Intent intent=getIntent();
        detide=intent.getStringExtra("detective id:");
        no_of_cases=intent.getStringExtra("noofcases");
        Toast g = Toast.makeText(this, detide, Toast.LENGTH_SHORT);
        g.show();
        dbr = openOrCreateDatabase("rachanadb", Context.MODE_PRIVATE, null);
        Cursor f=dbr.rawQuery("SELECT * FROM detective WHERE  ID='"+detide+"'",null);
        id=(TextView)findViewById(R.id.detid);
        first=(TextView)findViewById(R.id.detfirstname);
        //  register=(Button)findViewById(R.id.registercomplaint);
        second=(TextView)findViewById(R.id.detlastname);
        mob=(TextView)findViewById(R.id.detmobileno);
        email=(TextView) findViewById(R.id.detemail);
        address=(TextView) findViewById(R.id.detaddress);
        assign=(TextView) findViewById(R.id.detassigned);
        close=(TextView) findViewById(R.id.detclosed);
        runon=(TextView)findViewById(R.id.deton);
        f.moveToFirst();
        id.setText("ID:"+f.getString(0));
       first.setText("firstname:"+f.getString(2));
        second.setText("Second name:"+f.getString(3));
       mob.setText("Mobile no:"+f.getString(5));
        email.setText("Email:"+f.getString(6));
        address.setText("Address:"+f.getString(4));
        assign.setText("Number of cases assigned:"+f.getString(8));
        close.setText("Number of cases closed:"+f.getString(9));
        runon.setText("Cases handling now:"+no_of_cases);
    }
}
