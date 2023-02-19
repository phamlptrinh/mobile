package com.example.basiccaltor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class History extends AppCompatActivity {

    TextView txHis;
    Button btClear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        txHis = (TextView) findViewById(R.id.txHis);
        btClear = (Button) findViewById(R.id.btClear);
        btClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                intent1.putExtra("clear","\n_________\n");
                startActivity(intent1);
            }
        });
        Intent intent = getIntent();
        String expression = intent.getStringExtra("expression");
        txHis.setText(txHis.getText()+"\n_________\n"+expression);
    }
}