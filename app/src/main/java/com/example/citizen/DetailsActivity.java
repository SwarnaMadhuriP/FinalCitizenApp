package com.example.citizen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {
    SQLiteDatabase db;
    TextView tcomplainttype,tid,tconvictname,tvictimname,tmobileno,tplace,tdate,ttime,tcomplaintname,tstatus,tassigned;
    EditText updetails;
    Button upi;
    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        // EachCaseDetail=(TextView)findViewById(R.id.eachcasedetail);
        tcomplainttype=(TextView)findViewById(R.id.ecomplainttype);
        tcomplaintname=(TextView)findViewById(R.id.ecomplaintname);
        //  register=(Button)findViewById(R.id.registercomplaint);
        tconvictname=(TextView)findViewById(R.id.econvictname2);
        tvictimname=(TextView)findViewById(R.id.evictimname2);
        tmobileno=(TextView) findViewById(R.id.emobileno2);
        tplace=(TextView) findViewById(R.id.eplace2);
        tdate=(TextView) findViewById(R.id.edate2);
        ttime=(TextView) findViewById(R.id.etime2);
        tid=(TextView)findViewById(R.id.ecomplaintid);
        tstatus=(TextView)findViewById(R.id.estatus);
        tassigned=(TextView)findViewById(R.id.eAssigned);
        updetails=(EditText)findViewById(R.id.update_details);
        upi=(Button)findViewById(R.id.up);
        Intent  icaseid=getIntent();
        //
        final String f=icaseid.getStringExtra("id_pressed");
        s=f;
        db=openOrCreateDatabase("rachanadb", Context.MODE_PRIVATE, null);
        Cursor c = db.rawQuery("SELECT * FROM CaseRegistration WHERE  ID='"+s+"'", null);

        //d.moveToFirst();
        if(c.getCount()==0)
        {
            showMessage("Error", "No records found");
            return;
        }
        c.moveToFirst();
        //StringBuffer buffer=new StringBuffer();

            tcomplainttype.setText("Complainttype: "+c.getString(3));
            tcomplaintname.setText("Complaintname: "+c.getString(6));
            tconvictname.setText("ConvictName: "+c.getString(5));
            tvictimname.setText("VictimName: "+c.getString(4));
            tmobileno.setText("Mobile: "+c.getString(7));
            tplace.setText("Place: "+c.getString(8));
            tdate.setText("Date: "+c.getString(9));
            ttime.setText("Time: "+   c.getString(10));
            tid.setText("ComplaintId: "+c.getString(0));
            tstatus.setText("ComplaintStatus: "+c.getString(2));
            tassigned.setText("Assigned: "+   c.getString(11));
//            //  "ComplaintId: "+c.getString(0)+"ComplaintStatus: "+c.getString(2)+
//            /*buffer.append("ComplaintId: "+c.getString(0)+"\n");
//            buffer.append("ComplaintStatus: "+c.getString(2)+"\n");
//            buffer.append("Complainttype: "+c.getString(3)+"\n");
//            buffer.append("VictimName: "+c.getString(4)+"\n");
//            buffer.append("ConvictName: "+c.getString(5)+"\n");
//            buffer.append("Complaintname: "+c.getString(6)+"\n");
//            buffer.append("Mobile: "+c.getString(7)+"\n");
//            buffer.append("Place: "+c.getString(8)+"\n");
//            buffer.append("Date: "+c.getString(9)+"\n");
//            buffer.append("Time: "+   c.getString(10)+"\n");
//            buffer.append("Assigned: "+   c.getString(11)+"\n\n");*/

        c = db.rawQuery("SELECT details FROM detcase WHERE  case_id='"+s+"'", null);
        c.moveToFirst();
        updetails.setText(c.getString(0));
        //showMessage("Success", "Registration Successful");
        //  showMessage("Complaint Details are :", buffer.toString());
        upi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues newValues = new ContentValues();
                newValues.put("details", updetails.getText().toString());

                db.update("detcase", newValues, "case_id='"+s+"'", null);
                showMessage("update","case details have been updated");
            }
        });


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
