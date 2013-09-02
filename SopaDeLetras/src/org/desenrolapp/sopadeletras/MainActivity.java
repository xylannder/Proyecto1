package org.desenrolapp.sopadeletras;

import principal.Principal;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;


public class MainActivity extends Activity {
	
	private TextView output;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		output = (TextView) findViewById(R.id.sopaLetrasTexto);
		
		Principal p = new Principal ();
		String Tablero = p.devuelveTablero();

		//output.setText(Tablero);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
