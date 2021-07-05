package com.example.randomword_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    static String[] array_rus;
    static String[] array_eng;
    static int rand = 0;
    static int calc = 0;
    static String who;
    static String lang = "eng";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.get_button);
        button.setOnClickListener(v -> {
            lang = "eng";
            GetRandom();
        });

        Button button_trans = findViewById(R.id.tranlate_button);
        button_trans.setOnClickListener(v -> Translate());

        Button button_next = findViewById(R.id.next_button);
        button_next.setOnClickListener(v -> {
            lang = "eng";
            GetNext();
        });

        Spinner spinner = findViewById(R.id.list);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                calc = 0;
                SetArray();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void GetNext() {
        who = "next";
        TextView textView = findViewById(R.id.label);
        if (calc < array_eng.length) {
            textView.setText(array_eng[calc]);
//            Button button = findViewById(R.id.next_button);
//            button.setText(String.valueOf(calc));
        } else calc = -1;
        calc++;
    }

    public void GetRandom() {
        String word;
        who = "random";
        TextView textView = findViewById(R.id.label);
        Random random = new Random();
        rand = random.nextInt(array_eng.length - 1);
        calc = rand + 1;
        word = array_eng[rand];
        textView.setText(word);

//        Button button = findViewById(R.id.get_button);
//        button.setText(String.valueOf(rand));
    }

    public void SetArray() {
        Spinner spinner = findViewById(R.id.list);
        switch (spinner.getSelectedItemPosition()) {
            case 0: {
                array_eng = getResources().getStringArray(R.array.part_1_eng);
                array_rus = getResources().getStringArray(R.array.part_1_rus);
                break;
            }
            case 1: {
                array_eng = getResources().getStringArray(R.array.part_2_eng);
                array_rus = getResources().getStringArray(R.array.part_2_rus);
                break;
            }
            case 2: {
                array_eng = getResources().getStringArray(R.array.part_3_eng);
                array_rus = getResources().getStringArray(R.array.part_3_rus);
                break;
            }
            case 3: {
                array_eng = getResources().getStringArray(R.array.part_4_eng);
                array_rus = getResources().getStringArray(R.array.part_4_rus);
                break;
            }
            case 4: {
                array_eng = getResources().getStringArray(R.array.part_5_eng);
                array_rus = getResources().getStringArray(R.array.part_5_rus);
                break;
            }
        }
    }

    public void Translate() {
        TextView textView = findViewById(R.id.label);
        if (who.equals("random")) {
            switch (lang) {
                case "eng": {
                    textView.setText(array_rus[rand + 1]);
                    lang = "rus";
                    break;
                }

                case "rus": {
                    textView.setText(array_eng[rand + 1]);
                    lang = "eng";
                    break;
                }
            }
        }

        if (who.equals("next")) {
            switch (lang) {
                case "eng": {
                    textView.setText(array_rus[calc - 1]);
                    lang = "rus";
                    break;
                }
                case "rus": {
                    textView.setText(array_eng[calc - 1]);
                    lang = "eng";
                    break;
                }
            }
        }
    }
}