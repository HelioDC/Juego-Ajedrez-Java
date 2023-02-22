package componentes;

public class Tablero {
	
	private Pieza [][] piezasTablero ;
	private boolean finPartida = false;
	
	public Tablero () {
		
		this.piezasTablero = new Pieza [8][8];
	}
	
	//Metodos
	public void inicializarTablero(Jugador jugador1 ,Jugador jugador2) {
		
		for (int i = 0 ; i < 8 ; i ++) 	
			{
			piezasTablero[1][i] = jugador1.getPiezasJugador()[i];
			jugador1.getPiezasJugador()[i].setJugador(1);
			jugador1.getPiezasJugador()[i].setPosicion(1,i);
			
			}
		for (int i = 0 ; i < 8 ; i ++) {
			piezasTablero[0][i] = jugador1.getPiezasJugador()[i+8];
			jugador1.getPiezasJugador()[i+8].setJugador(1);
			jugador1.getPiezasJugador()[i+8].setPosicion(0,i);
		}
		for (int i = 0 ; i < 8 ; i ++) 	{
			piezasTablero[6][i] = jugador2.getPiezasJugador()[i];
			jugador2.getPiezasJugador()[i].setJugador(2);
			jugador2.getPiezasJugador()[i].setPosicion(6,i);
		}
		for (int i = 0 ; i < 8 ; i ++) {
			piezasTablero[7][i] = jugador2.getPiezasJugador()[i+8];
			jugador2.getPiezasJugador()[i+8].setJugador(2);
			jugador2.getPiezasJugador()[i+8].setPosicion(7,i);
		}
		
	
		
	}
	
	public void actualizarTablero(int[] posicion, int[] nuevaPosicion) {
		
		// Si se ha movido una pieza a una posicion donde había una pieza del contrario
	    if (piezasTablero[nuevaPosicion[1]][nuevaPosicion[0]] != null && piezasTablero[nuevaPosicion[1]][nuevaPosicion[0]].getJugador() == piezasTablero[posicion[0]][posicion[1]].getJugador()) {
	    	
	    		Pieza pieza1 = piezasTablero[posicion[0]][posicion[1]];
	    		Pieza pieza2 = piezasTablero[nuevaPosicion[1]][nuevaPosicion[0]];
	    		piezasTablero[posicion[0]][posicion[1]] = pieza2 ;
	    		piezasTablero[posicion[0]][posicion[1]].setPosicion(posicion);
	    		piezasTablero[nuevaPosicion[1]][nuevaPosicion[0]] = pieza1 ;
	    		piezasTablero[nuevaPosicion[1]][nuevaPosicion[0]].setPosicion(nuevaPosicion[1],nuevaPosicion[0]);
	    	    
	    	
	    }
	    else {
	    	if(piezasTablero[nuevaPosicion[1]][nuevaPosicion[0]] != null) {
	    
			// Eliminamos la pieza 
			piezasTablero[nuevaPosicion[1]][nuevaPosicion[0]].setVida(false);
			
			//Comprobar si la pieza eliminada es el rey de algun jugador
			if(piezasTablero[nuevaPosicion[1]][nuevaPosicion[0]] instanceof Rey) {
				
				finPartida = true;
			}
         
			piezasTablero[nuevaPosicion[1]][nuevaPosicion[0]]=null ;
		}
		
		// Actualizar pieza a nueva posicion del tablero y eliminar pieza de la posicion anterior
		piezasTablero[nuevaPosicion[1]][nuevaPosicion[0]] =	piezasTablero[posicion[0]][posicion[1]];
		piezasTablero[nuevaPosicion[1]][nuevaPosicion[0]].setPosicion(nuevaPosicion[1],nuevaPosicion[0]);
		piezasTablero[posicion[0]][posicion[1]] = null; 
		// Comprobar si la pieza es un peon que debe convertirse en reina
		if(piezasTablero[nuevaPosicion[1]][nuevaPosicion[0]] instanceof Peon && (nuevaPosicion[1] == 0 || nuevaPosicion[1] == 7)) {
			
			for (int i = 0, k=0;  i < 8 ; i ++) {
				for (int j= 0;  j < 8 ; j ++) {
					if (piezasTablero[i][j] instanceof Reina && piezasTablero[i][j].getJugador() == piezasTablero[nuevaPosicion[1]][nuevaPosicion[0]].getJugador()) {
						 k= 1 ;
					}
					
					}
				if (i == 7 && k == 0) {
					int jugadorPieza = piezasTablero[nuevaPosicion[1]][nuevaPosicion[0]].getJugador();
					piezasTablero[nuevaPosicion[1]][nuevaPosicion[0]] = new Reina(true);
					piezasTablero[nuevaPosicion[1]][nuevaPosicion[0]].setJugador(jugadorPieza);
					piezasTablero[nuevaPosicion[1]][nuevaPosicion[0]].setPosicion(nuevaPosicion[1],nuevaPosicion[0]);
				}
				
			}	
			
			
		}
	    }
	}

	public Pieza[][] getPiezasTablero() {
		return piezasTablero;
	}

	public void setPiezasTablero(Pieza[][] piezasTablero) {
		this.piezasTablero = piezasTablero;
	}
	
	public boolean isFinPartida() {
		return finPartida;
	}
	
	

}
