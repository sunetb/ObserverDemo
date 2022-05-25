package dk.stbn.observerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NextActivity extends AppCompatActivity implements MyCustomObserver{

    TextView info;
    Button generate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        Singleton.getInstance().observeOrSubscribe(this);
        generate = findViewById(R.id.mybutton);
        System.out.println("ID = " + generate.getId());
        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Singleton.getInstance().generateTestValues();
            }
        });

        info = findViewById(R.id.tvnext);
        info.setText("we wait (NextActivity)");


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