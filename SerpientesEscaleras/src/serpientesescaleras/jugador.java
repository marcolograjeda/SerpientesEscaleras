/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serpientesescaleras;
//import java.util.Random;
/**
 *
 * @author Junior
 */
public class jugador {
    administrador admin = new administrador();
    public String[] jugadores(){
        int numeroJugadores = numeroJugadores();
        String[] nombresJugadores = new String[numeroJugadores];
        for(int contar=0;contar<numeroJugadores;contar++){
            System.out.println("Ingresa el nombre del jugador");
            nombresJugadores[contar]= admin.leer(0);
        }
        return nombresJugadores;
    }
    
    public String[] turnoRandom(String[] jugadores){
        for(int a=0; a<jugadores.length;a++){
            int posicionRandom = (int)(Math.random()*jugadores.length);
            String temporal = jugadores[a];
            jugadores[a]=jugadores[posicionRandom];
            jugadores[posicionRandom]=temporal;
        }
        return jugadores;
    }
    public int manejarTurnos(int turno, String[] jugadores){
        if(jugadores.length==2){
            switch(turno){
                case 0:
                    turno++;
                    break;
                case 1:
                    turno=0;
                    break;
            }
        }else{
            switch(turno){
                case 0:
                    turno++;
                    break;
                case 1:
                    turno++;
                    break;
                case 2:
                    turno=0;
                    break;
            }
        }
        return turno;
    }
    
    public int numeroJugadores(){
        System.out.println("¿Cuantos jugadores van a participar?");
        int numeroJugadores = Integer.parseInt(admin.leer(1));
        if(numeroJugadores>3){
            System.out.println("El maximo es de 3 jugadores");
            numeroJugadores = numeroJugadores();
        }else if(numeroJugadores<2){
            System.out.println("El minimo de jugadores es de 2");
            numeroJugadores = numeroJugadores();
        }
        return numeroJugadores;
    }
}