package com.example.basiccaltor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MaterialButton bthis;
    TextView txdata, txresult;
    MaterialButton btC, btOpenBracket, btCloseBracket, btEqual, btDel;
    MaterialButton bt9, bt7, bt8, btplus;
    MaterialButton bt6, bt5, bt4, btminus;
    MaterialButton bt1, bt2, bt3, btmulti;
    MaterialButton btneg, bt0, btdot, btdiv;
    String history = "";
	String value="";
        String textofbt;
        String input ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txresult = (TextView) findViewById(R.id.txresult);
        txdata = (TextView) findViewById(R.id.txdata);

        Intent intent1 = getIntent();
        history = intent1.getStringExtra("clear");

	    if (savedInstanceState != null) {
           input = savedInstanceState.getString("input");
           value = savedInstanceState.getString("value");
           history = savedInstanceState.getString("history");}
        if (value != null)       {txresult.setText(value);}
        if(input!=""){txdata.setText(input);}
	    else txdata.setText("0");


        assignId(btC, R.id.btC);
        assignId(btOpenBracket, R.id.btOpenBracket);
        assignId(btCloseBracket, R.id.btCloseBracket);
        assignId(btEqual, R.id.btEqual);
        assignId(bt7, R.id.bt7);
        assignId(bt8, R.id.bt8);
        assignId(bt9, R.id.bt9);
        assignId(bt4, R.id.bt4);
        assignId(bt5, R.id.bt5);
        assignId(bt6, R.id.bt6);
        assignId(bt1, R.id.bt1);
        assignId(bt2, R.id.bt2);
        assignId(bt3, R.id.bt3);
        assignId(btplus, R.id.btplus);
        assignId(btminus, R.id.btminus);
        assignId(btmulti, R.id.btmulti);
        assignId(btdiv, R.id.btdiv);
        assignId(btneg, R.id.btneg);
        assignId(btdot, R.id.btdot);
        assignId(bt0, R.id.bt0);
        assignId(btDel, R.id.btDel);
        assignId(bthis, R.id.bthis);
    }

    void assignId(MaterialButton bt, int id){
        bt = (MaterialButton) findViewById(id);
        bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Button bt = (Button) view;
        value="";
        textofbt = bt.getText().toString();
        input = txdata.getText().toString();

        if(input=="0")
        {
            input="";
        }

        switch(bt.getId()){
            case R.id.btC:{input="0";
                        value = "";
                        break;}

            case R.id.btEqual: List pf = basicCalculator.postfix(input);
                Double result = basicCalculator.calPostfix(pf);
                value = String.valueOf(result);
                String memory = input.concat("\n = ");
                memory = memory.concat(value);
                memory = memory.concat("\n _________\n");
                if(history==null){
                history = memory;}
                else history = history.concat(memory);
                break;

            case R.id.btDel:if(input.length()>1){
                input = input.substring(0,input.length()-1);}
                break;

            case R.id.bthis:Intent intent = new Intent(getApplicationContext(), History.class);
                intent.putExtra("expression", history);
                startActivity(intent); return;
            default: {input = input.concat(textofbt);}
        }
        txdata.setText(input);
        txresult.setText(value);
    }

	@Override
public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState); 
    // Add information for saving HelloToast counter
    // to the to the outState bundle
    outState.putString("input", txdata.getText().toString());
        outState.putString("value", txresult.getText().toString());
        outState.putString("history", history);
}

	@Override
public void onRestoreInstanceState (Bundle mySavedState) {
   super.onRestoreInstanceState(mySavedState);

   if (mySavedState != null) {
       input = mySavedState.getString("input");
       value = mySavedState.getString("value");
       history = mySavedState.getString("history");
       if (input != null)
           txdata.setText(input);
       if (value != null)
           txresult.setText(value);
   }
}


}

