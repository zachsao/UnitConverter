package com.example.zach.unitconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.Math;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class LengthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length);

        final EditText et1=findViewById(R.id.edittext1);
        final Spinner spinner1 = findViewById(R.id.spinner1);
        final Spinner spinner2 = findViewById(R.id.spinner2);
        Button convert_btn = findViewById(R.id.conv_len_btn);
        final TextView result_tv = findViewById(R.id.result_textView);

        RadioGroup rg = findViewById(R.id.rg);
        RadioButton rd1 = findViewById(R.id.rd1);

        defaultRadioButton(rg,rd1);

        // Create an ArrayAdapter using the string array and a default spinner layout
        final ArrayAdapter<CharSequence> adapter_meters = ArrayAdapter.createFromResource(this,
                R.array.array_meters, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter_meters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner1.setAdapter(adapter_meters);


        // Create an ArrayAdapter using the string array and a default spinner layout
        final ArrayAdapter<CharSequence> adapter_miles = ArrayAdapter.createFromResource(this,
                R.array.array_meters, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter_miles.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner2.setAdapter(adapter_miles);

        final double[] metric_equivalence = {Math.pow(10,-9),Math.pow(10,-6),Math.pow(10,-3),Math.pow(10,-2),Math.pow(10,-1),Math.pow(10,0)
                ,Math.pow(10,1),Math.pow(10,2),Math.pow(10,3)};

        convert_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(et1.getText().toString())){
                    double res = Double.parseDouble(et1.getText().toString())*metric_equivalence[(int) spinner1.getSelectedItemId()]/metric_equivalence[(int) spinner2.getSelectedItemId()];

                    NumberFormat formatter = new DecimalFormat("##0.#####E0");

                    res = Double.parseDouble(formatter.format(res));

                    String str = et1.getText().toString()+spinner1.getSelectedItem().toString()+" = "+
                            res +" "+ spinner2.getSelectedItem().toString();
                    result_tv.setText(str);
                }
            }
        });
    }

    public void defaultRadioButton(RadioGroup radioGroup, RadioButton radioButton){
        radioGroup.check(radioButton.getId());
    }
}
