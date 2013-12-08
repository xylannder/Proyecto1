package tablero;

public class PosicionesPalabras {
	private String palabra;
	private boolean elegida = false;
	private int xPixel[];
	private int yPixel[];

	private int xTablero[];
	private int yTablero[];
	
	PosicionesPalabras (){}

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

	public int[] getxPixel() {
		return xPixel;
	}

	public void setxPixel(int[] xPixel) {
		this.xPixel = xPixel;
	}

	public int[] getyPixel() {
		return yPixel;
	}

	public void setyPixel(int[] yPixel) {
		this.yPixel = yPixel;
	}

	public int[] getxTablero() {
		return xTablero;
	}

	public void setxTablero(int[] xTablero) {
		this.xTablero = xTablero;
	}

	public int[] getyTablero() {
		return yTablero;
	}

	public void setyTablero(int[] yTablero) {
		this.yTablero = yTablero;
	}
	
}
