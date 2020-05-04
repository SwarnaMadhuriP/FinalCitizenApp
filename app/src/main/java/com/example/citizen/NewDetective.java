package com.example.citizen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;
import android.app.AlertDialog.Builder;



public class NewDetective extends AppCompatActivity {
    Button register;
    EditText firstName,lastName,address,email,phoneNumber,dateOfJoin,branch,noOfCases,caseClosed,password;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_detective);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        address = findViewById(R.id.address);
        email = findViewById(R.id.email);
        phoneNumber = findViewById(R.id.phoneNumber);
        dateOfJoin = findViewById(R.id.date);
        branch = findViewById(R.id.details);
        noOfCases = findViewById(R.id.caseassigned);
        caseClosed = findViewById(R.id.caseclosed);
        password = findViewById(R.id.password);
        register = findViewById(R.id.submit);

        db=openOrCreateDatabase("rachanadb", Context.MODE_PRIVATE, null);
        //db.execSQL("CREATE TABLE IF NOT EXISTS detective(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,password VARCHAR,firstname VARCHAR,lastname VARCHAR,address VARCHAR,phonenumber INT,email VARCHAR,dateOfJoin date,caseAssigned INT,caseClosed INT,branchDetails VARCHAR);");

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if( checkDataEntered())
                {
                    db.execSQL("INSERT INTO detective (password,firstname,lastname,address,phonenumber,email,dateOfJoin,caseAssigned,caseClosed,branchDetails)VALUES('" + password.getText() + "','" + firstName.getText() + "','" + lastName.getText() +

                            "','" + address.getText() + "','" + phoneNumber.getText() + "','" + email.getText() +
                            "','" + dateOfJoin.getText() + "','" + noOfCases.getText() + "','" + caseClosed.getText() + "','" + branch.getText() + "');");

                    showMessage("Success", "Record added");

                    //showing detective id

                    Cursor c = db.rawQuery("SELECT ID,password FROM detective WHERE email='" + email.getText() + "'", null);
                    if (c.getCount() == 0) {
                        showMessage("Error", "Not found");
                        return;
                    }
                    StringBuffer buffer = new StringBuffer();
                    while (c.moveToNext()) {
                        buffer.append("Detective ID : " + c.getString(0) + "\n\n");
                        buffer.append("Password: " + c.getString(1) + "\n\n");

                        showMessage("Detective Details:", buffer.toString()
                        );
                    }



                    clearText();
                }
                else {
                    showMessage("Failed", "Record Not Added");
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
        if (isEmpty(firstName)) {
            Toast t = Toast.makeText(this, "You must enter first name to register!", Toast.LENGTH_SHORT);
            t.show();
            valid=false;
        }

        if (isEmpty(lastName)) {
            lastName.setError("Last name is required!");
            valid=false;
        }

        if (isEmail(email) == false) {
            email.setError("Enter valid email!");
            valid=false;
        }
        if (isEmpty(phoneNumber)) {
            phoneNumber.setError("Phone Number is required!");
            valid=false;
        }
        if(phoneNumber.getText().toString().length() !=10){
            phoneNumber.setError("Invalid Phone number");
            valid=false;
        }
        if (isEmpty(noOfCases)) {
            noOfCases.setError("Enter 0 if its new detective!");
            valid=false;
        }
        if (isEmpty(caseClosed)) {
            caseClosed.setError("Enter 0 if its new detective!");
            valid=false;
        }
        return valid;

    }

    public void showMessage(String title,String message)

    {
        Builder builder=new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }

    public void clearText()

    {
        firstName.setText("");
        lastName.setText("");
        address.setText("");
        noOfCases.setText("");
        caseClosed.setText("");
        branch.setText("");
        dateOfJoin.setText("");
        phoneNumber.setText("");
        email.setText("");

        firstName.requestFocus();

    }

}
