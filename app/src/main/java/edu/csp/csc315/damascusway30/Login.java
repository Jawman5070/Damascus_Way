package edu.csp.csc315.damascusway30;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    private Button loginButton;
    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
        if (email.getText().toString() != "" && password.getText().toString().equals("password"))
        {
            // do something
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);

        }
        else loginButton.setText("Try Again");
    }
}
