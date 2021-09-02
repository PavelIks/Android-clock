package com.example.threadtest;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView)findViewById(R.id.textView);
        Button btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Runnable runnable = new Runnable()
                {
                    @Override
                    public void run()
                    {
                        for (;;)
                        {
                            textView.post(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    Calendar c = Calendar.getInstance();
                                    String totalTime = String.valueOf(c.get(Calendar.HOUR)) + ":" + String.valueOf(c.get(Calendar.MINUTE)) + ":" + String.valueOf(c.get(Calendar.SECOND));
                                    textView.setText(totalTime);
                                }
                            });
                            try
                            {
                                Thread.sleep(1000);
                            }
                            catch (InterruptedException e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                };
                Thread thread = new Thread(runnable);
                thread.start();
            }
        });
    }
}