package com.example.citizen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ComplaintRegistration extends AppCompatActivity {
    Button filecomplaint,complaintstatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_registration);
        filecomplaint=(Button)findViewById(R.id.filecomplaint);
        complaintstatus=(Button)findViewById(R.id.complaintstatus);
        complaintstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startcomplaintstatus=new Intent(ComplaintRegistration.this,ComplaintStatus.class);
                startActivity(startcomplaintstatus);
            }
        });
        filecomplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startfilecomplaint=new Intent(ComplaintRegistration.this,FailComplaint.class);
                startActivity(startfilecomplaint);
            }
        });
    }
}
