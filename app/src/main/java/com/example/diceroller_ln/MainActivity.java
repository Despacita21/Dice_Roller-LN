package com.example.diceroller_ln;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements SensorEventListener
{
    private ImageView imageViewDice;
    private Random rng = new Random();

    private TextView CritText;

    private Sensor sensor;
    private SensorManager manager;
    private boolean isSensorFound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViewDice = findViewById(R.id.image_view_dice);
        imageViewDice.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                playSound();
            }
        });

        CritText = findViewById( R.id.CritTextView );
        CritText.setVisibility( View.INVISIBLE );
        manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if(manager.getDefaultSensor(Sensor.TYPE_SIGNIFICANT_MOTION) != null)
        {
            sensor = manager.getDefaultSensor(Sensor.TYPE_SIGNIFICANT_MOTION);
            isSensorFound = true;
        }
    }

    private void playSound()
    {
        MediaPlayer roll_sound = MediaPlayer.create(this, R.raw.bubbles);
        roll_sound.start();
        rollDice();
    }

    private void rollDice()
    {
        CritText.setVisibility(View.INVISIBLE);
       int randomNumber = rng.nextInt(20 ) + 1;



       switch ( randomNumber )
       {
           case 1:
               imageViewDice.setImageResource( R.drawable.d1 );
               CritText.setVisibility(View.VISIBLE);
               CritText.setText(R.string.Bad_Roll);
               MediaPlayer roll_sound1 = MediaPlayer.create(this, R.raw.one);
               roll_sound1.start();
               break;
           case 2:
               imageViewDice.setImageResource( R.drawable.d2 );
               break;
           case 3:
               imageViewDice.setImageResource( R.drawable.d3 );
               break;
           case 4:
               imageViewDice.setImageResource( R.drawable.d4 );
               break;
           case 5:
               imageViewDice.setImageResource( R.drawable.d5 );
               break;
           case 6:
               imageViewDice.setImageResource( R.drawable.d6 );
               break;
           case 7:
               imageViewDice.setImageResource( R.drawable.d7 );
               break;
           case 8:
               imageViewDice.setImageResource( R.drawable.d8 );
               break;
           case 9:
               imageViewDice.setImageResource( R.drawable.d9 );
               break;
           case 10:
               imageViewDice.setImageResource( R.drawable.d10 );
               break;
           case 11:
               imageViewDice.setImageResource( R.drawable.d11 );
               break;
           case 12:
               imageViewDice.setImageResource( R.drawable.d12 );
               break;
           case 13:
               imageViewDice.setImageResource( R.drawable.d13 );
               break;
           case 14:
               imageViewDice.setImageResource( R.drawable.d14 );
               break;
           case 15:
               imageViewDice.setImageResource( R.drawable.d15 );
               break;
           case 16:
               imageViewDice.setImageResource( R.drawable.d16 );
               break;
           case 17:
               imageViewDice.setImageResource( R.drawable.d17 );
               break;
           case 18:
               imageViewDice.setImageResource( R.drawable.d18 );
               break;
           case 19:
               imageViewDice.setImageResource( R.drawable.d19 );
               break;
           case 20:
               imageViewDice.setImageResource( R.drawable.d20 );
               CritText.setVisibility(View.VISIBLE);
               CritText.setText(R.string.Good_Roll);
               MediaPlayer roll_sound2 = MediaPlayer.create(this, R.raw.twenty);
               roll_sound2.start();
               break;

       }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent)
    {
        MediaPlayer roll_sound4 = MediaPlayer.create(this, R.raw.lots_of_dice);
        roll_sound4.start();
        rollDice();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i)
    {

    }

    @Override
    protected void onResume() {
        super.onResume();

        if(isSensorFound)
        {
            manager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if(isSensorFound)
        {
            manager.unregisterListener(this);
        }
    }
}