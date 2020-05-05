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
public class LoginAdminActivity extends AppCompatActivity {
    Button login;
    EditText username,password;
    SQLiteDatabase db;
    String validusername,validpassword,usernameValue,passwordValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);
        db=openOrCreateDatabase("NewDatabase", Context.MODE_PRIVATE, null);
        setupUI();
        setupListeners();
    }
    private void setupUI() {
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
    }
    private void setupListeners() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c = db.rawQuery("SELECT email,password FROM Admin WHERE email='" + username.getText() + "'", null);
                if (c.getCount() == 0) {
                    showMessage("Error", "user name not Found");
                    clearText();
                    return;
                }
                //   StringBuffer buffer = new StringBuffer();
                while (c.moveToNext()) {
                    validusername=c.getString(0) ;
                    validpassword= c.getString(1);
                }
                // clearText();
                if(!checkUsername()){
                    showMessage("Error", "Please enter all values");
                    return;
                }
                usernameValue = username.getText().toString();
                passwordValue = password.getText().toString();
                if (usernameValue.equals(validusername) && passwordValue.equals(validpassword)) {
                        //everything checked we open new activity
                        Intent i = new Intent(LoginAdminActivity.this, MenuActivity.class);
                        startActivity(i);
                        i.putExtra("user_name",usernameValue);
                        //we close this activity
                      //  this.finish();
                } else {
                       // Toast t = Toast.makeText(this, "Wrong email or password!", Toast.LENGTH_SHORT);
                        showMessage("Invalid", "Wrong password");
                        //t.show();
                }
            }

        });

    }
    boolean checkUsername() {
        boolean isValid = true;
        if (isEmpty(username)) {
            username.setError("You must enter username to login!");
            isValid = false;
        } /*else {
            if (!isEmail(username)) {
                username.setError("Enter valid email!");
                isValid = false;
            }
        }*/
        if (isEmpty(password)) {
            password.setError("You must enter password to login!");
            isValid = false;
        } /*else {
            if (password.getText().toString().length() < 4) {
                password.setError("Password must be at least 4 chars long!");
                isValid = false;
            }
        }*/
        //check email and password
        //IMPORTANT: here should be call to backend or safer function for local check; For example simple check is cool
        //For example simple check is cool
        /*if (isValid) {
            String usernameValue = username.getText().toString();
            String passwordValue = password.getText().toString();
            if (usernameValue.equals(validusername) && passwordValue.equals(validpassword)) {
                //everything checked we open new activity
                Intent i = new Intent(LoginAdminActivity.this, MenuActivity.class);
                startActivity(i);
                //we close this activity
                this.finish();
            } else {
                Toast t = Toast.makeText(this, "Wrong email or password!", Toast.LENGTH_SHORT);
                t.show();
            }
        }*/
        return isValid;
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
    public void clearText()
    {
        username.setText("");
        password.setText("");
        username.requestFocus();

    }
}
