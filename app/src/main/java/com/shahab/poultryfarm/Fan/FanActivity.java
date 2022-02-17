package com.shahab.poultryfarm.Fan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.shahab.poultryfarm.R;

import static com.shahab.poultryfarm.R.string.error_age;
import static com.shahab.poultryfarm.R.string.error_empty_fields;
import static com.shahab.poultryfarm.R.string.error_fan_first;
import static com.shahab.poultryfarm.R.string.error_temperature;

public class FanActivity extends AppCompatActivity {

    EditText age;
    EditText amountOpen;
    EditText avgWeight;
    TextView fan;
    EditText outerTemp;
    EditText totalChicken;
    EditText ventLength;
    TextView ventWidth;
    Spinner dayNight;
    Button calculateFan;
    Button calculateVent;
    public static double CFM = -1;
    public static String MSG = "";


    double[][] Night ={{0.5,0.5,0.5,0.5,0.5,0.5,0.6,0.6,0.6,0.6,0.6,0.7,0.7,0.7,0.7,0.7,0.8,0.8,0.8,0.8,0.8,1,1,1,1,1,1.5,1.5,1.5,1.5,1.5},
            {0.6,0.6,0.6,0.6,0.6,0.6,0.7,0.7,0.7,0.7,0.7,0.7,0.7,0.7,0.7,0.7,0.9,0.9,0.9,0.9,0.9,1.25,1.25,1.25,1.25,1.25,1.5,1.5,1.5,1.5,1.5},
            {0.6,0.6,0.6,0.6,0.6,0.6,0.7,0.7,0.7,0.7,0.7,0.8,0.8,0.8,0.8,0.8,1,1,1,1,1,1.5,1.5,1.5,1.5,1.5,2.5,2.5,2.5,2.5,2.5},
            {0.7,0.7,0.7,0.7,0.7,0.7,0.8,0.8,0.8,0.8,0.8,1,1,1,1,1,1.25,1.25,1.25,1.25,1.25,2,2,2,2,2,3,3,3,3,3},
            {0.8,0.8,0.8,0.8,0.8,0.8,0.9,0.9,0.9,0.9,0.9,1.25,1.25,1.25,1.25,1.25,1.5,1.5,1.5,1.5,1.5,2,2,2,2,2,3,3,3,3,3}};

    double[][] Day = {{0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.6, 0.6, 0.6, 0.6, 0.6, 0.7 , 0.7 , 0.7 , 0.7 , 0.7 , 1   , 1   , 1   , 1   , 1   , 1.5, 1.5, 1.5, 1.5, 1.5, 2  , 2  , 2  , 2  , 2},
            {0.6, 0.6, 0.6, 0.6, 0.6, 0.6, 0.7, 0.7, 0.7, 0.7, 0.7, 0.7 , 0.7 , 0.7 , 0.7 , 0.7 , 1.25, 1.25, 1.25, 1.25, 1.25, 1.5, 1.5, 1.5, 1.5, 1.5, 2.5, 2.5, 2.5, 2.5, 2.5},
            {0.6, 0.6, 0.6, 0.6, 0.6, 0.6, 0.8, 0.8, 0.8, 0.8, 0.8, 1   , 1   , 1   , 1   , 1   , 1.5 , 1.5 , 1.5 , 1.5 , 1.5 , 2.5, 2.5, 2.5, 2.5, 2.5, 3  , 3  , 3  , 3  , 3},
            {0.7, 0.7, 0.7, 0.7, 0.7, 0.7, 0.8, 0.8, 0.8, 0.8, 0.8, 1   , 1   , 1   , 1   , 1   , 1.7 , 1.7 , 1.7 , 1.7 , 1.7 , 3  , 3  , 3  , 3  , 3  , 4  , 4  , 4  ,  4 , 4},
            {0.8, 0.8, 0.8, 0.8, 0.8, 0.8, 0.9, 0.9, 0.9, 0.9, 0.9, 1.25, 1.25, 1.25, 1.25, 1.25, 2   , 2   , 2   , 2   , 2   , 3  , 3  , 3  , 3  , 3  , 4  , 4  , 4  , 4  , 4}};


    public double getDayGraphValue(int age,int temp){
        return Day[age-1][temp];
    }
    public double getNightGraphValue(int age,int temp){
        return Night[age-1][temp];
    }

    public  void init(){

        age = findViewById(R.id.ftrd_AgeInWeeks);
        amountOpen = findViewById(R.id.ftrd_AmountOpen);
        avgWeight = findViewById(R.id.ftrd_AvgWeight);
        fan = findViewById(R.id.ftrd_fan);
        outerTemp = findViewById(R.id.ftrd_OuterTemp);
        totalChicken = findViewById(R.id.ftrd_totalChicken);
        ventLength = findViewById(R.id.ftrd_ventLength);
        ventWidth = findViewById(R.id.ftrd_ventWidth);
        dayNight = findViewById(R.id.spinner);
        calculateFan = findViewById(R.id.calculateFan);
        calculateVent= findViewById(R.id.calculateVent);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.spinner_array, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dayNight.setAdapter(adapter);

        outerTemp.setOnEditorActionListener((v, actionId, event) -> {
            if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)|| (actionId == EditorInfo.IME_ACTION_NEXT)) {
                calculateFan();
            }
            return false;
        });

        amountOpen.setOnEditorActionListener((v, actionId, event) -> {
            if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                calculateVent();
            }
            return false;
        });

    }

    public void clickFan(){
        calculateFan.setOnClickListener(v -> calculateFan());
    }
    public void clickVent(){
        calculateVent.setOnClickListener(v -> calculateVent());
    }
    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    public void calculateFan(){

        try {
            double weight = Double.parseDouble(String.valueOf(avgWeight.getText()));
            double ttlChicken = Double.parseDouble(String.valueOf(totalChicken.getText()));
            int ageWeek = Integer.parseInt(String.valueOf(age.getText()));
            int temp = Integer.parseInt(String.valueOf(outerTemp.getText()));
            double graphValue;

            if (ageWeek < 1 || ageWeek > 5) {
                Toast.makeText(this, error_age, Toast.LENGTH_SHORT).show();
                return;
            }
            if (temp < 0 || temp > 30) {
                Toast.makeText(this, error_temperature, Toast.LENGTH_SHORT).show();
                return;
            }
            if (dayNight.getSelectedItem().toString().equals("دن")) {
                graphValue = getDayGraphValue(ageWeek, temp);
                MSG = " دن کے لئے ";
            }
            else {
                graphValue = getNightGraphValue(ageWeek, temp);
                MSG = " رات کے لئے ";
            }
            CFM = weight * ttlChicken * graphValue / 1000.0;
            double fan = CFM * 60 / 20000.0;
            this.fan.setText(String.format("%.1f",fan) +" سیکنڈ "+ MSG);
        }catch (NumberFormatException e){
            Toast.makeText(this, error_empty_fields, Toast.LENGTH_SHORT).show();
        }
    }
    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    public void calculateVent(){
        try {
            double vLen = Double.parseDouble(String.valueOf(ventLength.getText()));
            double vOpen = Double.parseDouble(String.valueOf(amountOpen.getText()));
            if(CFM !=-1){

                double vent = CFM * 0.102 / (vLen*vOpen);
                this.ventWidth.setText(String.format("%.1f",vent) + " انچ"+MSG);
            }else{
                Toast.makeText(this, error_fan_first, Toast.LENGTH_SHORT).show();
            }

        }catch (NumberFormatException e){
            Toast.makeText(this, error_empty_fields, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
        clickFan();
        clickVent();
    }
}