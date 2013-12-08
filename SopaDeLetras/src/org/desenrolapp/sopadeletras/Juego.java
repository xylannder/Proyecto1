package org.desenrolapp.sopadeletras;

import java.util.Vector;

import tablero.CreaTablero;
import almacenPalabras.GestionBaseDatos;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class Juego extends Activity {

	static int tamañoTablero = 12;
	static char letraRelleno = (char) 47;
	static Vector<String> Palabras;
	static char[][] tableroJuego = null;
	static int palabrasColocadas = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Vector<String> Palabras = new Vector<String>();
		Palabras = devuelvePalabras("planeta"); // De momento el tema pinta
												// poco, recoge todas las
												// palabras.
		try {
			CreaTablero ct = new CreaTablero(Palabras);
			tableroJuego = ct.devuelveTablero();
		} finally {

		}
		vista v = new vista(this, tableroJuego, tamañoTablero);
		setContentView(v);
	}

	/**
	 * Genera un vector con las palabras que se van a agregar al tablero
	 * */
	public Vector<String> devuelvePalabras(String tema) {
		GestionBaseDatos gdb = new GestionBaseDatos(this, "Pruebas");

		Vector<String> palabras = new Vector<String>();

		Cursor c = gdb.getNotas();

		c.moveToFirst();
		for (int i = 0; i < c.getCount(); i++) {
			palabras.add(c.getString(c.getColumnIndex("palabra")));
			c.moveToNext();
		}

		gdb.cerrar();

		return palabras;
	}

	// VISTA DE JUEGO
	public class vista extends View {

		private Paint textoPaint = new Paint();

		private char[][] tableroJuego;
		private int N;

		public vista(Context context, char[][] tablero, int n) {
			super(context);
			N = n;
			tableroJuego = new char[N][N];
			tableroJuego = tablero;
			float dp = getResources().getDisplayMetrics().density;
			try {
				textoPaint.setTextSize(20 * dp);
				textoPaint.setColor(Color.RED);
			} finally {

			}
			textoPaint.setAntiAlias(true);
			textoPaint.setTextAlign(Paint.Align.CENTER);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);

			int x = 20;
			int separacionNumeros = getWidth(); // caso para 4 letras
			int y = separacionNumeros / N;
			for (int i = 0; i < N; i++) {
				y += separacionNumeros / N;
				for (int j = 0; j < N; j++) {
					String s = new StringBuilder().append("")
							.append(tableroJuego[i][j]).toString();
					canvas.drawText(s, x, y, textoPaint);
					x += separacionNumeros / N;
				}
				x = 20;
			}
		}

		@Override
		public boolean onTouchEvent(MotionEvent evento) {
			return false;
		}
	}
}
