package com.example.dani.project5b;

import android.app.Activity;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ToggleButton;


public class Settings extends Activity {

    private  AudioManager mAudioManager;
    private int mVolume=6, mVolumeMax=10, mVolumeMin=0;
    private int sonando=0;
    private MediaPlayer mPlayer;
    private boolean audioOn;
    public static final String Name="AudioSettings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        //capturamos el estado del toggleButton
        final ToggleButton tb = (ToggleButton) findViewById(R.id.toggleButton);

       final SharedPreferences pref= getSharedPreferences("toggleButton", MODE_PRIVATE);
        tb.setChecked(pref.getBoolean("toggleButton",true));

                 //Guardamos las preferencias del ususario

                tb.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {

                          SharedPreferences.Editor editor = getSharedPreferences("toggleButton", MODE_PRIVATE).edit();
                          // SharedPreferences.Editor editor = shPref.edit();
                            editor.putBoolean("toggleButton",tb.isChecked());
                            editor.commit();


                    }
                }

                );

/*AUDIO*/



        //Capturamos el servicio para manejar Sonidos
        mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);


        //Cargamos la cancion
        mPlayer = MediaPlayer.create(this, R.raw.trompeta);

        //Esperaremos a que este cargada
        mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.d("AUDIO", "Cargada la cancion");

            }
        });
        // Suena la cancion
        tb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPlayer.setLooping(true);
                if(tb.isChecked()){

                    sonando=1;

                    mPlayer.setVolume((float) mVolume / mVolumeMax,
                            (float) mVolume / mVolumeMax);


                    mPlayer.start();
                    SharedPreferences.Editor prefEd = getSharedPreferences("toggleButton",MODE_PRIVATE).edit();
                    prefEd.putBoolean("toggleButton", tb.isChecked());
                    prefEd.commit();

                }else if(sonando==1){

                    sonando=2;
                    mPlayer.setVolume((float) mVolume / mVolumeMax,
                            (float) mVolume / mVolumeMax);
                    mPlayer.pause();
                    SharedPreferences.Editor prefEd = getSharedPreferences("toggleButton",MODE_PRIVATE).edit();
                    prefEd.putBoolean("toggleButton", tb.isChecked());
                    prefEd.commit();
                }else{

                    sonando=1;
                    mPlayer.setVolume((float) mVolume / mVolumeMax,
                            (float) mVolume / mVolumeMax);
                    mPlayer.start();

                }
            }
            // Release resources & clean up

            protected void onPause() {
                mPlayer.release();
            }


        });




    }


    @Override
   public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
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
