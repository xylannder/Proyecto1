package rellenaTablero;

public class Vertical {
	public static char [][] tableroJuego;
	String Palabra;
	private static int tamañoTablero;
	private static char letraRelleno;
	private static boolean palabraColocada;
	
	public Vertical (String palabra, char [][] tblJuego, int tam, char lr){
		Palabra = palabra;
		tableroJuego = tblJuego;
		tamañoTablero = tam;
		letraRelleno = lr;
		/**
		 * El constructor sólo coloca la primera palabra del tablero, del resto se ocuta actualizaDatos
		 * */
		colocaPalabra();
	}
	public char [][] devuelveTableroJuego (){
		return tableroJuego;
	}
	public void actualizaDatos (String palabra, char [][] tblJuego){
		Palabra = palabra;
		tableroJuego = tblJuego;
		colocaPalabra();
	}
	private void colocaPalabra() {
		int ejeYinicial = tamañoTablero - Palabra.length() + 1;
		int ejeX = (int)(Math.random()*tamañoTablero);
		int ejeY = (int)(Math.random()*ejeYinicial);
		int vectorEjeX [] = new int [tamañoTablero];
		int vectorEjeY [] = new int [tamañoTablero];
		int letrasColocadas = 0;
		boolean correcto = true;
		
		for (int k = 0; k < Palabra.length(); k++, ejeY++){
			if (tableroJuego [ejeY][ejeX] == letraRelleno){
				vectorEjeX[k] = ejeX;
				vectorEjeY[k] = ejeY;
				tableroJuego[ejeY][ejeX] = Palabra.charAt(k);
				letrasColocadas++;
			}
			else {
				/**
				 * Encontró un caracter distinto de la letra de relleno, por lo que vamos a dejar el tablero de juego tal y como 
				 * estaba
				 * */
				for (int i = 0; i < letrasColocadas; i++){
					tableroJuego [vectorEjeY[i]][vectorEjeX[i]] = letraRelleno;
				}
				correcto = false;
				break;
			}
		}
		if (correcto) palabraColocada = true;
		else palabraColocada = false;		
	}
	public boolean resultado (){
		return palabraColocada;
	}


}