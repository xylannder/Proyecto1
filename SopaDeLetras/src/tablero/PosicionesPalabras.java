package tablero;

public class PosicionesPalabras {
	//datos de la palabra
	private int id;
	private String palabra;
	private boolean elegida = false;
	private boolean mostradaPantalla = false;
	
	//posición en la pantalla
	private int xPixelInicial;
	private int yPixelInicial;
	private int xPixelFinal;
	private int yPixelFinal;
	
	//posición en la matriz
	private int xTableroFinal;
	private int yTableroFinal;
	private int xTableroInicial;
	private int yTableroInicial;
	
	public PosicionesPalabras (){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

	public boolean isElegida() {
		return elegida;
	}

	public void setElegida(boolean elegida) {
		this.elegida = elegida;
	}

	public int getxPixelInicial() {
		return xPixelInicial;
	}

	public void setxPixelInicial(int xPixelInicial) {
		this.xPixelInicial = xPixelInicial;
	}

	public int getyPixelInicial() {
		return yPixelInicial;
	}

	public void setyPixelInicial(int yPixelInicial) {
		this.yPixelInicial = yPixelInicial;
	}

	public int getxTableroFinal() {
		return xTableroFinal;
	}

	public void setxTableroFinal(int xTableroFinal) {
		this.xTableroFinal = xTableroFinal;
	}

	public int getyTableroFinal() {
		return yTableroFinal;
	}

	public void setyTableroFinal(int yTableroFinal) {
		this.yTableroFinal = yTableroFinal;
	}

	public int getxPixelFinal() {
		return xPixelFinal;
	}

	public void setxPixelFinal(int xPixelFinal) {
		this.xPixelFinal = xPixelFinal;
	}

	public int getyPixelFinal() {
		return yPixelFinal;
	}

	public void setyPixelFinal(int yPixelFinal) {
		this.yPixelFinal = yPixelFinal;
	}

	public int getxTableroInicial() {
		return xTableroInicial;
	}

	public void setxTableroInicial(int xTableroIicial) {
		this.xTableroInicial = xTableroIicial;
	}

	public int getyTableroInicial() {
		return yTableroInicial;
	}

	public void setyTableroInicial(int yTableroInicial) {
		this.yTableroInicial = yTableroInicial;
	}

	public boolean getMostradaPantalla() {
		return mostradaPantalla;
	}

	public void setMonstradaPantalla(boolean monstradaPantalla) {
		this.mostradaPantalla = monstradaPantalla;
	}
}
