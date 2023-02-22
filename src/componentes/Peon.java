package componentes;

public class Peon extends Pieza {

	private boolean primerTurno = true;
	public Peon( boolean vida) {
		super(new int[]{0,0}, vida);
		
	}
	
	

	public boolean isPrimerTurno() {
		return primerTurno;
	}



	public void setPrimerTurno(boolean primerTurno) {
		this.primerTurno = primerTurno;
	}



	@Override
	public boolean mover(int[] nuevaPosicion, Tablero tablero) {
		int movimientoY = -nuevaPosicion[1] + posicion[0] ;
		int movimientoX = -nuevaPosicion[0] + posicion[1] ;
		boolean movimientoCorrecto = false ;
		//Comprobar si la nueva casilla esta vacia
		if(tablero.getPiezasTablero()[nuevaPosicion[1]][nuevaPosicion[0]] == null) { 
			//  Comprobar si el movimiento es correcto
			if(movimientoY == 1 && movimientoX == 0 && tablero.getPiezasTablero()[nuevaPosicion[1]][nuevaPosicion[0]] == null && tablero.getPiezasTablero()[posicion[0]][posicion[1]].getJugador() == 2) movimientoCorrecto = true ;
			else if(movimientoY == -1 && movimientoX == 0 && tablero.getPiezasTablero()[nuevaPosicion[1]][nuevaPosicion[0]] == null && tablero.getPiezasTablero()[posicion[0]][posicion[1]].getJugador() == 1) movimientoCorrecto = true ;
			else if(movimientoY == 2 && movimientoX == 0 && tablero.getPiezasTablero()[nuevaPosicion[1]][nuevaPosicion[0]] == null && tablero.getPiezasTablero()[posicion[0]][posicion[1]].getJugador() == 2
					&& ((Peon)tablero.getPiezasTablero()[posicion[0]][posicion[1]]).primerTurno == true) movimientoCorrecto = true ;
			else if(movimientoY == -2 && movimientoX == 0 && tablero.getPiezasTablero()[nuevaPosicion[1]][nuevaPosicion[0]] == null && tablero.getPiezasTablero()[posicion[0]][posicion[1]].getJugador() == 1
					 && ((Peon)tablero.getPiezasTablero()[posicion[0]][posicion[1]]).primerTurno == true) movimientoCorrecto = true ;
		}
		// Si la nueva casilla está llena comprobar que las piezas sean de distintos jugadores y que el movimiento sea correcto
		else if ((movimientoY == 1 && movimientoX == 1 || movimientoY == 1 && movimientoX == -1) && tablero.getPiezasTablero()[nuevaPosicion[1]][nuevaPosicion[0]].getJugador() != tablero.getPiezasTablero()[posicion[0]][posicion[1]].getJugador() 
				    && tablero.getPiezasTablero()[posicion[0]][posicion[1]].getJugador() == 2) {
				movimientoCorrecto = true ;
		}
		else if ((movimientoY == -1 && movimientoX == -1 || movimientoY == -1 && movimientoX == 1) && tablero.getPiezasTablero()[nuevaPosicion[1]][nuevaPosicion[0]].getJugador() != tablero.getPiezasTablero()[posicion[0]][posicion[1]].getJugador() 
					    && tablero.getPiezasTablero()[posicion[0]][posicion[1]].getJugador() == 1) {
					movimientoCorrecto = true ;		
	
		}
		if (movimientoCorrecto ==true ) {
			((Peon)tablero.getPiezasTablero()[posicion[0]][posicion[1]]).primerTurno = false;
			tablero.actualizarTablero(posicion,nuevaPosicion);
			return true;
		}
		else  return false;

	}

}
