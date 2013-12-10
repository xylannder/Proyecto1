package tablero;

import java.util.Vector;

import otros.QuickSort;
import rellenaTablero.Diagonal1;
import rellenaTablero.Diagonal2;
import rellenaTablero.Horizontal;
import rellenaTablero.Vertical;
import tablero.PosicionesPalabras;

public class CreaTablero {

	/**
	 * @param args
	 */
	static int tamañoTablero = 10;
	static char letraRelleno = (char) 47;
	static Vector <String> Palabras;
	static char[][] tableroJuego;
	static int palabrasColocadas = 0;
	static int intentos = 0;
	static int intentosMaximos = 100;
	static int cantidadPalabras;
	
	//Objeto Posiciones de las palabras
	static PosicionesPalabras [] pp;
	static PosicionesPalabras ppaux;
	static int contador;
	
	//Datos que guardará el objeto Posiciones de las palabras
	static int xInicial;
	static int yInicial;
	static int xFinal;
	static int yFinal;
	static String palabraGuardada;
    
	public CreaTablero ( Vector <String> palabras, int t) {
		Palabras = palabras;
		tamañoTablero = t;

		/**
		 * Eliminamos las palabras que no caben en el tablero.
		 * */
//		De momenot falla el ajuste. Continúo trabajando
		ajusteTamañoPalabras (); 
		cantidadPalabras = Palabras.size();

		/**
		 * Ordenamos las palabras de mayor a menor tamaño
		 * */
		QuickSort QS = new QuickSort (Palabras);
		Palabras = QS.devuelveVectorOrdenado();
		/**
		 * Generamos un Tablero de Juego
		 * */
		tableroJuego = new char [tamañoTablero][tamañoTablero];
		/**
		 * Generamos el objeto que contendra la información de todas las palabras del tablero
		 * */
		System.out.println(Palabras.size());
		pp = new PosicionesPalabras [Palabras.size()];
		ppaux = new PosicionesPalabras();
		contador = 0;
		/**
		 * Por defecto el tablero siempre va a aceptar palabras en Horizontal, mientras que Vertical, Diagonales y palabras del revés
		 * sólo si lo pide el jugador.
		 * 
		 * La primera palabra cumplirá las siguientes característica:
		 * 		1.- Su colocación será siempre en Horizontal.
		 * 		2.- Será la primera palabra del Vector Palabras, es decir, la más larga 
		 * */
		rellenaTablero();
		/**
		 * Las clases Horizontal, Diagonal1 y 2 y Vertical
		 * Colocan las palabras aleatoriamente en los espacios del tablero.
		 * Si si quiere introducir la palabra de revés... hay que mandársela del revés
		 * */
		try{
			int opciones = 4; // = 1 sólo horizontal, 2 hor + vertical, 3 hor + ver + diagonal1, 4 todos
			boolean invierte = true; //true inverte algunas palabras, false no
			for (int i = 0; i < Palabras.size(); i++){
				String palabra = Palabras.get(i);
				int aleatorio = (int)(Math.random()*opciones);
				if (invierte){
					if ((int)(Math.random()*2) == 0){//Invierte la palabra
						String aux = inviertePalabra(palabra);
						palabra = aux;
					}
				}
				boolean resultado = false;
				switch (aleatorio){
				case 0:
					resultado = horizontal(palabra);
					break;
				case 1:
					resultado = vertical (palabra);
					break;
				case 2:
					resultado = diagonal1 (palabra);
					break;
				case 3:
					resultado = diagonal2 (palabra);
					break;
				}
				if (!resultado){
					intentos++;
					if (intentos == intentosMaximos) break;
					i--;
				}
				else{

					palabraGuardada = Palabras.get(i);
					setPosicionesPalabras();
					intentos = 0;
					palabrasColocadas++;
					contador++;
				}
			}	
		}catch (Exception e){
			System.out.println(e);
		}
//		System.out.println("Se colocaron: " + palabrasColocadas);
//		imprimeTableroJuego();
	completaTablero();
//		System.out.println("-----------------------");
//	//	imprimeTableroJuego();
//		/**/
//		imprimeInformacionPosicionesPalabras();
	}
	
	
	public int getCantidadPalabras() {
		return cantidadPalabras;
	}


	public static void setCantidadPalabras(int cantidadPalabras) {
		CreaTablero.cantidadPalabras = cantidadPalabras;
	}


	public PosicionesPalabras[] getPp() {
		return pp;
	}

	public static void setPp(PosicionesPalabras[] pp) {
		CreaTablero.pp = pp;
	}

	public char[][] getTableroJuego() {
		return tableroJuego;
	}
	public static void setTableroJuego(char[][] tableroJuego) {
		CreaTablero.tableroJuego = tableroJuego;
	}
	private static void setPosicionesPalabras() {
		PosicionesPalabras aux = new PosicionesPalabras ();
		aux.setId(contador);
		aux.setPalabra(palabraGuardada);
		aux.setxTableroInicial(xInicial);
		aux.setyTableroInicial(yInicial);
		aux.setxTableroFinal(xFinal);
		aux.setyTableroFinal(yFinal);
		aux.setElegida(false);
		
		pp[contador] = aux;
	}
	private static void imprimeInformacionPosicionesPalabras() {
		try{
			for (int i= 0; i < contador; i++){
				System.out.println("id: " + pp[i].getId());
				System.out.println("\tPalabra: " + pp[i].getPalabra());
				System.out.println("\txTablero: " + pp[i].getxTableroInicial());
				System.out.println("\tyTablero: " + pp[i].getyTableroInicial());
				System.out.println("\txFinal: " + pp[i].getxTableroFinal());
				System.out.println("\tyFinal: " + pp[i].getyTableroFinal());
			}
		}catch (Exception e){
			System.out.println(e);
		}
	}
	private static String inviertePalabra(String palabra) {
		StringBuilder builder=new StringBuilder(palabra);
		String sCadenaInvertida=builder.reverse().toString();
		return sCadenaInvertida;
	}
	static boolean horizontal (String palabra){
		Horizontal H = new Horizontal (palabra, tableroJuego, tamañoTablero, letraRelleno);
		tableroJuego = H.devuelveTableroJuego();
		if (H.resultado()){
			xInicial = H.getxInicial();
			yInicial = H.getyInicial();
			xFinal 	 = H.getxFinal();
			yFinal 	 = H.getyFinal();

			return true;
		}
		return false;

	}
	static boolean vertical (String palabra){
		Vertical V = new Vertical (palabra, tableroJuego, tamañoTablero, letraRelleno);
		tableroJuego = V.devuelveTableroJuego();
		if (V.resultado()){
			xInicial = V.getxInicial();
			yInicial = V.getyInicial();
			xFinal   = V.getxFinal();
			yFinal 	 = V.getyFinal();
			return true;
		}
		return false;

	}
	static boolean diagonal1(String palabra){
		Diagonal1 D1 = new Diagonal1 (palabra, tableroJuego, tamañoTablero, letraRelleno);
		tableroJuego = D1.devuelveTableroJuego();
		if (D1.resultado()){
			xInicial = D1.getxInicial();
			yInicial = D1.getyInicial();
			xFinal 	 = D1.getxFinal();
			yFinal 	 = D1.getyFinal();
			return true;
		}
		return false;

	}
	static boolean diagonal2(String palabra){
		Diagonal2 D2 = new Diagonal2 (palabra, tableroJuego, tamañoTablero, letraRelleno);
		tableroJuego = D2.devuelveTableroJuego();
		if (D2.resultado()){
			xInicial = D2.getxInicial();
			yInicial = D2.getyInicial();
			xFinal 	 = D2.getxFinal();
			yFinal 	 = D2.getyFinal();

			return true;
		}
		return false;
	}
	static void completaTablero (){
		for (int i =0; i < tamañoTablero ; i++){
			for (int j=0; j < tamañoTablero; j++){
				if (tableroJuego [i][j] == letraRelleno)
					tableroJuego [i][j] = (char) (Math.random()*26 + 97); 
			}
		}
	}

	private static void imprimeTableroJuego() {
		System.out.print("  ");
		for (int i = 0; i < tamañoTablero; i++){
			System.out.print(i + " ");
		}
			
		for (int i = 0; i < tamañoTablero; i++){
			System.out.print("\n" + i + " ");
			for (int j =0; j < tamañoTablero; j++ ){
				System.out.print(tableroJuego[i][j] + " ");
			}
		}
	}
	static void rellenaTablero (){
		for (int i =0; i < tamañoTablero ; i++){
			for (int j=0; j < tamañoTablero; j++){
				tableroJuego [i][j] = letraRelleno;				
			}
		}	
	}
	private static void ajusteTamañoPalabras() {
		for (int i = 0; i < Palabras.size(); i++){
			String aux = Palabras.get(i);
			if (tamañoTablero < aux.length()){
				Palabras.remove(i);
				i--;
			}
		}
	}
	private static void escribeVector(){
		for (int i = 0; i < Palabras.size(); i ++)
			System.out.println((i+1) + " " + Palabras.get(i) + "\tTamaño: " + Palabras.get(i).length());
	}
}
