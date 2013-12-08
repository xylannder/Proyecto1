package org.desenrolapp.sopadeletras;

import almacenPalabras.GestionBaseDatos;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View.OnClickListener;


public class LeerBaseDatos extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		GestionBaseDatos gdb = new GestionBaseDatos (this, "Pruebas");
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_leer_base_datos);
		

		Cursor c = gdb.getNotas();
		String [] tao= new String [c.getCount()];
		
		c.moveToFirst();
		for (int i =0 ; i < c.getCount(); i++){		
			tao[i] = c.getString(c.getColumnIndex("palabra"));
			c.moveToNext();
		}
		
		ArrayAdapter<String> adaptador = new ArrayAdapter <String>(this, android.R.layout.simple_list_item_1,tao);
		
		ListView l = (ListView)findViewById(R.id.lista);
		
		l.setAdapter(adaptador);
		
		View agregar = findViewById(R.id.botonAtras);
		agregar.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.leer_base_datos, menu);
		return true;
	}
	@Override
	public void onClick(View v) {
		if (v.getId() == findViewById(R.id.botonAtras).getId()){
			Intent i = new Intent(this, MainActivity.class);
			startActivity(i);
			this.finish();
		}
	}
}
