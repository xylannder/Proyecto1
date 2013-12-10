package org.desenrolapp.sopadeletras;

import almacenPalabras.GestionBaseDatos;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		View salir = findViewById(R.id.salir);
		salir.setOnClickListener(this);

		View jugar = findViewById(R.id.jugar);
		jugar.setOnClickListener(this);

		View baseDatos = findViewById(R.id.MainBaseDatos);
		baseDatos.setOnClickListener(this);

		View leeBaseDatos = findViewById(R.id.botonLeerBaseDatos);
		leeBaseDatos.setOnClickListener(this);

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
//		Toast.makeText(getApplicationContext(), "onDestroy!",
//				Toast.LENGTH_SHORT).show();

	}

	@Override
	protected void onResume() {
		super.onResume();
//		Toast.makeText(getApplicationContext(), "onResume", Toast.LENGTH_SHORT)
//				.show();
	}

	@Override
	protected void onPause() {
		super.onPause();
//		Toast.makeText(getApplicationContext(), "onPause", Toast.LENGTH_SHORT)
//				.show();

	}

	@Override
	public void onClick(View v) {
		if (v.getId() == findViewById(R.id.salir).getId()){
			finish();
		}		
		else if (v.getId() == findViewById(R.id.jugar).getId()){
			Intent i = new Intent(this, Juego.class);
			startActivity(i);
			this.finish();
		}	
		else if (v.getId() == findViewById(R.id.MainBaseDatos).getId()){
			GestionBaseDatos gdb = new GestionBaseDatos (this, "Pruebas");		//			gestorPalabras.AlmacenPalabrasSQLite apsql = new gestorPalabras.AlmacenPalabrasSQLite(this, "palabras");
			gdb.anadirNotas("planetas" ,"mercurio");
			gdb.anadirNotas("planetas" ,"venus");
			gdb.anadirNotas("planetas" ,"tierra");
			gdb.anadirNotas("planetas" ,"marte");
			gdb.anadirNotas("planetas" ,"jupiter");
			gdb.anadirNotas("planetas" ,"saturno");
			gdb.anadirNotas("planetas" ,"urano");
			gdb.anadirNotas("planetas" ,"neptuno");
			gdb.anadirNotas("planetas" ,"pluton");		
			gdb.cerrar();
//			Toast.makeText(getApplicationContext(),"Palabras agregadas correctamente." , Toast.LENGTH_SHORT).show();
		}
		else if (v.getId() == findViewById(R.id.botonLeerBaseDatos).getId()){
			Intent i = new Intent(this, LeerBaseDatos.class);
			startActivity(i);
			this.finish();
		}
	}
}