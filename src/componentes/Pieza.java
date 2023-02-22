package componentes;

public abstract class Pieza {
	
	// Atributos
	
	protected int [] posicion = new int[2] ; //coordenadas x e y
	private boolean vida = true ;  // indica si la pieza está en juego o ha sido eliminada
	private int jugador ;
	
	// Metodos abstractos
	
	public abstract boolean mover(int[] nuevaPosicion, Tablero tablero); // si se realiza el movimiento devuelve true

	// Constructor
	
	public Pieza(int[] posicion, boolean vida) {
		super();
		this.posicion = posicion;
		this.vida = vida;
		
	}

	public int[] getPosicion() {
		return posicion;
	}

	public void setPosicion(int... posicion) {
		this.posicion = posicion;
	}

	public boolean isVida() {
		return vida;
	}

	public void setVida(boolean vida) {
		this.vida = vida;
	}
	
	public int getJugador() {
		return jugador;
	}
	
	public void setJugador( int jugador) {
		this.jugador = jugador;
		
	}
    
	
	

}
