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
        player.jugadores();
        tab.iniciarMatriz();
        tab.llenarMatriz("escalera");
        tab.llenarMatriz("serpiente");
    }
}
