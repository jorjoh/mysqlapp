package com.example.jsonsqltest.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.android.volley.*;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends Activity {
	// Deklarerer varriabler (EditText, Button, RequestQueue og string)
	EditText duration, distance, area, target;
	Button insert, reset;
	ProgressBar progressBar;
	ProgressDialog progress;
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
		target = (EditText) findViewById(R.id.editText4);
		insert = (Button) findViewById(R.id.insert);
		reset = (Button) findViewById(R.id.reset);
		reset = (Button) findViewById(R.id.reset);
		//progressBar = (ProgressBar) findViewById(R.id.progressBar);

		//

		requestQueue = Volley.newRequestQueue(getApplicationContext());
		// Setter på en lytter på "insert" knappen og kjører kode under
		insert.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				progress = ProgressDialog.show(MainActivity.this, "dialog title",
						"dialog message", true);
				/*ProgressDialog progress;

				progress = new ProgressDialog(this);
				progress.setTitle("Please Wait!!");
				progress.setMessage("Wait!!");
				progress.setCancelable(true);
				progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
				progress.show();*/
				//progressBar.setVisibility(View.VISIBLE);
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
						parameters.put("target", target.getText().toString());
						// Returnerer de
						try {
							Thread.sleep(3000);

						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						//
						return parameters;
					}
				};
				// Legger "request" variabelen til i køen
				requestQueue.add(request);
				progress.dismiss();

				// Fyrer opp en sucsess melding om at data er sendt til databasen
				Toast.makeText(getBaseContext(), "Dataene er registrert i databasen",
						Toast.LENGTH_SHORT).show();

			}
		});
		reset.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//progressBar.setVisibility(View.VISIBLE);
				duration.setText("");
				distance.setText("");
				area.setText("");          //Dette må flyttes :D & TEST MOT GitLab
				target.setText("");
				// Fyrer opp en sucsess melding om at data er sendt til databasen
				Toast.makeText(getBaseContext(), "Alle felter er nullstillt",
						Toast.LENGTH_SHORT).show();
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
