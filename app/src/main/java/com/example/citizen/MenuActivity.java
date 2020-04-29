package com.example.citizen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {
    Button detective_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        detective_add=(Button)findViewById(R.id.addDetective);

        detective_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registration=new Intent(MenuActivity.this,NewDetective.class);
                startActivity(registration);
            }
        });
    }
}
