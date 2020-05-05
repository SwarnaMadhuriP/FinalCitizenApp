package com.example.citizen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class AddAdmin extends AppCompatActivity {
    Button dAddAdmin;
    EditText  demailid,dpassword;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_admin);
        demailid = findViewById(R.id.adminemail);
        dpassword = findViewById(R.id.adminpassword);
        dAddAdmin= findViewById(R.id.addAdmin2);
        db=openOrCreateDatabase("NewDatabase", Context.MODE_PRIVATE, null);
        //db.execSQL("CREATE TABLE IF NOT EXISTS detective(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,password VARCHAR,firstname VARCHAR,lastname VARCHAR,address VARCHAR,phonenumber INT,email VARCHAR,dateOfJoin date,caseAssigned INT,caseClosed INT,branchDetails VARCHAR);");

        dAddAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( checkDataEntered())
                {
                    db.execSQL("INSERT INTO  Admin(email,password)VALUES('" + demailid.getText()+"','"+dpassword.getText() + "');");
                    showMessage("Success", "Admin added Succesfully");

                    //showing detective id

                    Cursor c = db.rawQuery("SELECT email,password FROM Admin WHERE email='" + demailid.getText() + "'", null);
                    if (c.getCount() == 0) {
                        showMessage("Error", "Not found");
                        return;
                    }
                    StringBuffer buffer = new StringBuffer();
                    while (c.moveToNext()) {
                        buffer.append("Admin username is  : " + c.getString(0) + "\n\n");
                        buffer.append("Password is : " + c.getString(1) + "\n\n");

                        showMessage("Admin Details:", buffer.toString()
                        );
                    }



                    clearText();
                }
                else {
                    showMessage("Failed", "Admin Not Added");
                }


            }
        });
    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    boolean checkDataEntered() {
        boolean valid=true;
        if (isEmail(demailid) == false) {
            demailid.setError("Enter valid email!");
            valid=false;
        }
        if (isEmpty(dpassword)) {
            dpassword.setError("Password  is required!");
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
        demailid.setText("");
        dpassword.setText("");
        demailid.requestFocus();

    }

}

