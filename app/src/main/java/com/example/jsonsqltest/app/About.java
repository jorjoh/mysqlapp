package com.example.jsonsqltest.app;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class About extends MainActivity {
	// Deklarerer varriabler (Button, String) som brukes senere i prosjektet
	Button showDataFromDB;
	String showUrl ="https://jorgenjohansen.no/trainingData/showData.php";
	TextView message;
	TextView resultTextView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		// Tildeler variabler de forskjellige feltene og finner de i XML-filen
		message = (TextView) findViewById(R.id.textView1);
		resultTextView = (TextView)findViewById(R.id.resultatVisAbout);
		showDataFromDB = (Button)findViewById(R.id.showDataAbout);

		// Setter melding i TextView når du kommer du kommer inn About.class
		message.setText("Denne applikasjonen er laget av Jørgen Johansen");

		// Setter på en lytter på "showDataFromDB" knappen og kjører kode under
		showDataFromDB.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Json objekt som inneholder en URL som POST'er en forespørsel til DB
				JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
						showUrl, new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						try {
							JSONArray products = response.getJSONArray("products");
							for (int i = 0; i < products.length(); i++) {
								JSONObject product = products.getJSONObject(i);

								String duration = product.getString("duration");
								String distance = product.getString("distance");
								String area = product.getString("area");
								String target = product.getString("target");
								String created_at = product.getString("created_at");

								System.out.println(duration + " " + distance + " " + area + " " + target + "\n");
								resultTextView.setMovementMethod(new ScrollingMovementMethod());
								resultTextView.append("Treningstid: " + duration + "\n" + "Distanse: " + distance + "\n" + "Område: " + area + " \n" + "Målet ditt: " + target + "\n"+ "Dato: " + created_at + "\n");
								resultTextView.append("===\n");

								//append(duration +" " + distance + " "+ area + " " + target + "\n");
							}
							resultTextView.append("===\n");
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {

					}
				});
				requestQueue.add(jsonObjectRequest);

			}
		});
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_about, menu);
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

		return super.onOptionsItemSelected(item);
	}
}
