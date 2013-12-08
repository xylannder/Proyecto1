package almacenPalabras;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

public class Ficheros {
	private static String NombreFichero;
	private static String Contenido;
	private static String DirectorioOrigen;
	private static String DirectorioDestino;
	private static boolean copia;
	
	Ficheros (String nf, String r, boolean t){
		/**
		 * Con copia = true 
		 * Constructor usado para pasar un directorio con datos 		 * para copiar en un directorio al que éstos deben ser 
		 * copiados
		 * 
		 * Con copia = false
		 * Contructor de la clase Fichero.
		 * Guarda los valores de Ruta y Contenido que deseamos guardar dentro del 
		 * fichero de texto.
		 * */
		copia = t;

		if (copia){
			DirectorioOrigen = nf;
			DirectorioDestino= r;
		}
		else{
			NombreFichero = nf;
			Contenido= r;
		}
	}
	public Ficheros (String nf){
		/**
		 * Contructor de la clase Fichero.
		 * Guarda el valor de Ruta en que deseamos guardar dentro del 
		 * fichero de texto.		
		 * */
		NombreFichero = nf;
	}
	public void Escribe() throws IOException {
		/**
		 * Método Escribe ()
		 * Guarda los datos contenidos en la variable de tipo String Contenido
		 * dentro del fichero cuyo nombre está contenido en la variable NombreFichero.		 
		 * */
		FileWriter fichero = new FileWriter (NombreFichero, true);
		PrintWriter pw = new PrintWriter (fichero);
			
		try {
			pw.println(Contenido);			
		}
		catch(Exception e){
			System.out.println("Error: " + e);
		}
		finally {
			/**
			 * Aprovechamos para cerrar el fichero
			 * */
			try {
				if (null != fichero) fichero.close();
			}
			catch (Exception e2){
				e2.printStackTrace();
			}
			
		}
	}
	public void ModificaContenido (String c){
		/**
		 * ModificaContenido
		 * Método que nos proporciona la posibilidad de modificar el String 
		 * Contenido de esta clase
		 * */
		Contenido = c;
	}
	public void ModificaNombreFichero(String Mnf){
		/**
		 * ModificaNombreFichero()
		 * Método que nos proporciona la posibilidad de modificar el String 
		 * NombreFichero de esta clase
		 * */
		NombreFichero = Mnf;
	}
	public Vector<String> ContenidoActualFichero() {
		/**
		 * ContenidoActualFichero
		 * Método que nos proporciona la posibilidad de recuperar el contenido de un
		 * fichero a la variable local de tipo String copia.
		 * */
		int tam = 50;
		String linea = null;
		Vector<String> copia = new Vector<String>();

		if (PermisoLectura ()){
			try {
				FileReader fr = new FileReader (NombreFichero);//fichero);
				BufferedReader br = new BufferedReader (fr);			
/**				Apertura y creación de BufferedReader
 * 				BufferedReader nos permitirá hacer una lectura cómoda, ya que nos 
 * 				proporciona el método readLine() */	
				
				for (int i = 0;(linea = br.readLine()) != null; i++){
					if (i == tam) break;
					copia.add(linea);
					
					//br.readLine();
				}
				br.close();
			}
			catch (Exception e){
				System.out.println("Error: " + e);
			}
		}
		return copia;
	}
	public void AñadeDatosAlFinal(String datos) throws IOException {
		/**
		 * Método AñadeDatosAlFinal
		 * Concatena los datos del archivo con nuevos datos que 
		 * queramos agregar y los guarda de nuevo en el fichero.
		 * */
		FileWriter fichero = new FileWriter (NombreFichero, true);
		PrintWriter pw = new PrintWriter (fichero);
			
		try {
			pw.println(datos);			
		}
		catch(Exception e){
			System.out.println("Error: " + e);
		}
		finally {
			/**
			 * Aprovechamos para cerrar el fichero
			 * */
			try {
				if (null != fichero) fichero.close();
			}
			catch (Exception e2){
				e2.printStackTrace();
			}
			
		}
}
	public void EliminaFichero(){
		/**
		 * EliminaFichero
		 * Método que, como su propio nombre indica, nos sirve para eliminar  ficheros.
		 *  */
		try{
			File fichero = new File (NombreFichero);
			fichero.delete();
		}
		catch (Exception e){
			System.out.println("Error: " + e);
		}
	}
	public boolean PermisoLectura() {
		/**
		 * Evaluamos si el fichero tiene permisos de Lectura
		 * */
		File f = new File(NombreFichero);
		if (f.canRead())
			return true;
		return false;
	}
	public boolean PermisoEscritura () {	
	/**
	 * Evaluamos si el fichero tiene permisos de Escritura
	 * */
		File f = new File(NombreFichero);
		if (f.canWrite())
			return true;
		return false;
	}
	public boolean PermisoEjecucion () {
		/**
		 * Evaluamos si el fichero tiene permisos de Ejecucion
		 * */
		File f = new File(NombreFichero);
		if (f.canExecute())
			return true;
		return false;
	}
	public boolean Existe (){
		File f = new File (NombreFichero);
		if (f.exists())
			return true;
		return false;
	}
	public boolean CopiaPega(Vector<String> ListaArchivos){
		/**
		 * Copia los fichero pasados en la variable ListaArchivos
		 * en el directorio que le indiquemos en el DirectorioDestino
		 * */
		for (int i = 0; i< ListaArchivos.size(); i++){
			try {
				File Fentrada = new File(DirectorioOrigen + "\\" + ListaArchivos.get(i));
				File Fsalida = new File(DirectorioDestino + "\\" + ListaArchivos.get(i));

				FileInputStream entrada = new FileInputStream(Fentrada);
				FileOutputStream salida = new FileOutputStream(Fsalida);

				int c;
				while( (c = entrada.read() ) != -1)
					salida.write(c);

				entrada.close();
				salida.close();
			} catch(IOException e) {
				return false;
			}
		}
		return true;
		}
	public int QueSoy (String ruta){
		/**
		 * Comprueba y devuelve de qué tipo de archivo o carpeta se trata la ruta enviada por 
		 * la declaracion
		 * Opciones:
		 * 		1.- Es un directorio
		 * 		2.- Es un fichero
		 * 		3.- Es un fichero oculto
		 * 		4.- Es una ruta absoluta
		 * 		0.- Error
		 * 
		 * Nota: Ruta Absoluta -> Una ruta absoluta es aquella que parte del directorio raíz 
		 * (es decir, parte de la carpeta que contiene a cualquier otra, y que normalmente se
		 * designa simplemente por el carácter /). Ejemplo: /usr/local/
		 * */
		File f = new File (ruta);
		if (f.isDirectory())	return 1;
		else if (f.isFile())	return 2;
		else if (f.isHidden())	return 3;
		else if (f.isAbsolute())return 4;
		return 0;
	}
}