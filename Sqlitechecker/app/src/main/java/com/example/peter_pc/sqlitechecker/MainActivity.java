package com.example.peter_pc.sqlitechecker;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Your Data fields and Buttons
    EditText id,fn,ln;
    Button save,listed;

    //Data holders for user input
    private String identry,fnentry,lnentry;

    /*Objectts of your Database helper class where the Database and Table are created and also methods
    to add and retrieve user data are*/
    MyDbHandler dbhandler;

    //SQliteDatabase Object
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id=(EditText)findViewById(R.id.idinput);
        fn=(EditText)findViewById(R.id.fnameinput);
        ln=(EditText)findViewById(R.id.lnameinput);
        save=(Button)findViewById(R.id.submit);
        listed=(Button)findViewById(R.id.retieve);
    }

    //The onclick method  called when Save button is clicked
    public void saveData(View v){
        //get the input fed by user
        identry=id.getText().toString();
        fnentry=fn.getText().toString();
        lnentry=ln.getText().toString();

        //Validate such that no empty record is saved
        if (!identry.isEmpty()|| !fnentry.isEmpty())
         {
             fn.setError(null);
             ln.setError(null);

             /*Surrounded by try & Catch just to ensure the app does not crush in anycaseb*/
              try {
                   //Initialize your helper class object
                  dbhandler= new MyDbHandler(getBaseContext());

                  //initialize the Sqlitedatabase object
                  db=dbhandler.getWritableDatabase();

                  //call method from helper class that saves the record to SQliteDatabase
                  dbhandler.addUsersInfo(identry,fnentry,lnentry,db);

                  //Closes your Database
                  dbhandler.close();

                  //log to show when record is saved
                  Log.e("NEW RECORD","User Info Saved");

              }catch (Exception e){
                  Toast.makeText(this, "Problem with Db", Toast.LENGTH_SHORT).show();
              }

         }else { fn.setError("Required"); ln.setError("Required");}
    }

    //The onclick method  called when View button is clicked
    public void showlist(View view){
        dbhandler= new MyDbHandler(getBaseContext());
        db=dbhandler.getReadableDatabase();
        /*calls the method which retrievs all saved data from User Database which receives
        SqliteDatabase Object as a parameter*/
        dbhandler.listUsers(db);
    }
}
