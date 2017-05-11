package com.alpersepici.idealkilo;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private TextView tvBoy, tvDurum, tvIdeal, tvKilo;
    private SeekBar seekbar;
    private RadioGroup rgroup;
    private boolean erkekmi;
    private double boy = 0.0;
    private int kilo = 50;
    private RadioGroup.OnCheckedChangeListener radioGroupOlayIsleyicisi = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
            if (checkedId == R.id.bay) {
                erkekmi = true;
            } else {
                erkekmi = false;
            }
            guncelle();
        }
    };
    private SeekBar.OnSeekBarChangeListener seekBarOlayIsleyicisi = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            kilo = 30 + progress;
            guncelle();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
    private TextWatcher editTextOlayIsleyici = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            try {
                boy = Double.valueOf(s.toString()) / 100.0;
            } catch (NumberFormatException e) {
                boy = 0.0;
            }
            guncelle();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        tvBoy = (TextView) findViewById(R.id.textView2);
        tvDurum = (TextView) findViewById(R.id.textView8);
        tvIdeal = (TextView) findViewById(R.id.textView6);
        tvKilo = (TextView) findViewById(R.id.textView10);
        rgroup = (RadioGroup) findViewById(R.id.radioGroup);
        seekbar = (SeekBar) findViewById(R.id.seekBar);
        editText.addTextChangedListener(editTextOlayIsleyici);
        seekbar.setOnSeekBarChangeListener(seekBarOlayIsleyicisi);
        rgroup.setOnCheckedChangeListener(radioGroupOlayIsleyicisi);

        guncelle();
    }

    private void guncelle() {
        tvKilo.setText(String.valueOf(kilo) + " kg");
        tvBoy.setText(String.valueOf(boy) + " m");

        int idealKiloBay = (int) (50 + 2.3 * (boy * 100 * 0.4 - 60));
        int idealKiloBayan = (int) (45.5 + 2.3 * (boy * 100 * 0.4 - 60));
        double vki = kilo / (boy * boy);

        if (erkekmi) {
            tvIdeal.setText(String.valueOf(idealKiloBay));
            if (vki <= 20.7) {
                tvDurum.setBackgroundResource(R.color.colorPrimary);
                tvDurum.setText("Zayıfsınız.");
            } else if (vki <= 20.7) {
                tvDurum.setBackgroundResource(R.color.colorPrimary);
                tvDurum.setText("Zayıfsınız.");
            }
        } else {
            tvIdeal.setText(String.valueOf(idealKiloBayan));
        }


    }
}
