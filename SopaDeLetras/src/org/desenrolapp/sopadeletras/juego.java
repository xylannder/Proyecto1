package org.desenrolapp.sopadeletras;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.widget.TextView;

public class juego extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.juego);
				
		TextView juego = (TextView)findViewById(R.id.tablero);
		
		Display display = getWindowManager().getDefaultDisplay(); 
		int width = display.getWidth();  // Ancho de la pantalla
		
		juego.setHeight(width);
		
		int x = 10;
		char [][] c = new char [x][x];
		for (int i =0; i < x; i++){
			for (int j=0; j < x; j++){
				c [i][j]= (char)(i+97);
			}
		}

		String aux = null;
		for (int i =0; i < x; i++){
			for (int j=0; j < x; j++){
				aux += " " + c [i][j];
			}
			aux += "\n";
		}
		
		juego.setText(aux);

	}

}
