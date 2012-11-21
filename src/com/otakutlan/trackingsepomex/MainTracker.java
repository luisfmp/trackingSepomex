package com.otakutlan.trackingsepomex;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainTracker extends Activity {

    public static final String EXTRA_TN = "com.otakutlan.trackingsepomex.TN";
    public static final String EXTRA_YEAR = "com.otakutlan.trackingsepomex.YEAR";

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tracker);
        
        Spinner sp_year = (Spinner) findViewById(R.id.tracking_year);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.years, android.R.layout.simple_spinner_item);
        sp_year.setAdapter(adapter);
    }
    
  //Metodo de bot—n enviar
    public void trackPackage(View view){
    	Intent intent = new Intent(this, ObtainTracking.class);
    	EditText editText = (EditText) findViewById(R.id.tracking_number);
    	Spinner sp_year = (Spinner) findViewById(R.id.tracking_year);
    	String year = sp_year.getSelectedItem().toString();
    	String tn = editText.getText().toString();
    	intent.putExtra(EXTRA_TN, tn);
    	intent.putExtra(EXTRA_YEAR, year);
    	if (tn.length() <= 0)
    		Toast.makeText(this, "Capture un numero de rastreo", Toast.LENGTH_SHORT).show();
    	else
    		startActivity(intent);
    }
}
