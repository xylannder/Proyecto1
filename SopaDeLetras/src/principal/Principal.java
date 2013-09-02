package principal;

import java.util.Vector;

import otros.QuickSort;
import rellenaTablero.Diagonal1;
import rellenaTablero.Diagonal2;
import rellenaTablero.Horizontal;
import rellenaTablero.Vertical;
import ficheros.Ficheros;

public class Principal {

	/**
	 * @param args
	 */
	static int tamañoTablero = 12;
	static char letraRelleno = (char) 47;
	static Vector <String> Palabras;
	static char[][] tableroJuego;
	static int palabrasColocadas = 0;
	private static String Ruta ="src\\archivosTexto\\Planetas.txt";
    
	public static void main (String [ ] args) {

		Ficheros ficheros = new Ficheros (Ruta);
		Palabras = new Vector <String>();
		Palabras = ficheros.ContenidoActualFichero();

		/**
		 * Eliminamos las palabras que no caben en el tablero.
		 * */
		ajusteTamañoPalabras();
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
		 * Por defecto el tablero siempre va a aceptar palabras en Horizontal, mientras que Vertical, Diagonales y palabras del revés
		 * sólo si lo pide el jugador.
		 * */
		rellenaTablero();

		/**
		 * Las clases Horizontal, Diagonal1 y 2 y Vertical
		 * Colocan las palabras aleatoriamente en los espacios del tablero.
		 * Si si quiere introducir la palabra de revés... hay que mandársela del revés
		 * */
		Horizontal H = new Horizontal (Palabras.get(0), tableroJuego, tamañoTablero, letraRelleno);
		tableroJuego = H.devuelveTableroJuego();
		if (H.resultado()) palabrasColocadas++;
		
		Vertical V = new Vertical (Palabras.get(1), tableroJuego, tamañoTablero, letraRelleno);
		tableroJuego = V.devuelveTableroJuego();
		if (V.resultado()) palabrasColocadas++;
		
		Diagonal1 D1 = new Diagonal1 (Palabras.get(2), tableroJuego, tamañoTablero, letraRelleno);
		tableroJuego = D1.devuelveTableroJuego();
		if (D1.resultado()) palabrasColocadas++;
		
		Diagonal2 D2 = new Diagonal2 (Palabras.get(3), tableroJuego, tamañoTablero, letraRelleno);
		tableroJuego = D2.devuelveTableroJuego();
		if (D2.resultado()) palabrasColocadas++;

		try{
			do{
				for (int i = 4; i < Palabras.size(); i++){
					D1.actualizaDatos(Palabras.get(i), tableroJuego);
					if (D1.resultado()){
						palabrasColocadas++;
						tableroJuego = D1.devuelveTableroJuego();
					}else i--;
					if ((i+1) < Palabras.size()) {
						H.actualizaDatos(Palabras.get(i++), tableroJuego);
						if (H.resultado()){
							tableroJuego = H.devuelveTableroJuego();
							palabrasColocadas++;
						}
					}else i--;
					if ((i+1) < Palabras.size()) {
						V.actualizaDatos(Palabras.get(i++), tableroJuego);
						if (V.resultado()){
							tableroJuego = V.devuelveTableroJuego();
							palabrasColocadas++;
						}
					} else i--;
					if ((i+1) < Palabras.size()){
						D2.actualizaDatos(Palabras.get(i++), tableroJuego);
						if (D2.resultado()) {
							palabrasColocadas++;
							tableroJuego = D2.devuelveTableroJuego();
						}
					}else i--;
					
				}	
			}while (palabrasColocadas < 9);

		}catch (Exception e){
			System.out.println(e);
		}
		System.out.println("Se colocaron: " + palabrasColocadas);
//		/**/
		imprimeTableroJuego();
		System.out.println("------------");
		tableroJuego[0][0] =(char) (Math.random()*26 + 97);
		completaTablero();
		imprimeTableroJuego();

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
		for (int i = 0; i < tamañoTablero; i++){
			for (int j =0; j < tamañoTablero; j++ ){
				System.out.print(tableroJuego[i][j] + " ");
			}
			System.out.print("\n");
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
				System.out.println("Se elimina: " + Palabras.elementAt(i) );
				Palabras.remove(i);
			}
		}
	}
	private static void escribeVector(){
		for (int i = 0; i < Palabras.size(); i ++)
			System.out.println((i+1) + " " + Palabras.get(i) + "\tTamaño: " + Palabras.get(i).length());
	}
	public static String devuelveTablero (){
		
		String  tablero = "no fallo";
		for (int i =0 ; i <12; i++)
			for (int j=0; j<12; j++){
				tableroJuego.
			}
	
		return valueOfchar;
	}
}
