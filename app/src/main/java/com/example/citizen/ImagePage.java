package com.example.citizen;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.Context;
import android.database.Cursor;

import java.io.ByteArrayOutputStream;

public class ImagePage extends AppCompatActivity {
    ImageView photo;
    Button upload,filecomplaint;
    SQLiteDatabase db;
   String icomplainttype,iconvictname,ivictimname,imobileno,iplace,idate,itime,icomplaintname;
    private static final int IMAGE_PICK_CODE=1000;
    private static final int PERMISSION_CODE=1001;
  String getAlphaNumericString(int n)
    {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_page);
      //  photo=(ImageView)findViewById(R.id.photo);
        upload=(Button)findViewById(R.id.upload);
        filecomplaint=(Button)findViewById(R.id.file22);
        db = openOrCreateDatabase("ComplaintDB.db", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS  CaseRegistration(Id VARCHAR,Status VARCHAR,Type1 VARCHAR,VName VARCHAR,CName VARCHAR,Complaintname VARCHAR,Mobile VARCHAR,Place VARCHAR,Date1 VARCHAR,Time1 VARCHAR);");
        filecomplaint.setOnClickListener(new View.OnClickListener() {
            String Idgenerated;
            int k=6;
            String status="ComplaintRecorded";
            @Override
            public void onClick(View v) {
                showMessage("Succesfully registered","Case Details");
                Idgenerated=getAlphaNumericString(k);
             //   ImageViewtoByte(photo);
                Intent i = getIntent();
                //Getting the Values from First Activity using the Intent received
                icomplainttype=i.getStringExtra("complainttype");
                iconvictname=i.getStringExtra("convictname");
                ivictimname=i.getStringExtra("victimname");
                imobileno=i.getStringExtra("MobileNo");
                icomplaintname=i.getStringExtra("complaintname");
                iplace=i.getStringExtra("place");
                idate=i.getStringExtra("Date");
                itime=i.getStringExtra("time");
               db.execSQL("INSERT INTO CaseRegistration VALUES('" + Idgenerated + "','"+status+"','" +icomplainttype+"','"+ivictimname+"','"+iconvictname+"','"+icomplaintname+"','"+imobileno+"','"+iplace+"','"+idate+"','"+itime + "');");
                Cursor c=db.rawQuery("SELECT * FROM  CaseRegistration", null);
                if(c.getCount()==0)
                {
                    showMessage("Error", "No records found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                 while(c.moveToNext())
                {
                    buffer.append("ComplaintId: "+c.getString(0)+"\n");
                    buffer.append("ComplaintStatus: "+c.getString(1)+"\n");
                    buffer.append("ComplaintType: "+c.getString(2)+"\n");
                    buffer.append("VictimName: "+c.getString(3)+"\n");
                    buffer.append("ConvictName: "+c.getString(4)+"\n");
                    buffer.append("ComplaintName: "+c.getString(5)+"\n");
                    buffer.append("MobileNo: "+c.getString(6)+"\n");
                    buffer.append("Place: "+c.getString(7)+"\n");
                    buffer.append("Date: "+c.getString(8)+"\n");
                    buffer.append("Time: "+c.getString(9)+"\n\n");
                }
                showMessage("Success", "Registration Successful");
          //      showMessage("Complaint Details are :", buffer.toString());
                //clearText();


           }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        String[] permissions={Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions,PERMISSION_CODE);
                    } else {
                          pickImageFromGallery();

                    }

                }
                else{
                    pickImageFromGallery();
                }
            }
        });

    }
    private void pickImageFromGallery(){
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,IMAGE_PICK_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_CODE:{
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    pickImageFromGallery();
                }
                else{
                    Toast.makeText(this,"permission denied",Toast.LENGTH_SHORT).show();

                }
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            photo.setImageURI(data.getData());
        }
    }
  //  private  byte[] ImageViewtoByte(ImageView image){
    //    Bitmap bitmap=((BitmapDrawable)image.getDrawable()).getBitmap();
      //  ByteArrayOutputStream stream=new ByteArrayOutputStream();
        //bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        //byte[] byteArray=stream.toByteArray();
        //return  byteArray;
  //  }
    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
   /* public void clearText()
    {
        complaintname.setText("");
        complaintname.requestFocus();
    }*/
}
