package componentes;

public class Torre extends Pieza {

	private boolean primerTurno = true;
	public Torre(boolean vida) {
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
			if (movimientoX > 0 && movimientoY == 0) {

				for (int j = 1; j < movimientoX; j++) {
					if (tablero.getPiezasTablero()[posicion[0]][posicion[1] + j] != null)
						return false;
				}
				movimientoCorrecto = true;
			} else if (movimientoX == 0 && movimientoY > 0) {

				for (int i = -1; i > -movimientoY; i--) {
					if (tablero.getPiezasTablero()[posicion[0] + i][posicion[1]] != null)
						return false;

				}
				movimientoCorrecto = true;
			}
			if (movimientoX < 0 && movimientoY == 0) {

				for (int j = -1; j > movimientoX; j--) {
					if (tablero.getPiezasTablero()[posicion[0]][posicion[1] + j] != null)
						return false;
				}
				movimientoCorrecto = true;
			} else if (movimientoX == 0 && movimientoY < 0) {

				for (int i = 1; i < -movimientoY; i++) {
					if (tablero.getPiezasTablero()[posicion[0] + i][posicion[1]] != null)
						return false;

				}
				movimientoCorrecto = true;
			}
			

		}
		else if(enroque ( tablero,  posicion , nuevaPosicion)) {
			movimientoCorrecto = true;
		}
		if (movimientoCorrecto == true) {
			((Torre)tablero.getPiezasTablero()[posicion[0]][posicion[1]]).primerTurno = false;
			tablero.actualizarTablero(posicion, nuevaPosicion);
			return true;
		} else
			return false;

	}
	
	public boolean enroque (Tablero tablero, int[] posicion , int[] nuevaPosicion) {
		
		if ( tablero.getPiezasTablero()[nuevaPosicion[1]][nuevaPosicion[0]] instanceof Rey && tablero.getPiezasTablero()[nuevaPosicion[1]][nuevaPosicion[0]].getJugador() == tablero.getPiezasTablero()[posicion[0]][posicion[1]].getJugador()
				&& ((Rey)tablero.getPiezasTablero()[nuevaPosicion[1]][nuevaPosicion[0]]).isPrimerTurno() && ((Torre)tablero.getPiezasTablero()[posicion[0]][posicion[1]]).isPrimerTurno()) {
			if (posicion[1] == 0 && nuevaPosicion[0] == 4) {
				for(int i = 1 ; i< 4 ; i ++) {
					if(tablero.getPiezasTablero()[posicion[0]][i] != null) return false;
				}
			}
			else if (posicion[1] == 7 && nuevaPosicion[0] == 4) {
				for(int i = 5 ; i< 7 ; i ++) {
					if(tablero.getPiezasTablero()[posicion[0]][i] != null) return false;
				}
			}
			
			return true ;
		}
		return false;
		
	}

}
