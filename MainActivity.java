package com.source.administrator.stopwatch;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
        private int seconds=0;
        private boolean running;
        private boolean wasRunning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(savedInstanceState!=null)
        {
            seconds=savedInstanceState.getInt("seconds");
            running=savedInstanceState.getBoolean("running");
            wasRunning=savedInstanceState.getBoolean("wasRunning");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runTimer();
    }
    public void onClickStart(View v)
    {
        running=true;
    }
    public void onClickStop(View v)
    {
        running=false;
    }
    public void  onClickReset(View v)
    {
        running=false;
        seconds=0;
    }
    private  void runTimer(){
        final TextView tv= (TextView) findViewById(R.id.textView);
        final Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours=seconds/3600;
                int minutes=(seconds%3600)/60;
                int secs=seconds%60;
                String time=String.format("%d:%02d:%02d",hours,minutes,secs);
                tv.setText(time);
                if(running)
                    seconds++;
                handler.postDelayed(this,1000);
            }
        });
    }
    @Override
    public void onStop()
    {
        super.onStop();
        wasRunning=running;
        running=false;
    }
    @Override
    public void onResume()
    {
        super.onResume();
        if(wasRunning)
            running=true;
    }
    @Override
    public void onPause()
    {
        super.onPause();
        wasRunning=running;
        running=false;
    }
    @Override
    public void onStart()
    {
        super.onStart();
        if(wasRunning)
            running=true;
    }
    @Override
    public void onSaveInstanceState(Bundle b)
    {
        b.putBoolean("running",running);
        b.putInt("seconds",seconds);
        b.putBoolean("wasRunning",wasRunning);
    }

}
