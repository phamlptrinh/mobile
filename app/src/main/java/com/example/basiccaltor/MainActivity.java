package com.example.basiccaltor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txdata, txresult;
    MaterialButton btC, btOpenBracket, btCloseBracket, btEqual, btDel;
    MaterialButton bt9, bt7, bt8, btplus;
    MaterialButton bt6, bt5, bt4, btminus;
    MaterialButton bt1, bt2, bt3, btmulti;
    MaterialButton btneg, bt0, btdot, btdiv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txresult = (TextView) findViewById(R.id.txresult);
        txdata = (TextView) findViewById(R.id.txdata);

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
    }

    void assignId(MaterialButton bt, int id){
        bt = (MaterialButton) findViewById(id);
        bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton bt = (MaterialButton) view;
        String textofbt = bt.getText().toString();
        String input = txdata.getText().toString();

        if(textofbt.equals("C"))
        {
            txdata.setText("");
            txresult.setText("0");
            return;
        }
        if(textofbt.equals("=")){
            List pf = basicCalculator.postfix(input);
            Double result = basicCalculator.calPostfix(pf);
            txresult.setText(String.valueOf(result));
            return;
        }
        if(textofbt.equals("DEL")){
            input = input.substring(0,input.length()-1);
        }

        else {input = input.concat(textofbt);}
        txdata.setText(input);
        txresult.setText(input);
    }
}