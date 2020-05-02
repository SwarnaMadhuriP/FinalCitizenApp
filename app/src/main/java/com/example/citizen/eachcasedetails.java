package com.example.citizen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class eachcasedetails extends AppCompatActivity {
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eachcasedetails);
        Intent  icaseid=getIntent();
        final String f=icaseid.getStringExtra("case_id");
        String s=f;
        db=openOrCreateDatabase("ComplaintRegistrationDB", Context.MODE_PRIVATE, null);
        Cursor c = db.rawQuery("SELECT * FROM CaseRegistration WHERE  ID='"+s+"'", null);
        if(c.getCount()==0)
        {
            showMessage("Error", "No records found");
            return;
        }
        StringBuffer buffer=new StringBuffer();
        while(c.moveToNext())
        {   buffer.append("ComplaintId: "+c.getString(0)+"\n");
            buffer.append("ComplaintStatus: "+c.getString(2)+"\n");
            buffer.append("Complainttype: "+c.getString(3)+"\n");
            buffer.append("VictimName: "+c.getString(4)+"\n");
            buffer.append("ConvictName: "+c.getString(5)+"\n");
            buffer.append("Complaintname: "+c.getString(6)+"\n");
            buffer.append("Mobile: "+c.getString(7)+"\n");
            buffer.append("Place: "+c.getString(8)+"\n");
            buffer.append("Date: "+c.getString(9)+"\n");
            buffer.append("Time: "+   c.getString(10)+"\n");
            buffer.append("Assigned: "+   c.getString(11)+"\n\n");
        }
        //showMessage("Success", "Registration Successful");
        showMessage("Complaint Details are :", buffer.toString());



    }
    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}
