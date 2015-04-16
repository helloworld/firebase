package com.thupukari.firebase;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.firebase.client.Firebase;


public class MainActivity extends ActionBarActivity {
    private Firebase myFirebaseRef = new Firebase("https://blistering-fire-8026.firebaseio.com/");
    public final static String EXTRA_MESSAGE = "com.thupukari.todos.MESSAGE";
    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void sendMessage(View view) {
        // Get the text of the text-field
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();

        //Save the string to SharedPreferences
        saveString(message);
    }

    public void saveString(String message){
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("savedString", message);
        // Commit the edits
        editor.commit();

        myFirebaseRef.child("message").setValue(message);
    }

    public String getMessage(String key){
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String message = settings.getString("savedString", "Default");
        EditText editText = (EditText) findViewById(R.id.edit_message);
        editText.setText(message);
        return message;
    }

}
