package com.example.citizen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {
    Button detective_add,case_details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        detective_add=(Button)findViewById(R.id.addDetective);
        case_details=(Button)findViewById(R.id.caseDetails);

        detective_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registration=new Intent(MenuActivity.this,NewDetective.class);
                startActivity(registration);
            }
        });
        case_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent icasedetails=new Intent(MenuActivity.this,CaseDetails.class);
                startActivity(icasedetails);
            }
        });

    }
}
