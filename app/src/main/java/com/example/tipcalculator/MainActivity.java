package com.example.tipcalculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.NumberFormat;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText billAmount;
    private TextView tipPercent;
    private SeekBar seekBar;
    private TextView tvTipAmount;

    public static final String TAG = "MainActivity";

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        billAmount = findViewById(R.id.editText_BillAmount);
        tipPercent = findViewById(R.id.textViewTipPercent);
        seekBar = findViewById(R.id.seekBar_Percent);
        tvTipAmount = findViewById(R.id.textViewTipAmount);

        // set change listener for seek bar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int i = 0;

            NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                i = progress;
                tipPercent.setText("" + i + "%");   // Also String.valueOf(The integer you want to change to a string) can be used inside of setText to convert int to string
                double j = getTipAmount(i);
                tvTipAmount.setText(numberFormat.format(j));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public double getTipAmount(int percent) {
        double amount = Integer.parseInt(billAmount.getText().toString());
        double tipAmount = (percent * amount) / 100;

        //Log.d(TAG, "getTipAmount: " + tipAmount);

        return tipAmount;


    }
}