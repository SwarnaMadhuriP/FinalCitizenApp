package com.example.citizen;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FailComplaint extends Activity implements android.view.View.OnClickListener{
    Object icomplainttype;
    Editable iconvictname;
    Editable ivictimname;
    Editable imobileno;
    Editable iplace;
    Editable idate;
    Editable itime;
    Editable icomplaintname;
    EditText complaintname,victimname,convictname,mobileno,place,date,time;
    Button  register;
    SQLiteDatabase db;
    Spinner s;
    String [] complaint_type={"roberry/Theft","Kidnap","Murder"};
    String getAlphaNumericString(int n)
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
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fail_complaint);
        complaintname=(EditText)findViewById(R.id.complaintname);
        register=(Button)findViewById(R.id.registercomplaint);
        convictname=(EditText)findViewById(R.id.convictname2);
        victimname=(EditText)findViewById(R.id.victimname2);
        mobileno=(EditText)findViewById(R.id.mobileno2);
        place=(EditText)findViewById(R.id.place2);
        date=(EditText)findViewById(R.id.date2);
        time=(EditText)findViewById(R.id.time2);
        s= (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adapter= new ArrayAdapter(FailComplaint.this,android.R.layout.simple_spinner_item,complaint_type);
        s.setAdapter(adapter);
        db = openOrCreateDatabase("ComplaintRegistrationDB", Context.MODE_PRIVATE, null);
        register.setOnClickListener(this);
    }
    public void onClick(View view) {
// Inserting a record to the case table
        String Idgenerated;
        int k=6;
        String status="ComplaintRecorded";
        if(view==register){
            Idgenerated=getAlphaNumericString(k);
           /* by swarna if (complaintname.getText().toString().trim().length() == 0||convictname.getText().toString().trim().length()==0||victimname.getText().toString().trim().length()==0||
            mobileno.getText().toString().trim().length()==0||place.getText().toString().trim().length()==0||date.getText().toString().trim().length()==0
            ||time.getText().toString().trim().length()==0) {
                showMessage("Error", "Please enter all values");
                return;
            }*/
            if(!checkDataEntered()){
                showMessage("Error", "Please enter all values");
                return;
            }
            icomplainttype=s.getSelectedItem();
            ivictimname=victimname.getText();
            iconvictname=convictname.getText();
            icomplaintname=complaintname.getText();
            imobileno=mobileno.getText();
            iplace=place.getText();
            idate=date.getText();
            itime=time.getText();
            db.execSQL("INSERT INTO CaseRegistration (Status,Type1,VName,CName,Complaintname,Mobile,Place,Date1,Time1,assigned) VALUES('" +status+"','" +icomplainttype+"','"+ivictimname+"','"+iconvictname+"','"+icomplaintname+"','"+imobileno+"','"+iplace+"','"+idate+"','"+itime + "',0);");
            Cursor c = db.rawQuery("SELECT ID,assigned FROM CaseRegistration WHERE Mobile='" +mobileno.getText()+ "'", null);
            if(c.getCount()==0)
            {
                showMessage("Error", "No records found");
                return;
            }
            StringBuffer buffer=new StringBuffer();
            while(c.moveToNext())
            {
                buffer.append("ComplaintId: "+c.getString(0)+"\n");
                buffer.append("Assigned: "+c.getString(1)+"\n\n");
            }
            showMessage("Success", "Registration Successful");
            showMessage("Complaint Details are :", buffer.toString());
            clearText();

        }
    }
    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
    boolean checkDataEntered() {
        boolean valid=true;
        if (isEmpty(complaintname)) {
            complaintname.setError("Complaint name is required!!");
            valid=false;
        }
        if (isEmpty(victimname)) {
            Toast t = Toast.makeText(this, "You must enter victim name to file a complaint!", Toast.LENGTH_SHORT);
            t.show();
            valid=false;
        }
        if (isEmpty(convictname)) {
            convictname.setError("Convict name is required!!If not known specify as unknown");
            valid=false;
        }
        if (isEmpty(place)) {
            place.setError("place of incident is required!!");
            valid=false;
        }
        if (isEmpty(mobileno)) {
            mobileno.setError("Mobile Number is required!");
            valid=false;
        }
        if(mobileno.getText().toString().length() !=10){
            mobileno.setError("Invalid Phone number");
            valid=false;
        }
        return valid;
    }

    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText()
    {
        complaintname.setText("");
        convictname.setText("");
        victimname.setText("");
        mobileno.setText("");
        place.setText("");
        date.setText("");
        time.setText("");
        complaintname.requestFocus();
    }
}