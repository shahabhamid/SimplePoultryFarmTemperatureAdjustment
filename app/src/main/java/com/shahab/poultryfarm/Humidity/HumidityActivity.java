package com.shahab.poultryfarm.Humidity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.shahab.poultryfarm.R;

public class HumidityActivity extends AppCompatActivity {

    Button btnHumidity;
    EditText editDry;
    EditText editWet;
    TextView textHumidity;

    public  void init(){

        editDry = findViewById(R.id.fh_dry);
        editWet = findViewById(R.id.fh_wet);
        textHumidity = findViewById(R.id.fh_humidity);
        btnHumidity = findViewById(R.id.calculateHumidity);

        editWet.setOnEditorActionListener((v, actionId, event) -> {
            if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                calculateHumidity();
            }
            return false;
        });

    }
    public void clickHumidity(){
        btnHumidity.setOnClickListener(v -> calculateHumidity());
    }

    @SuppressLint("SetTextI18n")
    private void calculateHumidity() {
        try {
            int dry = Integer.parseInt(String.valueOf(editDry.getText()));
            double wet = Integer.parseInt(String.valueOf(editWet.getText()));
            int y = (int) Math.abs(dry - wet);
            textHumidity.setText(table[dry + 1][y - 1]+"");
        }catch (Exception ex){
            Toast.makeText(this,getString(R.string.value_doesnt_exist)+ex.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_humidity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
        clickHumidity();
    }
    int[][] table
            = {{79, 59, 39, 20, 1},
            {81, 61, 44, 25, 7},
            {81, 64, 47, 29, 13},
            {82, 64, 79, 33, 17, 1},
            {83, 65, 49, 36, 21, 7},
            {83, 67, 51, 35, 25, 11},
            {84, 68, 53, 38, 24, 15, 2},
            {85, 70, 55, 41, 27, 14, 6},
            {85, 71, 57, 44, 30, 17, 5},
            {86, 72, 59, 46, 33, 21, 9},
            {86, 73, 60, 48, 36, 24, 12, 1},
            {87, 74, 62, 50, 38, 27, 16, 5},
            {87, 75, 63, 52, 41, 30, 19, 9},
            {88, 76, 65, 54, 43, 32, 22, 12, 2},
            {88, 77, 66, 55, 45, 35, 25, 15, 6},
            {89, 78, 67, 57, 47, 37, 27, 18, 9, 1},
            {89, 78, 68, 58, 48, 39, 30, 21, 12, 4},
            {89, 79, 69, 59, 50, 41, 32, 24, 15, 7},
            {90, 80, 70, 61, 52, 43, 34, 26, 18, 10, 3},
            {90, 80, 71, 62, 53, 45, 36, 28, 21, 13, 6},
            {90, 81, 72, 63, 54, 46, 38, 30, 23, 16, 9, 2},
            {91, 81, 73, 64, 56, 48, 40, 32, 25, 18, 11, 5},
            {91, 82, 73, 65, 57, 49, 42, 34, 27, 20, 14, 7, 1},
            {91, 82, 74, 66, 58, 50, 43, 36, 29, 23, 16, 10, 4},
            {91, 83, 75, 67, 59, 52, 45, 38, 31, 25, 18, 12, 6, 1},
            {91, 83, 75, 68, 60, 53, 46, 39, 33, 27, 20, 15, 9, 3},
            {92, 84, 76, 68, 61, 54, 47, 41, 35, 28, 22, 17, 11, 6, 1},
            {92, 84, 76, 69, 62, 55, 49, 42, 36, 30, 24, 19, 13, 8, 3},
            {92, 84, 77, 70, 63, 56, 50, 44, 38, 32, 26, 21, 15, 10, 5, 1},
            {92, 85, 77, 70, 64, 57, 51, 45, 39, 33, 28, 23, 17, 12, 8, 3},
            {92, 85, 78, 71, 64, 58, 52, 46, 40, 35, 29, 24, 19, 14, 10, 5, 1},
            {93, 86, 78, 72, 65, 59, 53, 47, 42, 36, 31, 26, 21, 16, 12, 7, 3},
            {93, 86, 80, 73, 67, 61, 56, 51, 45, 40, 36, 31, 27, 22, 18, 14, 11, 7},
            {93, 86, 80, 74, 68, 62, 57, 51, 46, 41, 37, 32, 28, 24, 20, 16, 12, 9, 5},
            {93, 87, 80, 74, 68, 63, 57, 52, 47, 42, 38, 33, 29, 25, 21, 17, 14, 10, 7},
            {93, 87, 81, 75, 69, 63, 58, 53, 48, 43, 39, 35, 30, 26, 23, 19, 15, 12, 8, 5},
            {94, 87, 81, 75, 69, 64, 59, 54, 49, 44, 40, 36, 32, 28, 24, 20, 17, 13, 10, 7},
            {94, 87, 81, 75, 70, 64, 59, 54, 50, 45, 41, 37, 33, 29, 25, 21, 18, 15, 11, 8},
            {94, 87, 82, 76, 70, 65, 60, 55, 51, 46, 42, 38, 34, 30, 26, 23, 19, 16, 13, 10},
            {94, 88, 82, 76, 71, 66, 61, 56, 51, 47, 43, 39, 35, 31, 27, 24, 20, 17, 14, 11},
            {94, 88, 82, 77, 71, 66, 61, 57, 52, 48, 43, 39, 36, 32, 28, 25, 22, 18, 15, 12},
            {94, 88, 82, 77, 72, 67, 62, 57, 53, 48, 44, 40, 36, 33, 29, 26, 23, 20, 16, 14}};

}