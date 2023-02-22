package componentes;

public class Rey extends Pieza {

	private boolean primerTurno = true;
	
	public Rey(boolean vida) {
		super(new int[] { 0, 0 }, vida);

	}
	
	public boolean isPrimerTurno() {
		return primerTurno;
	}



	public void setPrimerTurno(boolean primerTurno) {
		this.primerTurno = primerTurno;
	}

	@Override
	public boolean mover(int[] nuevaPosicion, Tablero tablero) {
		int movimientoY = -nuevaPosicion[1] + posicion[0];
		int movimientoX = nuevaPosicion[0] - posicion[1];
		boolean movimientoCorrecto = false;
		// Comprobar si la nueva casilla esta vacia
		if (tablero.getPiezasTablero()[nuevaPosicion[1]][nuevaPosicion[0]] == null
				|| tablero.getPiezasTablero()[nuevaPosicion[1]][nuevaPosicion[0]].getJugador() != tablero.getPiezasTablero()[posicion[0]][posicion[1]].getJugador()) {
			// Comprobar si el movimiento es correcto
			if (movimientoX ==1 && movimientoY == 0 || movimientoX ==1 && movimientoY == 1 || movimientoX ==0 && movimientoY == 1 || movimientoX ==-1 && movimientoY == 0|| movimientoX == -1 && movimientoY == 0
					|| movimientoX == -1 && movimientoY == 1	|| movimientoX == -1 && movimientoY == -1|| movimientoX == 0 && movimientoY == -1	|| movimientoX == 1 && movimientoY == -1) {

				
				movimientoCorrecto = true;
				
			} 
		}
		else if(enroque ( tablero,  posicion , nuevaPosicion)) {
			movimientoCorrecto = true;
		}
		if (movimientoCorrecto == true) {
			((Rey)tablero.getPiezasTablero()[posicion[0]][posicion[1]]).primerTurno = false;
			tablero.actualizarTablero(posicion, nuevaPosicion);
			return true;
		} else
			return false;

	}
public boolean enroque (Tablero tablero, int[] posicion , int[] nuevaPosicion) {
		
		if ( tablero.getPiezasTablero()[nuevaPosicion[1]][nuevaPosicion[0]] instanceof Torre && tablero.getPiezasTablero()[nuevaPosicion[1]][nuevaPosicion[0]].getJugador() == tablero.getPiezasTablero()[posicion[0]][posicion[1]].getJugador()
				&& ((Torre)tablero.getPiezasTablero()[nuevaPosicion[1]][nuevaPosicion[0]]).isPrimerTurno() && ((Rey)tablero.getPiezasTablero()[posicion[0]][posicion[1]]).isPrimerTurno()) {
			if (posicion[1] == 4 && nuevaPosicion[0] == 7) {
				for(int i = 5 ; i< 7 ; i ++) {
					if(tablero.getPiezasTablero()[posicion[0]][i] != null) return false;
				}
			}
			else if (posicion[1] == 4 && nuevaPosicion[0] == 0) {
				for(int i = 1 ; i< 4 ; i ++) {
					if(tablero.getPiezasTablero()[posicion[0]][i] != null) return false;
				}
			}
			
			return true ;
		}
		return false;
		
	}
	

}
