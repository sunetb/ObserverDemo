package dk.stbn.observerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements MyCustomObserver{

    TextView info;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Singleton.getInstance().observeOrSubscribe(this);
        next = findViewById(R.id.button);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, NextActivity.class);
                startActivity(i);
            }
        });

        info = findViewById(R.id.tv);
        info.setText("we wait");


    }

    @Override
    public void itHappened() {
        info.setText(""+Singleton.getInstance().getValue());
    }

    @Override
    protected void onDestroy() {
        Singleton.getInstance().unsubscribe(this);
        super.onDestroy();
    }

}