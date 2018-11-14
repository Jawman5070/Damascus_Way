package edu.csp.csc315.damascusway30;

import android.content.Intent;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Login extends AppCompatActivity {

    private Button loginButton;
    private EditText email;
    private EditText password;
    private DatabaseIO _db;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initToolbar();
        LocalData.getInstance().queue = Volley.newRequestQueue(this);
        _db = new DatabaseIO();
        _db.ParsingExample();


       // String devConnectionString = "jdbc:mysql://107.180.46.186/damascus_way";
       // String devUser = "damascus_way_mob";
       // String devPassword = "b^l}+mS_T0FH";

      //  _db = new DatabaseIO(devConnectionString,devUser,devPassword);

         loginButton = (Button) findViewById(R.id.loginButton);
         email = (EditText) findViewById(R.id.editText2);
         password = (EditText) findViewById(R.id.editText);

         loginButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 login();
             }
         });

    }

    public void login()
    {

        //Test JSON
       // DatabaseIO db = new DatabaseIO();
        Resident r;
       // r = db.ParsingExample();
       // try {
       //     Thread.sleep(10000);
       // } catch (InterruptedException e) {
       //     e.printStackTrace();
       // }
        r = LocalData.getInstance().getCurrentResident();

        /*
        Employee employee = _db.GetEmployee(email.getText().toString(), password.getText().toString());

        if(employee != null)
        {
            // Employee with valid credentials found in the database
            //Intent intent = new Intent(Login.this, MainActivity.class);
            //startActivity(intent);
            // pass employee into the next view
        }
        */


        if (email.getText().toString() != "" && password.getText().toString().equals("password"))
        {
            Employee e = new Employee(0, "The", "Dude");
            LocalData.getInstance().setCurrentEmployee(e);

            // do something1
            Intent intent = new Intent(Login.this, RoundsActivity.class);

            startActivity(intent);

        }
        else loginButton.setText("Try Again");
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //set the toolbar logo to Damascus Way
        toolbar.setLogo(R.drawable.dw_logo);
        //matches remaining background to white
        toolbar.setBackgroundColor(Color.WHITE);
    }
}
