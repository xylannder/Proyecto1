package rellenaTablero;

public class Diagonal2 {
	public static char [][] tableroJuego;
	String Palabra;
	private static int tamañoTablero;
	private static char letraRelleno;
	public static boolean palabraColocada;
//	static boolean primeraPalabra = false; // indica si ya se introdujo la primera palabra en el tablero
	
	public Diagonal2 (String palabra, char [][] tblJuego, int tam, char lr){
		Palabra = palabra;
		tableroJuego = tblJuego;
		tamañoTablero = tam;
		letraRelleno = lr;
		/**
		 * El constructor sólo coloca la primera palabra del tablero, del resto se ocuta actualizaDatos
		 * */
		colocaPalabra();
	}
	public void actualizaDatos (String palabra, char [][] tblJuego){
		Palabra = palabra;
		tableroJuego = tblJuego;
		colocaPalabra();
	}
	private void colocaPalabra() {
		int espacioValido = tamañoTablero - Palabra.length() + 1;
	
		int ejeX = (int)(Math.random() * espacioValido);
		int ejeY = (int)(Math.random() * espacioValido + Palabra.length() - 1);
	
		int vectorEjeX [] = new int [Palabra.length()];
		int vectorEjeY [] = new int [Palabra.length()];
		char vectorLetras [] = new char[Palabra.length()];
		int letrasColocadas = 0;
		boolean correcto = true;
		
		for (int k = (Palabra.length() - 1), w = 0; k >= 0; k--, ejeX++, ejeY--, w++){
			if (tableroJuego [ejeY][ejeX] == letraRelleno || tableroJuego[ejeY][ejeX] == Palabra.charAt(k)){
				vectorEjeX[w] = ejeX;
				vectorEjeY[w] = ejeY;
				vectorLetras[w] = tableroJuego[ejeY][ejeX];
				tableroJuego[ejeY][ejeX] = Palabra.charAt(k);
				letrasColocadas++;
			}
			else {
				/**
				 * Encontró un caracter distinto de la letra de relleno, por lo que vamos a dejar el tablero de juego tal y como 
				 * estaba
				 * */
				for (int i = 0; i < letrasColocadas; i++){
//					int aux = letrasColocadas - i;
					tableroJuego [vectorEjeY[i]][vectorEjeX[i]] = vectorLetras[i];
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
	public char [][] devuelveTableroJuego (){
		return tableroJuego;
	}

}
