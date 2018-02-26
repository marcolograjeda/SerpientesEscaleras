/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serpientesescaleras;
import java.util.ArrayList;
/**
 *
 * @author Junior
 */
public class jugador {
    administrador admin = new administrador();
    public String[] jugadores(){
        System.out.println("¿Cuantos jugadores van a participar?");
        int numeroJugadores = Integer.parseInt(admin.leer(1));
        if(numeroJugadores>3){
            System.out.println("El maximo es de 3 jugadores");
            jugadores();
        }else if(numeroJugadores<2){
            System.out.println("El minimo de jugadores es de 2");
            jugadores();
        }
        String[] nombresJugadores = new String[numeroJugadores];
        //String[] nombresJugadores = new String[numeroJugadores];
        //ArrayList<String> nombresJugadores = new ArrayList();
        for(int contar=0;contar<numeroJugadores;contar++){
            System.out.println("Ingresa el nombre del jugador");
            nombresJugadores[contar]= admin.leer(0);
        }
        /*for(int contar=0;contar<nombresJugadores.length;contar++){
            System.out.println(nombresJugadores[contar]);
        }*/
        return nombresJugadores;
    }
    
    public int turnoRandom(String[] jugadores){
        int posicion = (int)(Math.random()*jugadores.length);
        return posicion; 
    }
    
    public int manejarTurnos(int turno,String[] jugadores){
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
                case 2:
                    turno=0;
                    break;
            }
        }
        return turno;
    }
}