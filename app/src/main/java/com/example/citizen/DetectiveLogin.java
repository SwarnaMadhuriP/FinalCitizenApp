package com.example.citizen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DetectiveLogin extends AppCompatActivity {
    Button login;
    EditText username,password;
    SQLiteDatabase rdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);

        setupUI();
        setupListeners();

    }

    private void setupUI() {
        username = (EditText) findViewById(R.id.detid);
        password = (EditText) findViewById(R.id.detpwd);
        //register = findViewById(R.id.register);
        login = (Button)findViewById(R.id.logindet);
    }

    private void setupListeners() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUsername();
            }
        });

    }
    void checkUsername() {
        boolean isValid = true;
        if (isEmpty(username)) {
            username.setError("You must enter username to login!");
            isValid = false;
        } else {
            if (!isEmail(username)) {
                username.setError("Enter valid email!");
                isValid = false;
            }
        }

        if (isEmpty(password)) {
            password.setError("You must enter password to login!");
            isValid = false;
        } else {
            if (password.getText().toString().length() < 4) {
                password.setError("Password must be at least 4 chars long!");
                isValid = false;
            }
        }

        //check email and password
        //IMPORTANT: here should be call to backend or safer function for local check; For example simple check is cool
        //For example simple check is cool
        if (isValid) {
            rdb = openOrCreateDatabase("ComplaintRegistrationDB.db", Context.MODE_PRIVATE, null);
            int a=Integer.parseInt(username.getText().toString());
            Cursor c = rdb.rawQuery("SELECT * FROM detective WHERE Id='"+username.getText()+"'", null);
            if (c.getCount() == 0) {
                showMessage("Error", "Detective Not found :(:( please contact admin");
                clearTextuser();
                return;
            } else {
                String passwordValue = password.getText().toString();
                if ( passwordValue.equals(c.getString(2))) {
                    //everything checked we open new activity
                    Intent i = new Intent(DetectiveLogin.this, DetectiveActivity.class);
                    startActivity(i);
                    //we close this activity
                    this.finish();
                } else {
                    Toast t = Toast.makeText(this, "Wrong id or password!", Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        }
    }
    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearTextuser()
    {
        username.setText("");

    }
    public void clearTextpwd(){
        password.setText("");
    }
}
