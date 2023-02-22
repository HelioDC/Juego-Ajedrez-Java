package componentes;

public class Reina extends Pieza {

	public Reina(boolean vida) {
		super(new int[] { 0, 0 }, vida);

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
			
			else if(movimientoX == movimientoY ) {
				
				for(int i=-1,j=1 ; i> -movimientoY ; i--,j++) {
						if (tablero.getPiezasTablero()[posicion[0]+i][posicion[1]+j] != null) return false ;
				}
				movimientoCorrecto = true ;
			}
			else if( -movimientoX == movimientoY ) {
				
				for(int i=-1, j=-1; i> -movimientoY ; i-- , j--) {
						if (tablero.getPiezasTablero()[posicion[0]+i][posicion[1]+j] != null) return false ;
					
				}
				movimientoCorrecto = true ;
			}
			else if( -movimientoX == -movimientoY ) {
				
				for(int i=1,j=-1; i <movimientoY ; i++,j--) {
						if (tablero.getPiezasTablero()[posicion[0]+i][posicion[1]+j] != null) return false ;
				}
				movimientoCorrecto = true ;
			}
			else if (  movimientoX == -movimientoY) {
			
				
				for(int i=1,j=1; i <movimientoY ; i++,j++) {
						if (tablero.getPiezasTablero()[posicion[0]+i][posicion[1]+j] != null) return false ;
				}
				movimientoCorrecto = true ;
				
			}
			

		}
		if (movimientoCorrecto == true) {
			tablero.actualizarTablero(posicion, nuevaPosicion);
			return true;
		} else
			return false;

	}

}
