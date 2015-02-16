package com.example.dani.project5b;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;


public class MainActivity extends Activity {
    private boolean audioOn;
    public static final String Name = "AudioSettings";
    private AudioManager mAudioManager;
    private int mVolume = 6, mVolumeMax = 10, mVolumeMin = 0;
    private int sonando = 0;
    private MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageButton ib = (ImageButton) findViewById(R.id.imageSet);
        final ImageButton ib2 = (ImageButton) findViewById(R.id.imagePower);
        final ImageButton ib3 = (ImageButton) findViewById(R.id.imageBad);


        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent second = new Intent(
                        MainActivity.this,
                        Settings.class

                );
                startActivity(second);
            }
        });

        ib2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(1);
            }
        });

        ib3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent third = new Intent(
                        MainActivity.this,
                        Like.class

                );
                startActivity(third);
            }
        });

     /*  SharedPreferences pref = getSharedPreferences("toggleButton", MODE_PRIVATE);
        //tb.setChecked(pref.getBoolean("toggleButton",true));
        audioOn = pref.getBoolean("toggleButton", true);

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


                mPlayer.setLooping(true);
                if(audioOn=true){



                    mPlayer.setVolume((float) mVolume / mVolumeMax,
                            (float) mVolume / mVolumeMax);


                    mPlayer.start();
                    ;

                }else {


                    mPlayer.setVolume((float) mVolume / mVolumeMax,
                            (float) mVolume / mVolumeMax);
                    mPlayer.pause();

                }*/
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
}
