package com.example.citizen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {
    Button detective_add,case_details,add_admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        detective_add=(Button)findViewById(R.id.addDetective);
        case_details=(Button)findViewById(R.id.caseDetails);
        add_admin=(Button)findViewById(R.id.addAdmin);
        Intent user=getIntent();
        String usernamevalue=user.getStringExtra("user_name");
        Toast t = Toast.makeText(this, "Welcome Admin "+usernamevalue, Toast.LENGTH_SHORT);
        t.show();

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
        add_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent igotoaddadmin=new Intent(MenuActivity.this,AddAdmin.class);
                startActivity(igotoaddadmin);
            }
        });

    }
}
