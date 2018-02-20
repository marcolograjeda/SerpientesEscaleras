/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serpientesescaleras;
/*import java.util.regex.Matcher;
import java.util.regex.Pattern;*/
import java.util.Scanner;
import java.util.ArrayList;
/**
 *
 * @author Junior
 */
public class ejecucion {
    public void Menu(){
        System.out.println("Ingresa el numero de la opción que desees\n1 Iniciar Juego.\n2 Renaudar Juego.\n3 Salir.");
        iniciarJuego();//Leer(1);
        
    }
    
    public void iniciarJuego(){
        jugadores();
    }
    
    public void jugadores(){
        System.out.println("¿Cuantos jugadores van a participar?");
        int numeroJugadores = Integer.parseInt(leer(1));
        ArrayList<String> nombresJugadores = new ArrayList();
        for(int contar=1;contar<=numeroJugadores;contar++){
            System.out.println("Ingresa el nombre del jugador");
            nombresJugadores.add(leer(0));
        }
        for(int contar=0;contar<nombresJugadores.size();contar++){
            System.out.println(nombresJugadores.get(contar));
        }
    }
    
    public String leer(int opcion){
        Scanner scan = new Scanner(System.in);
        String leer = scan.nextLine();
        if(opcion==1){
            if(leer.matches("[0-9]*")){
                return leer;
            }else{
                System.out.println("Ingrese un numero");
                leer(1);
            }
        }
        return leer;       
    }
}


