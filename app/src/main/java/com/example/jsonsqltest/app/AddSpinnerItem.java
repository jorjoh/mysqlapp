package com.example.jsonsqltest.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jørgen Johansen on 15.11.2015.
 */
public class AddSpinnerItem extends Activity {
    // Deklarerer varriabler (EditText, Button, RequestQueue og string) & Kenneth ser på "test"
        EditText duration;
        Button insert, back;
        Spinner spinner;
    List<String> spinnerArray =  new ArrayList<String>();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_spinner);
            // Tildeler variabler de forskjellige feltene og finner de i XML-filen
            duration = (EditText) findViewById(R.id.editText);
            insert = (Button) findViewById(R.id.insertSpinner);
            back = (Button) findViewById(R.id.mainScreen);

            spinner = (Spinner)findViewById(R.id.spinner);

            // Lager en liste som skal inneholde verdiene til spinneren(nedtreksmenyen)

            spinnerArray.add("Velg trening");
            spinnerArray.add("Intervalltrening");
            spinnerArray.add("Joggetur");
            spinnerArray.add("Gå-tur");
            spinnerArray.add("Styrketrening");
            spinnerArray.add("Generell løping");


            insert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    spinnerArray.add(insert.getText().toString());
                }
            });
            // Legger så denne listen inn i et array som sendes inn til spinneren som parameter
            ArrayAdapter<String> arrayWithtraningValues = new ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, spinnerArray);

            arrayWithtraningValues.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(arrayWithtraningValues);

            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            });

        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }
            // Hvis det finnes en action_aboutme
            if (id == R.id.action_aboutme) {
                // Fyrer om nytt "intent" med klasse About
                startActivity(new Intent(getApplicationContext(), About.class));
            }

            return super.onOptionsItemSelected(item);
        }
    }


