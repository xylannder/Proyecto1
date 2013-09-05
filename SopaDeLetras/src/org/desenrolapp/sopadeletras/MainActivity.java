package org.desenrolapp.sopadeletras;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button salir = (Button) findViewById(R.id.salir);
		salir.setOnClickListener(this);
		
		Button jugar = (Button) findViewById(R.id.jugar);
		jugar.setOnClickListener(this);
	}
	
	@Override
	protected void onDestroy (){
		super.onDestroy();
		Toast.makeText(getApplicationContext(),"onDestroy!" , Toast.LENGTH_SHORT).show();

	}
	
	@Override
	protected void onResume(){
		super.onResume();
		Toast.makeText(getApplicationContext(),"onResume" , Toast.LENGTH_SHORT).show();
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		Toast.makeText(getApplicationContext(),"onPause" , Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onClick(View v) {
		if (v.getId() == findViewById(R.id.salir).getId()){
			finish();
		}		
		else if (v.getId() == findViewById(R.id.jugar).getId()){
			Intent i = new Intent(this, juego.class);
			startActivity(i);
			this.finish();
		}	
	}
}