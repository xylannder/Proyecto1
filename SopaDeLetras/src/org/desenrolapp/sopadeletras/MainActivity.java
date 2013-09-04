package org.desenrolapp.sopadeletras;

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
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
/**
 * 

public class Main extends Activity implements OnTouchListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		TextView entrada = (TextView)findViewById(R.id.TextViewEntrada);
		entrada.setOnTouchListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onTouch(View vista, MotionEvent evento) {
		TextView salida = (TextView) findViewById(R.id.TextViewSalida);
	//	salida.append(evento.toString()+"\n" );
		String acciones[] = { "ACTION_DOWN", "ACTION_UP", "ACTION_MOVE", "ACTION_CANCEL","ACTION_OUTSIDE", "ACTION_POINTER_DOWN", "ACTION_POINTER_UP" };
		int accion = evento.getAction();
		int codigoAccion = accion & MotionEvent.ACTION_MASK;
		salida.append(acciones[codigoAccion]);
		for (int i = 0; i < evento.getPointerCount(); i++) {
		salida.append(" puntero:" + evento.getPointerId(i) + 
		" x:" + evento.getX(i) + " y:" + evento.getY(i));
		}
		salida.append("\n");
		return true;
	}

}
 * */
 */