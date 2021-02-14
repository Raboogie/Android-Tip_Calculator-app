// Author: Raynaldo Francis
// GitHub handle: Raboogie
// Video Link: https://youtu.be/9S2zAcbL72c
package com.example.tipcalculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.NumberFormat;
import android.os.Build;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText billAmount;
    private TextView tipPercent;
    private SeekBar seekBar;
    private TextView tvTipAmount;
    private TextView tvTotal;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        billAmount = findViewById(R.id.editText_BillAmount);
        tipPercent = findViewById(R.id.textViewTipPercent);
        seekBar = findViewById(R.id.seekBar_Percent);
        tvTipAmount = findViewById(R.id.textViewTipAmount);
        tvTotal = findViewById(R.id.textViewTotalAmount);

        // set change listener for seek bar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int startPoint;
            NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                tipPercent.setText("" + progress + "%");   // Also String.valueOf(The integer you want to change to a string) can be used inside of setText to convert int to string

                double tip = calcTipAmount(progress);
                tvTipAmount.setText(numberFormat.format(tip));

                double total = calcBillTotal(tip);
                tvTotal.setText(numberFormat.format(total));
            }

            // Track the start point of your mouse click or touch.
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //tipPercent.setText("Stop" + seekBar.getProgress() + "%");
            }
        });
    }

    public double calcTipAmount(int percent) {
        double tipAmount = 0;

        if (billAmount.length() == 0) {
            billAmount.requestFocus();
            billAmount.setError("Enter Amount");
        } else {
            double amount = Double.parseDouble(billAmount.getText().toString());
            tipAmount = (percent * amount) / 100;
        }
        return tipAmount;
    }

    public double calcBillTotal(double tipAmount) {
        double billTotal = 0;

        if (billAmount.length() == 0) {
            billAmount.requestFocus();
            billAmount.setError("Enter Amount");
        } else {
            double amount = Double.parseDouble(billAmount.getText().toString());
            billTotal = tipAmount + amount;
        }
        return billTotal;
    }
}