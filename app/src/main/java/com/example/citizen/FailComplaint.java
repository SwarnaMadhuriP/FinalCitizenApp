package com.example.citizen;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class FailComplaint extends Activity implements android.view.View.OnClickListener{
    Button file,next;
    EditText complaintname,victimname,convictname,mobileno,place,date,time;
    SQLiteDatabase db;
    String complainttypei,victimnamei,convictnamei,mobilenoi,complaintnamei,placei,datei,timei;
    Spinner s;

    String [] complaint_type={"roberry/Theft","Kidnap","Murder"};

    /* String getAlphaNumericString(int n)
    {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fail_complaint);
        next=(Button)findViewById(R.id.next);
        convictname=(EditText)findViewById(R.id.convictname2);
        victimname=(EditText)findViewById(R.id.victimname2);
        mobileno=(EditText)findViewById(R.id.mobileno2);
        place=(EditText)findViewById(R.id.place2);
        date=(EditText)findViewById(R.id.date2);
        time=(EditText)findViewById(R.id.time2);
        complaintname=(EditText)findViewById(R.id.complaintname);
        s= (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adapter= new ArrayAdapter(FailComplaint.this,android.R.layout.simple_spinner_item,complaint_type);
        s.setAdapter(adapter);
       // db = openOrCreateDatabase("RegistrationDB1", Context.MODE_PRIVATE, null);
        //db.execSQL("CREATE TABLE IF NOT EXISTS cregistration(Name VARCHAR,Id VARCHAR);");
       // file.setOnClickListener(this);
        next.setOnClickListener(this);


    }
    public void onClick(View view) {
// Inserting a record to the Student table
      //  String Id;
        //int k=6;
        if(view==next){
            if (complaintname.getText().toString().trim().length() == 0||convictname.getText().toString().trim().length()==0||victimname.getText().toString().trim().length()==0||
            mobileno.getText().toString().trim().length()==0||place.getText().toString().trim().length()==0||date.getText().toString().trim().length()==0
            ||time.getText().toString().trim().length()==0) {
                showMessage("Error", "Please enter all values");
                return;
            }
            /* Id=getAlphaNumericString(k);
            db.execSQL("INSERT INTO cregistration VALUES('" + complaintname.getText() + "','" + Id  + "');");
            Cursor c=db.rawQuery("SELECT * FROM  cregistration", null);
            if(c.getCount()==0)
            {
                showMessage("Error", "No records found");
                return;
            }
            StringBuffer buffer=new StringBuffer();
            while(c.moveToNext())
            {
                buffer.append("ComplaintName: "+c.getString(0)+"\n");
                buffer.append("ComplaintId: "+c.getString(1)+"\n\n");
            }
            showMessage("Success", "Registration Successful");
            showMessage("Registration Details", buffer.toString());
            clearText();*/
            complainttypei=s.getSelectedItem().toString();
            convictnamei=convictname.getText().toString();
            victimnamei=victimname.getText().toString();
            complaintnamei=complaintname.getText().toString();
            mobilenoi=mobileno.getText().toString();
            placei=place.getText().toString();
            datei=date.getText().toString();
            timei=time.getText().toString();
            Intent i=new Intent(FailComplaint.this,ImagePage.class);
            i.putExtra("complainttype",complainttypei);
            i.putExtra("convictname",convictnamei);
            i.putExtra("complaintname",complaintnamei);
            i.putExtra("victimname",victimnamei);
            i.putExtra("MobileNo",mobilenoi);
            i.putExtra("place",placei);
            i.putExtra("Date",datei);
            i.putExtra("time",timei);
            startActivity(i);
        }
    }

    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
   /* public void clearText()
    {
        complaintname.setText("");
        complaintname.requestFocus();
    }*/
}

