package com.example.employbee;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import android.os.Bundle;
import android.widget.Toast;

public class login_attempt extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_attempt);

    }

    public void returnToMain(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);



       // return(super.loggedInQ());
    }

    public void loginAttempt(View v) {

        EditText password = (EditText) findViewById(R.id.enterPassword);

        if (password.getText().toString().equals("1432")) {

            Toast toast = Toast.makeText(getApplicationContext(),"correct password",Toast.LENGTH_LONG);
            toast.show();

            super.logIn();

            if(super.loggedInQ()) {
                Toast toasst = Toast.makeText(getApplicationContext(),"login successful",Toast.LENGTH_LONG);
                toasst.show();
            };

           // Intent intent = new Intent(this, create_room.class);
           // startActivity(intent);
        }

        else {
            Toast toast = Toast.makeText(getApplicationContext(),"incorrect password",Toast.LENGTH_LONG);
            toast.show();

        }

    }

    }