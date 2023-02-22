package juego;

import java.awt.*;

import java.awt.PointerInfo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import componentes.*;


public class Juego {
	
	// atributos 
	 JFrame ventana ;
	
	//Panel principal
	JPanel panelPrincipal;
	JButton iniciar;
	JLabel portada;
	ImageIcon imagenPortada;
	//Panel Juego
	JPanel panelJuego;
	JLabel fondoJuego;
	ImageIcon imagenTablero;

    // botones para las casillas
    JButton	botonTablero = new JButton(); //Nos servira para saber donde clickeamos en el tablero de juego
	
    // Matriz tablero que muestra componentes
    
    JLabel[][] matComponentes = new JLabel [8][8] ;
    { for (int i = 0 ; i<8 ; i++) {
		for (int j = 0 ; j<8 ; j++) {
			matComponentes[i][j]= new JLabel();
		}
    }
    }
    
    
    
    // Turno de juego 
	Integer turnoJugador = 1 ;
	Integer accionTurno = 1 ;
	int[] coordenadasIniciales = new int [2];
	JLabel muestraTurno ;
	JLabel turnoActual = new JLabel();
   
   
	public Juego() {
		
		// Inicializamos componenentes
		Tablero tablero = new Tablero();
		Jugador jugador1 = new Jugador();
		Jugador jugador2 = new Jugador();
		tablero.inicializarTablero(jugador1, jugador2);
		
		JFrame ventana = new JFrame("ajedrez");

		ventana.setSize(350,280);
		ventana.setLayout(null);
		ventana.setLocationRelativeTo(null);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
	    ventana.setVisible(true);
	    
	    //Panel principal
	    
	    panelPrincipal = new JPanel();
	    panelPrincipal.setLayout(null);
	    panelPrincipal.setBounds(0, 0, ventana.getWidth(), ventana.getHeight());
	    
	    panelPrincipal.setVisible(true);
	    panelPrincipal.setBackground(Color.red);
	    
	    
	    iniciar =new JButton("Iniciar");
	    iniciar.setBounds(120, 100, 100, 30);
	    iniciar.setVisible(true);
	    iniciar.setBackground(null);
	    panelPrincipal.add(iniciar,0);
	    iniciar.setBackground(Color.white);
	    	    
	    ventana.add(panelPrincipal);
	    
	    iniciar.addMouseListener(new MouseAdapter () {
	    	public void mousePressed(MouseEvent e) {
	    		
	    		pantallaDeJuego(ventana, tablero);
	    	}
	   
	});
	
	ventana.setVisible(true);
	}
	
	public void pantallaDeJuego(JFrame ventana, Tablero tablero) {
		
		panelPrincipal.setVisible(false); 
		
	    panelJuego = new JPanel();
	    panelJuego.setLayout(null);
	    panelJuego.setBounds(0, 0, ventana.getWidth(), ventana.getHeight());
	    panelJuego.setVisible(true);
	   
	    
	    
	    
	    
	    //Fondo Juego
	    fondoJuego = new JLabel();
	    
	    fondoJuego.setBounds(0, 0, 240, 240);
	   
	    imagenTablero = new ImageIcon("imagenes/imagenTablero.png");
	    fondoJuego.setIcon(imagenTablero);
	    fondoJuego.setVisible(true);
	    panelJuego.add(fondoJuego,0);
	    
	    muestraTurno = new JLabel();
	    muestraTurno.setText("TURNO");
	    muestraTurno.setBounds(265,30,50,50);
	    muestraTurno.setVisible(true);
	    panelJuego.add(muestraTurno,0);
	    

	    botonTablero = new JButton();
	    botonTablero.setBounds(0, 0, 240, 240);
	    botonTablero.setVisible(true);
	    botonTablero.setOpaque(false);
	    botonTablero.setContentAreaFilled(false);
	    botonTablero.setBorderPainted(false);
	    botonTablero.setBackground(null);
		panelJuego.add(botonTablero, 0);
		botonTablero.setBackground(Color.white);
		
		
		actualizarPantalla(tablero);
	   

		botonTablero.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {

				// Se obtiene el objeto de información del puntero del mouseInfo
	            PointerInfo pi = MouseInfo.getPointerInfo(); 

	            // Se obtiene la locacion del mouse 
	            Point p = pi.getLocation(); 

	            // Se obtiene la ubicacion del mouse con respecto a la ventana
	            int[] coordenadasClick = {(p.x-ventana.getX()-7),(p.y-ventana.getY()-30)};
	            if (accionTurno == 1) {
	            	accionTurno = accion(tablero,turnoJugador,coordenadasClick);
	            	if (accionTurno == 2 ) {
	            		coordenadasIniciales[0] = coordenadasClick[0];
	            		coordenadasIniciales[1] = coordenadasClick[1] ;
	            	}
	            }
	            else if (accionTurno == 2) {
	            	accionTurno = accion2(tablero, turnoJugador,coordenadasIniciales,coordenadasClick );
	            	if (accionTurno == 1) {
	            		turnoJugador = turnoJugador == 1 ? 2 : 1 ;
	            		
	            		}
	            	accionTurno = 1;
	            	}
	            
	            actualizarPantalla(tablero);
	            
	            	if(comprobarFinPartida(tablero)) {
            			accionTurno = 3 ;
            			JOptionPane.showMessageDialog(null,"GAME OVER");
	            	   
	            }
	            	
	            
			}
		});
		
	    ventana.add(panelJuego);
	
	}
	
	
	// JUEGO PRINCIPAL
	
	public void Juego() {
		
	}
	
	// Actualiza las imagenes del juego en pantalla
	public void actualizarPantalla(Tablero tablero) {
		
		int[][] matrizPantalla = new int[8][8];   //almacena un numero en cada casilla correspondiente a la imagen a mostrar en cada casilla del tablero
		
		for (int i = 0 ; i<8 ; i++) {
			for (int j = 0 ; j<8 ; j++) {
				
				if(tablero.getPiezasTablero()[i][j] == null || tablero.getPiezasTablero()[i][j].isVida() == false) matrizPantalla[i][j]=0;
				else if (tablero.getPiezasTablero()[i][j] instanceof Peon) {
					if (tablero.getPiezasTablero()[i][j].getJugador() == 1) {
						
					if ((i+j)%2 == 0)matrizPantalla[i][j] = 1;   // fondo negro ,pieza negra
					if ((i+j)%2 != 0)matrizPantalla[i][j] = 2;   // fondo blanco ,pieza negra
					}
					else {
						
						if ((i+j)%2 == 0)matrizPantalla[i][j] = 3;   // fondo negro ,pieza blanca
						if ((i+j)%2 != 0)matrizPantalla[i][j] = 4;   // fondo blanco ,pieza blanca
						
					}
				}
				else if (tablero.getPiezasTablero()[i][j] instanceof Alfil) {
					if (tablero.getPiezasTablero()[i][j].getJugador() == 1) {
						
					if ((i+j)%2 == 0)matrizPantalla[i][j] = 5;   // fondo negro ,pieza negra
					if ((i+j)%2 != 0)matrizPantalla[i][j] = 6;   // fondo blanco ,pieza negra
					}
					else {
						
						if ((i+j)%2 == 0)matrizPantalla[i][j] = 7;   // fondo negro ,pieza blanca
						if ((i+j)%2 != 0)matrizPantalla[i][j] = 8;   // fondo blanco ,pieza blanca
					}
				}
				else if (tablero.getPiezasTablero()[i][j] instanceof Torre) {
					if (tablero.getPiezasTablero()[i][j].getJugador() == 1) {
						
					if ((i+j)%2 == 0)matrizPantalla[i][j] = 9;   // fondo negro ,pieza negra
					if ((i+j)%2 != 0)matrizPantalla[i][j] = 10;   // fondo blanco ,pieza negra
					}
					else {
						
						if ((i+j)%2 == 0)matrizPantalla[i][j] = 11;   // fondo negro ,pieza blanca
						if ((i+j)%2 != 0)matrizPantalla[i][j] = 12;   // fondo blanco ,pieza blanca
					}
				}
				else if (tablero.getPiezasTablero()[i][j] instanceof Caballo) {
					if (tablero.getPiezasTablero()[i][j].getJugador() == 1) {
						
					if ((i+j)%2 == 0)matrizPantalla[i][j] = 13;   // fondo negro ,pieza negra
					if ((i+j)%2 != 0)matrizPantalla[i][j] = 14;   // fondo blanco ,pieza negra
					}
					else {
						
						if ((i+j)%2 == 0)matrizPantalla[i][j] = 15;   // fondo negro ,pieza blanca
						if ((i+j)%2 != 0)matrizPantalla[i][j] = 16;   // fondo blanco ,pieza blanca
					}
				}
				else if (tablero.getPiezasTablero()[i][j] instanceof Rey) {
					if (tablero.getPiezasTablero()[i][j].getJugador() == 1) {
						
					if ((i+j)%2 == 0)matrizPantalla[i][j] = 17;   // fondo negro ,pieza negra
					if ((i+j)%2 != 0)matrizPantalla[i][j] = 18;   // fondo blanco ,pieza negra
					}
					else {
						
						if ((i+j)%2 == 0)matrizPantalla[i][j] = 19;   // fondo negro ,pieza blanca
						if ((i+j)%2 != 0)matrizPantalla[i][j] = 20;   // fondo blanco ,pieza blanca
					}
				}
				else if (tablero.getPiezasTablero()[i][j] instanceof Reina) {
					if (tablero.getPiezasTablero()[i][j].getJugador() == 1) {
						
					if ((i+j)%2 == 0)matrizPantalla[i][j] = 21;   // fondo negro ,pieza negra
					if ((i+j)%2 != 0)matrizPantalla[i][j] = 22;   // fondo blanco ,pieza negra
					}
					else {
						
						if ((i+j)%2 == 0)matrizPantalla[i][j] = 23;   // fondo negro ,pieza blanca
						if ((i+j)%2 != 0)matrizPantalla[i][j] = 24;   // fondo blanco ,pieza blanca
					}
				}
			}		
		}
		
		// Muestra cada imagen por pantalla
		for (int i = 0 ; i<8 ; i++) {
			for (int j = 0 ; j<8 ; j++) {
				matComponentes[i][j].setBounds(j*30, i*30, 30, 30);   
			   if( matrizPantalla[i][j] !=0 ) {
				   matComponentes[i][j].setIcon( new ImageIcon("imagenes/prueba/" + matrizPantalla[i][j] + ".png"));
				   matComponentes[i][j].setVisible(true);
			   }
			   else matComponentes[i][j].setVisible(false);
			    panelJuego.add(matComponentes[i][j],0);
			}
			
			//Actualizar turno mostrado por pantalla
			turnoActual.setBounds(273, 75, 30, 30);
			turnoActual.setIcon(new ImageIcon("imagenes/turno/"+ turnoJugador + ".png"));
			turnoActual.setVisible(true);
			panelJuego.add(turnoActual,0);
			
	}

}
	public int accion(Tablero tablero,Integer turnoJugador, int[] coordenadasClick) {
		boolean siguienteAccion = false;
		
		if (turnoJugador == 1) {
		    if(tablero.getPiezasTablero()[coordenadasClick[1]/30][coordenadasClick[0]/30] != null &&tablero.getPiezasTablero()[coordenadasClick[1]/30][coordenadasClick[0]/30].getJugador() == 1 ) siguienteAccion = true;
		}
		else if (turnoJugador == 2) {
			if(tablero.getPiezasTablero()[coordenadasClick[1]/30][coordenadasClick[0]/30] != null && tablero.getPiezasTablero()[coordenadasClick[1]/30][coordenadasClick[0]/30].getJugador() == 2) siguienteAccion = true;	
		}
		
		if (siguienteAccion == true)  return 2;
		else return 1 ;
		
	}
	
	public int accion2(Tablero tablero,Integer turnoJugador,int[] coordenadasIniciales, int[] coordenadasClick) {
		
		boolean accionCompletada = false ;
		coordenadasClick[0] =  coordenadasClick[0]/30;
		coordenadasClick[1] =  coordenadasClick[1]/30;
		coordenadasIniciales[0] =  coordenadasIniciales[0]/30;
		coordenadasIniciales[1] =  coordenadasIniciales[1]/30;
		if (tablero.getPiezasTablero()[coordenadasIniciales[1]][coordenadasIniciales[0]].mover(coordenadasClick,tablero)==true) {
	
			accionCompletada = true;					
			return 1 ;		
		}	
		return 2 ;
		
		
		
		
		
		
	}
	
	public boolean comprobarFinPartida (Tablero tablero) {
		return tablero.isFinPartida();
	}
	
}
