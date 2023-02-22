package componentes;

public class Jugador {
	
	private int numPiezas = 16 ;  //numero de piezas iniciales
	private Pieza[] piezasJugador = new Pieza[16];
	
	
    public Jugador() {
    	piezasJugador[0] = new Peon(true);
    	piezasJugador[1] = new Peon(true);
    	piezasJugador[2] = new Peon(true);
    	piezasJugador[3] = new Peon(true);
    	piezasJugador[4] = new Peon(true);
    	piezasJugador[5] = new Peon(true);
    	piezasJugador[6] = new Peon(true);
    	piezasJugador[7] = new Peon(true);
    	piezasJugador[8] = new Torre(true);
    	piezasJugador[9] = new Caballo(true);
    	piezasJugador[10] = new Alfil(true);
    	piezasJugador[11] = new Reina(true);
    	piezasJugador[12] = new Rey(true);
    	piezasJugador[13] = new Alfil(true);
    	piezasJugador[14] = new Caballo(true);
    	piezasJugador[15] = new Torre(true);
    	
    	
    	
    }
	
	
	public Pieza[] getPiezasJugador() {
		return piezasJugador;
	}


	public void setNumPiezas(int numPiezas) {
		
	}
	
	public int getNumPiezas() {
		return numPiezas;
	}

}
