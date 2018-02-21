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
    public void jugadores(){
        System.out.println("¿Cuantos jugadores van a participar?");
        int numeroJugadores = Integer.parseInt(admin.leer(1));
        if(numeroJugadores>3){
            System.out.println("El maximo es de 3 jugadores");
            jugadores();
        }else if(numeroJugadores<2){
            System.out.println("El minimo de jugadores es de 2");
            jugadores();
        }
        else{
            ArrayList<String> nombresJugadores = new ArrayList();
            for(int contar=1;contar<=numeroJugadores;contar++){
                System.out.println("Ingresa el nombre del jugador");
                nombresJugadores.add(admin.leer(0));
            }
            /*for(int contar=0;contar<nombresJugadores.size();contar++){
                System.out.println(nombresJugadores.get(contar));
            }*/
        }
    }
}
