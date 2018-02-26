/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serpientesescaleras;

import java.awt.event.KeyEvent;

/**
 *
 * @author Junior
 */
public class ejecucion {
    jugador player = new jugador();
    administrador admin = new administrador();
    tablero tab = new tablero();
    String[] jugadores = new String[0];
    public void Menu(){
        System.out.println("Ingresa el numero de la opción que desees\n1 Iniciar Juego.\n2 Renaudar Juego.\n3 Salir.\n\n");
        int opcion = Integer.parseInt(admin.leer(1));
        switch(opcion){
            case 1:
                iniciarJuego();
                break;
            case 2:
                break;
            case 3:
                System.exit(0);
        }
    }
    
    public void iniciarJuego(){
        jugadores = player.jugadores();
        tab.iniciarMatriz();
        boolean problema = false;
        boolean repetir = false;
        /*do{
            problema = tab.llenarMatriz("escalera");
            if(problema){
                System.out.println("Hubo una o más escaleras con problemas, ¿desea ingresar más escaleras?\n1. Si\n2. No");
                int opcion = Integer.parseInt(admin.leer(1));
                switch(opcion){
                    case 1:
                        repetir = true;
                        break;
                    case 2:
                        repetir = false;
                        break;
                    default: 
                        System.out.println("No escogiste ninguna opcion.");
                        repetir = false;
                }   
            }
        }while(repetir);
        do{
            problema = tab.llenarMatriz("serpiente");
            if(problema){
                System.out.println("Hubo una o más escaleras con problemas, ¿desea ingresar más escaleras?\n1. Si\n2. No");
                int opcion = Integer.parseInt(admin.leer(1));
                switch(opcion){
                    case 1:
                        repetir = true;
                        break;
                    case 2:
                        repetir = false;
                        break;
                    default: 
                        System.out.println("No escogiste ninguna opcion.");
                        repetir = false;
                }   
            }
        }while(repetir);*/
        for(int jugador=0;jugador<jugadores.length;jugador++){
            Integer idJugador = jugador +1;
            tab.jugadorTablero(idJugador.toString(), 9, 9);
        }
        tab.imprimirMatriz();
        int jugadorTurno = player.turnoRandom(jugadores);
        boolean finJuego = false;
        do{
            juego(jugadorTurno);
            jugadorTurno=player.manejarTurnos(jugadorTurno, jugadores);
            finJuego = tab.finJuego();
        }while(!finJuego);
        System.out.println("El ganador es " + jugadores[jugadorTurno]);
    }
    
    public void juego(int jugadorTurno){
        System.out.println("Es el turno de " + jugadores[jugadorTurno] + " " +jugadorTurno);
        int opcion = formasAvanzar();
        String[] coordenadasXY = tab.obtenerPosicion(jugadorTurno);
        int[] coordenadasDeTurno = admin.separadorComas(coordenadasXY);
        int espacios = lanzamiento(opcion);
        tab.avanzar(coordenadasDeTurno, espacios, jugadorTurno);
        tab.imprimirMatriz();
    }
    
    //arreglar para que escoja una opcion
    public int formasAvanzar(){
        System.out.println("¿Como desea avanzar?\n1 Con el dado\n2 Numero fijo");
        int opcion = Integer.parseInt(admin.leer(1));
        return opcion;
    }
    
    public int lanzamiento(int opcion){
        int espacios =0;
        switch(opcion){
            case 1:
                espacios = admin.dado();
                System.out.println("Sus dados cayeron en: "+espacios);
                break;
            case 2:
                System.out.println("Ingrese el numero que desea");
                espacios = Integer.parseInt(admin.leer(1));
                if(!(0<espacios&&espacios<100)){
                    System.out.println("Puede escoger solamente un numero en el rango de 1 a 99");
                    espacios = lanzamiento(2);
                }
        }
        return espacios;
    }
}