package com.example.citizen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class DetectiveActivity extends AppCompatActivity {
    Button cases;
    SQLiteDatabase ddb;
    String detid;
    int no_of_cases;

    ImageButton x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detective);
        //cases = (Button) findViewById(R.id.get_cases);
        ddb = openOrCreateDatabase("rachanadb", Context.MODE_PRIVATE, null);
        Intent intent = getIntent();
        detid = intent.getStringExtra("id");
        Toast f = Toast.makeText(this, detid, Toast.LENGTH_SHORT);
        f.show();
        x=(ImageButton) findViewById(R.id.imageButton);
        x.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                Intent detectivedetails = new Intent(DetectiveActivity.this, DetectiveDetailsActivity.class);
                String dey = String.valueOf(detid);
                String h=String.valueOf(no_of_cases);
                detectivedetails.putExtra("noofcases",h);
                detectivedetails.putExtra("detective id:", dey);
                startActivity(detectivedetails);
            }
        });
//        ddb.execSQL("INSERT INTO detcase VALUES(1,5,'open')");
//        ddb.execSQL("INSERT INTO detcase VALUES(1,3,'open')");
//        ddb.execSQL("INSERT INTO detcase VALUES(2,1,'open')");
//        ddb.execSQL("INSERT INTO detcase VALUES(2,6,'open')");
       Cursor c = ddb.rawQuery("SELECT COUNT(*) FROM detcase WHERE det_id='" + detid + "'", null);
        c.moveToFirst();
        no_of_cases = Integer.parseInt(c.getString(0));
        StringBuffer a = new StringBuffer();
        a.append("no_of_cases handled by you:" + no_of_cases + "");
        Toast t = Toast.makeText(this, a.toString(), Toast.LENGTH_SHORT);
        t.show();

        LinearLayout layout;
        layout = (LinearLayout) findViewById(R.id.l2);
        Cursor d = ddb.rawQuery("SELECT case_id FROM detcase WHERE det_id='" + detid + "'", null);
        d.moveToFirst();
        if (no_of_cases == 0) {
            Toast g = Toast.makeText(this, "Cases not assigned", Toast.LENGTH_SHORT);
            g.show();
            return;
        }
        for (int i = 1; i <= no_of_cases; i++) {
            final Button button = new Button(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(50, 30, 30, 30);
            button.setLayoutParams(params);
            //  params.setMargins(left, top, right, bottom);
            // button.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            button.setId(i);
            button.setGravity(Gravity.CENTER);
            final int finalI = Integer.parseInt(d.getString(0));
            d.moveToNext();
            button.setText("Case No " + finalI + " Details");

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "case no pressed is " + finalI, Toast.LENGTH_SHORT).show();
                    Intent gotocase = new Intent(DetectiveActivity.this, DetailsActivity.class);
                    int j = finalI;
                    String f = String.valueOf(j);
                    gotocase.putExtra("id_pressed", f);
                    startActivity(gotocase);
                    return;
                }
            });
            layout.addView(button);
        }




    }


}
