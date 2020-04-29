package com.example.citizen;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.view.View.OnClickListener;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ComplaintStatus extends AppCompatActivity  {
    EditText complaintid;
    Button checkstatus;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_status);
        complaintid=(EditText)findViewById(R.id.complaintid);
        checkstatus=(Button)findViewById(R.id.checkstatus);
        db=openOrCreateDatabase("ComplaintRegistrationDB.db", Context.MODE_PRIVATE, null);
        //db.execSQL("CREATE TABLE IF NOT EXISTS student(rollno VARCHAR,name VARCHAR,marks VARCHAR);");
        checkstatus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (complaintid.getText().toString().trim().length() == 0) {
                    showMessage("Error", "Please enter ComplaintId");
                    return;
                }
                Cursor c = db.rawQuery("SELECT * FROM CaseRegistration WHERE Id='" + complaintid.getText() + "'", null);
                if (c.getCount() == 0) {
                    showMessage("Error", "Complaint Not found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (c.moveToNext()) {
                    buffer.append("Complaint Status is : " + c.getString(1) + "\n\n");
                   // buffer.append("Name: " + c.getString(1) + "\n");
                    //buffer.append("Marks: " + c.getString(2) + "\n\n");

                    showMessage("ComplaintStatus:", buffer.toString()
                    );
                }
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
    public void clearText()
    {
        complaintid.setText("");

    }

}


