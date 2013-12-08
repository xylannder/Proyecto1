package tablero;

import java.util.Vector;

import otros.QuickSort;
import rellenaTablero.Diagonal1;
import rellenaTablero.Diagonal2;
import rellenaTablero.Horizontal;
import rellenaTablero.Vertical;

public class CreaTablero {

	static int tama�oTablero = 12;
	static char letraRelleno = (char) 47;
	static Vector<String> Palabras;
	static char[][] tableroJuego;
	static int palabrasColocadas = 0;

	public CreaTablero(Vector<String> palabras) {
		/**
		 * Eliminamos las palabras que no caben en el tablero.
		 * */

		/**
		 * Ordenamos las palabras de mayor a menor tama�o
		 * */
		Palabras = palabras;
		QuickSort QS = new QuickSort(Palabras);
		Palabras = QS.devuelveVectorOrdenado();
		// escribeVector();

		/**
		 * Generamos un Tablero de Juego
		 * */
		tableroJuego = new char[tama�oTablero][tama�oTablero];

		/**
		 * Por defecto el tablero siempre va a aceptar palabras en Horizontal,
		 * mientras que Vertical, Diagonales y palabras del rev�s s�lo si lo
		 * pide el jugador.
		 * 
		 * La primera palabra cumplir� las siguientes caracter�stica: 1.- Su
		 * colocaci�n ser� siempre en Horizontal. 2.- Ser� la primera palabra
		 * del Vector Palabras, es decir, la m�s larga
		 * */
		rellenaTablero();
		// imprimeTableroJuego();
		/**
		 * Las clases Horizontal, Diagonal1 y 2 y Vertical Colocan las palabras
		 * aleatoriamente en los espacios del tablero. Si si quiere introducir
		 * la palabra de rev�s... hay que mand�rsela del rev�s
		 * */
		try {
			int opciones = 4; // = 1 s�lo horizontal, 2 hor + vertical, 3 hor +
								// ver + diagonal1, 4 todos
			boolean invierte = true; // true inverte algunas palabras, false no
			for (int i = 0; i < Palabras.size(); i++) {
				String palabra = Palabras.get(i);
				int aleatorio = (int) (Math.random() * opciones);
				if (invierte) {
					if ((int) (Math.random() * 2) == 0) {// Invierte la palabra
						String aux = inviertePalabra(palabra);
						palabra = aux;
					}
				}
				boolean resultado = false;
				switch (aleatorio) {
				case 0:
					resultado = horizontal(palabra);
					break;
				case 1:
					resultado = vertical(palabra);
					break;
				case 2:
					resultado = diagonal1(palabra);
					break;
				case 3:
					resultado = diagonal2(palabra);
					break;
				}
				if (!resultado)
					i--;
				else
					palabrasColocadas++;
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		completaTablero();
	}

	public char[][] devuelveTablero() {
		return tableroJuego;
	}

	/**
	 * Funciones necesarias para generar el tablero
	 */
	private static String inviertePalabra(String palabra) {
		StringBuilder builder = new StringBuilder(palabra);
		String sCadenaInvertida = builder.reverse().toString();
		return sCadenaInvertida;
	}

	static boolean horizontal(String palabra) {
		Horizontal H = new Horizontal(palabra, tableroJuego, tama�oTablero,
				letraRelleno);
		tableroJuego = H.devuelveTableroJuego();
		if (H.resultado())
			return true;
		return false;

	}

	static boolean vertical(String palabra) {
		Vertical V = new Vertical(palabra, tableroJuego, tama�oTablero,
				letraRelleno);
		tableroJuego = V.devuelveTableroJuego();
		if (V.resultado())
			return true;
		return false;

	}

	static boolean diagonal1(String palabra) {
		Diagonal1 D1 = new Diagonal1(palabra, tableroJuego, tama�oTablero,
				letraRelleno);
		tableroJuego = D1.devuelveTableroJuego();
		if (D1.resultado())
			return true;
		return false;

	}

	static boolean diagonal2(String palabra) {
		Diagonal2 D2 = new Diagonal2(palabra, tableroJuego, tama�oTablero,
				letraRelleno);
		tableroJuego = D2.devuelveTableroJuego();
		if (D2.resultado())
			return true;
		return false;
	}

	static void completaTablero() {
		for (int i = 0; i < tama�oTablero; i++) {
			for (int j = 0; j < tama�oTablero; j++) {
				if (tableroJuego[i][j] == letraRelleno)
					tableroJuego[i][j] = (char) (Math.random() * 26 + 97);
			}
		}
	}

	static void rellenaTablero() {
		for (int i = 0; i < tama�oTablero; i++) {
			for (int j = 0; j < tama�oTablero; j++) {
				tableroJuego[i][j] = letraRelleno;
			}
		}
	}
	
	private static void imprimeTableroJuego() {
		for (int i = 0; i < tama�oTablero; i++) {
			for (int j = 0; j < tama�oTablero; j++) {
				System.out.print(tableroJuego[i][j] + " ");
			}
			System.out.print("\n");
		}
	}

	private static void ajusteTama�oPalabras() {
		for (int i = 0; i < Palabras.size(); i++) {
			String aux = Palabras.get(i);
			if (tama�oTablero < aux.length()) {
				Palabras.remove(i);
			}
		}
	}

	private static void escribeVector() {
		for (int i = 0; i < Palabras.size(); i++)
			System.out.println((i + 1) + " " + Palabras.get(i) + "\tTama�o: "
					+ Palabras.get(i).length());
	}
}