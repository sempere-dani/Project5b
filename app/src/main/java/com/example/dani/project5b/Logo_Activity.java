package com.example.dani.project5b;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;


public class Logo_Activity extends Activity {
//Definimos el tiempo de espera hasta que cambie de pantalla

    private static final long DELAY=4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);


        //Creamos el timer para que quite el logo y pase a la ventana principal

        TimerTask task = new TimerTask() {

            @Override
            public void run() {
// Start the next activity
                Intent mainIntent = new Intent().setClass(
                        Logo_Activity.this, MainActivity.class);
                startActivity(mainIntent);
// Close the activity so the user won't able to go back this
// activity pressing Back button
                finish();
            }
        };
// Simulate a long loading process on application startup.
        Timer timer = new Timer();

        timer.schedule(task, DELAY);

        //Cargamos el spinner
        final ProgressBar spinner;
        spinner = (ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.VISIBLE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_logo, menu);
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
}
