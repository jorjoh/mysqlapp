package com.example.jsonsqltest.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
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
	EditText duration, distance,area,target;
	Button insert,showData;
	//TextView result;
	RequestQueue requestQueue;
	String insertUrl ="https://jorgenjohansen.no/trainingData/insertValue.php";
	String showUrl ="https://jorgenjohansen.no/trainingData/showData.php";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		duration = (EditText)findViewById(R.id.editText);
		distance = (EditText)findViewById(R.id.editText2);
		area = (EditText)findViewById(R.id.editText3);
		target = (EditText)findViewById(R.id.editText4);
		insert = (Button)findViewById(R.id.insert);
		showData = (Button)findViewById(R.id.showDataDB);



		requestQueue = Volley.newRequestQueue(getApplicationContext());

		insert.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				StringRequest request = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {

					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {

					}
				}){
					@Override
					protected Map<String, String> getParams() throws AuthFailureError {
						Map<String, String> parameters = new HashMap<String, String>();
						parameters.put("duration",duration.getText().toString());
						parameters.put("distance",distance.getText().toString());
						parameters.put("area",area.getText().toString());
						parameters.put("target",target.getText().toString());
						return parameters;
					}
				};
				requestQueue.add(request);
				duration.setText("");
				distance.setText("");
				area.setText("");
				target.setText("");
				Toast.makeText(getBaseContext(),"Dataene er registrert i databasen",
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
		if(id == R.id.action_aboutme) {
			startActivity(new Intent(getApplicationContext(), About.class));
		}


		return super.onOptionsItemSelected(item);
	}
}
