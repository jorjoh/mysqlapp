package com.example.jsonsqltest.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class MainActivity extends Activity {
	// Deklarerer varriabler (EditText, Button, RequestQueue og string)
	EditText duration, distance, area, typeOfTraining;
	Button insert, reset, dbInfo;
	//ProgressBar progressBar;
	ProgressDialog progress;
	Spinner spinner;
	// Kø hvor kode som skal kjøres plasseres
	RequestQueue requestQueue;
	// Url til script som kjøres i databasen
	String insertUrl = "https://jorgenjohansen.no/trainingData/insertValue.php";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Tildeler variabler de forskjellige feltene og finner de i XML-filen
		duration = (EditText) findViewById(R.id.editText);
		distance = (EditText) findViewById(R.id.editText2);
		area = (EditText) findViewById(R.id.editText3);
		//typeOfTraining = (EditText) findViewById(R.id.editText4);
		insert = (Button) findViewById(R.id.insert);
		reset = (Button) findViewById(R.id.reset);
		dbInfo = (Button) findViewById(R.id.dbInfo);
		spinner = (Spinner)findViewById(R.id.spinner);



		// Lager en liste som skal inneholde verdiene til spinneren(nedtreksmenyen)
		List<String> spinnerArray =  new ArrayList<String>();
		spinnerArray.add("Velg trening");
		spinnerArray.add("Intervalltrening");
		spinnerArray.add("Joggetur");
		spinnerArray.add("Gå-tur");
		spinnerArray.add("Styrketrening");
		spinnerArray.add("Generell løping");



		// Legger så denne listen inn i et array som sendes inn til spinneren som parameter
		ArrayAdapter<String> arrayWithtraningValues = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item, spinnerArray);

		arrayWithtraningValues.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(arrayWithtraningValues);

		requestQueue = Volley.newRequestQueue(getApplicationContext());
		// Setter på en lytter på "insert" knappen og kjører kode under
		insert.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

                    progress = ProgressDialog.show(MainActivity.this, "Registrerer i databasen",
                            "Lagrer informasjonen, venligst vent...", true);

                progress.show();

                // Lager en instans av Handler - klassen
                Handler handler = new Handler();
                // Forsinker dialogboksen med 3 sekunder
                handler.postDelayed(new Runnable() {
                    public void run() {
                        // Dreper dialog boksen etter x-antall sekunder
                        progress.dismiss();
                    }}, 2000);  // 2000 milliseconds = 2 sec


				// StringRequest en "innkapslet" forespørsel for å hente svar fra en URL streng
				StringRequest request = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						System.out.println(response);
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {

					}
				}) {
					@Override
					// Bruker HashMap til innseting av data fra EditText feltene og inn mot tabellene i databasen
					protected Map<String, String> getParams() throws AuthFailureError {
						// Definerer string parametere som skal sendes inn
						Map<String, String> parameters = new HashMap<String, String>();
						parameters.put("duration", duration.getText().toString());
						parameters.put("distance", distance.getText().toString());
						parameters.put("area", area.getText().toString());
						parameters.put("target", spinner.getSelectedItem().toString());
						//parameters.put("target", typeOfTraining.getText().toString());
						// Returnerer de
						return parameters;
					}
				};
				// Legger "request" variabelen til i køen
				requestQueue.add(request);

				// Fyrer opp en sucsess melding om at data er sendt til databasen
				Toast.makeText(getBaseContext(), "Dataene er registrert i databasen",
						Toast.LENGTH_SHORT).show();
        }

		});
        // Setter en lytter på knappen reset
		reset.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
                //Sletter informasjonen i tekstfeltet
				duration.setText("");
                //Sletter informasjonen i tekstfeltet
				distance.setText("");
                //Sletter informasjonen i tekstfeltet
				area.setText("");

				// Fyrer opp en sucsess melding om at alle felter er nullstill
				Toast.makeText(getBaseContext(), "Alle felter er nullstillt",
						Toast.LENGTH_SHORT).show();
			}
		});
        // Setter lytter på knappen "dbInfo"
		dbInfo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
                // Når knappen er trykket fyrer den opp et nytt intent med klassen About
				startActivity(new Intent(getApplicationContext(), About.class));
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
			// Fyrer om nytt "intent" med klassen About
			startActivity(new Intent(getApplicationContext(), About.class));
		}

		return super.onOptionsItemSelected(item);
	}
}
