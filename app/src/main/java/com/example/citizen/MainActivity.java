package com.example.citizen;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity{
    Button citizen1,admin1,detective1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        citizen1=(Button)findViewById(R.id.citizen);
        admin1=(Button)findViewById(R.id.admin);
        detective1=(Button)findViewById(R.id.detective);
        citizen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startcitizen=new Intent(MainActivity.this,ComplaintRegistration.class);
                startActivity(startcitizen);
            }
        });



    }
}
