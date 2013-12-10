package org.desenrolapp.sopadeletras;

import java.util.Vector;

import tablero.CreaTablero;
import tablero.PosicionesPalabras;
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
import android.widget.Toast;

public class Juego extends Activity {
	
	CreaTablero ct;
	static PosicionesPalabras [] pp;
	static PosicionesPalabras aux;

	static int tamañoTablero = 10;
	static char letraRelleno = (char) 47;
	static Vector<String> Palabras;
	static char[][] tableroJuego = null;
	static int palabrasColocadas = 0;
	int xInicial;
	int yInicial;
	int xFinal;
	int yFinal;
	int vectorPalabras[][];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Vector<String> Palabras = new Vector<String>();
		Palabras = devuelvePalabras("planeta"); // De momento el tema pinta
												// poco, recoge todas las
												// palabras.
		try {
			ct = new CreaTablero(Palabras, tamañoTablero);
			tableroJuego = ct.getTableroJuego();
			pp = new PosicionesPalabras [ct.getCantidadPalabras()];
			pp = ct.getPp();
		} finally {

		}
		vista v = new vista(this, tableroJuego, tamañoTablero);
		setContentView(v);
//		setContentView(R.layout.juego);
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
		StringBuilder b = new StringBuilder();

		private char[][] tableroJuego;
		private int N;

		
		public vista(Context context, char[][] tablero, int n) {
			super(context);
			
			
			
			N = n;
			tableroJuego = new char[N][N];
			tableroJuego = tablero;
			float dp = getResources().getDisplayMetrics().density;
			try {
				textoPaint.setTextSize(25 * dp);
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
					String s = new StringBuilder().append("").append(tableroJuego[i][j]).toString();
					
					for (int k = 0; k < pp.length; k++){
						aux = pp[k];
						if (aux.getxTableroInicial() == j && aux.getyTableroInicial() == i){
							aux.setxPixelInicial(x);
							aux.setyPixelInicial(y);
							pp[k] = aux;
						}
						if (aux.getxTableroFinal() == j && aux.getyTableroFinal() == i){
							aux.setxPixelFinal(x);
							aux.setyPixelFinal(y);
							pp[k] = aux;
						}
					}
					
					canvas.drawText(s, x, y, textoPaint);
					x += separacionNumeros / N;
				}
				x = 20;
			}
		}

		@Override
		public boolean onTouchEvent(MotionEvent evento) {
	
			b.setLength(0);

			switch (evento.getAction()){
			case MotionEvent.ACTION_DOWN: //Se ha pulsado la pantalla
				xInicial = (int) evento.getX();
				yInicial = (int) evento.getY();
//				Toast.makeText(getApplicationContext(), "xInicial: " + xInicial + " yInicial: " + yInicial, 2000)
//			.show();
				break;
			case MotionEvent.ACTION_MOVE: //Se está moviendo el dedo por la pantalla

				break;
			case MotionEvent.ACTION_CANCEL: //Se ha pulsado la pantalla
				break;
			case MotionEvent.ACTION_UP: //Se ha levantado el dedo
				 xFinal = (int) evento.getX();
				 yFinal = (int) evento.getY();
//					Toast.makeText(getApplicationContext(), "xFinal: " + xFinal + " yFinal: " + yFinal, 2000)
//					.show();
				 int variable = palabraSeleccionada();
				 if (variable != -1){
					 aux = pp[variable];
					 aux.setMonstradaPantalla(true);
					 Toast.makeText(getApplicationContext(), "Se selecciono: " + aux.getPalabra(), 1000)
					 .show();	
					 pp[variable] = aux;
					 if (todasSeleccionadas()){
						 Toast.makeText(getApplicationContext(), "Ganaste", 1000)
						 .show();	

					 }
				 }
				 break;
			}		
			return true;
		}

		private boolean todasSeleccionadas() {

			for (int k = 0; k < pp.length; k++){
				aux = pp[k];
				if (!aux.getMostradaPantalla()){
					return false;
				}
			}
			return true;
		}

		private int palabraSeleccionada() {

			int sensibilidad = 30;
			for (int k = 0; k < pp.length; k++){
				aux = pp[k];
				//Si se selecciona de adelante a atrás
				if (aux.getxPixelFinal() >= (xFinal- sensibilidad) && aux.getxPixelFinal() <= (xFinal + sensibilidad) ){
					if (aux.getyPixelFinal() >= (yFinal- sensibilidad) && aux.getyPixelFinal() <= (yFinal + sensibilidad) ){
						if (aux.getxPixelInicial() >= (xInicial- sensibilidad) && aux.getxPixelInicial() <= (xInicial + sensibilidad)){
							if (aux.getyPixelInicial() >= (yInicial- sensibilidad) && aux.getyPixelInicial() <= (yInicial + sensibilidad) ){
								aux.setElegida(true);
								pp[k] = aux;
								return k;
							}
						}					
					}
				}
				//si se selcciona "al revés"
				if (aux.getxPixelInicial() >= (xFinal- sensibilidad) && aux.getxPixelInicial() <= (xFinal + sensibilidad) ){
					if (aux.getyPixelInicial() >= (yFinal- sensibilidad) && aux.getyPixelInicial() <= (yFinal + sensibilidad) ){
						if (aux.getxPixelFinal() >= (xInicial- sensibilidad) && aux.getxPixelFinal() <= (xInicial + sensibilidad) ){
							if ( aux.getyPixelFinal()>= (yInicial- sensibilidad) && aux.getyPixelFinal() <= (yInicial + sensibilidad) ){
								aux.setElegida(true);
								aux.setElegida(true);
								pp[k] = aux;
								return k;
							}
						}					
					}
				}
			}
			return -1; //No se selecciononada	
		}
	}

}