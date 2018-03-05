/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serpientesescaleras;

/**
 *
 * @author Junior
 */
public class ejecucion {
    jugador player = new jugador();
    administrador admin = new administrador();
    tablero tab = new tablero();
    String[] jugadores = new String[0];
    int ronda = 0;
    
    //El menu principal del juego
    public int Menu(){
        System.out.println("Ingresa el numero de la opción que desees\n1 Iniciar Juego.\n2 Reanudar Juego.\n3 Salir.\n\n");
        int opcion = Integer.parseInt(admin.leer(1));
        switch(opcion){
            case 1:
                iniciarJuego();
                break;
            case 2:
                if(jugadores.length==0){
                    System.out.println("No hay un juego iniciado\n");
                    Menu();
                }else{
                    if(tab.finJuego()){
                        System.out.println("El juego anterior finalizó");
                        Menu();
                    }
                }
                break;
            case 3:
                System.out.println("Feliz dia");
                System.exit(0);
            default:
                System.out.println("Escoja una de las opciones anteriores");
                Menu();
        }
        return opcion;
    }
    
    //Inicia el juego cuando se selecciona la opcion en el menu
    public void iniciarJuego(){
        jugadores = player.jugadores();
        tab.iniciarMatriz();
        if (ronda!=0){
            ronda =0;
        }
        boolean problema = false;
        boolean repetir = false;
        repetirCoordenadas("serpiente", false, false);
        repetirCoordenadas("escalera", false, false);
        jugadores = player.turnoRandom(jugadores);
        System.out.println("El orden del juego va ser");
        for(int jug=0;jug<jugadores.length;jug++){
            System.out.println(jug+1+" "+jugadores[jug]);
        }
        int jugadorTurno = 0;
        for(int jugador=0;jugador<jugadores.length;jugador++){
            Integer idJugador = jugador +1;
            tab.jugadorTablero(idJugador.toString(), 9, 9);
        }
        System.out.println("Este es el tablero del juego (Presione enter para avanzar)");
        tab.imprimirMatriz();
        admin.leer(5);
        boolean finJuego = false;
        do{
            ronda = contarRonda(jugadorTurno, ronda);
            juego(jugadorTurno);
            finJuego = tab.finJuego();
            if(!finJuego){
                jugadorTurno=player.manejarTurnos(jugadorTurno, jugadores);
            }
        }while(!finJuego);
        System.out.println("El ganador es " + jugadores[jugadorTurno]+"\n\n");
        Menu();
    }
    
    //Comienza el juego hasta que un jugador llega a la ultima casilla y gana
    public void juego(int jugadorTurno){
        System.out.println("\nEs el turno de " + jugadores[jugadorTurno] + "                          Ronda:" +ronda);
        tab.imprimirMatriz();
        String opcion = formasAvanzar();
        int escogio =0;
        if(opcion.equals("p")){
            escogio = Menu();
            juego(jugadorTurno);
        }else if(!(opcion.equals("f"))/*||(!(opcion.equals("F")))*/){
            if((!(opcion.equals("F")))){
                String[] coordenadasXY = tab.obtenerPosicion(jugadorTurno);
                int[] coordenadasDeTurno = admin.separadorComas(coordenadasXY,1);
                int espacios = lanzamiento(Integer.parseInt(opcion));
                tab.avanzar(coordenadasDeTurno, espacios, jugadorTurno);
                tab.imprimirMatriz();
                boolean finJuego = tab.finJuego();
                if(finJuego==false){
                    System.out.println("Si desea finalizar su turno ingrese f\nSi desea pausar el juego presione p");
                    if(admin.leer(4).equals("p")){
                        Menu();
                    }   
                }
            }
        }
    }
    
    //Menu auxiliar para las formas de avanzar
    public String formasAvanzar(){
        System.out.println("¿Como desea avanzar?\n1 Con el dado              (Ingrese p para ir al menu o pausar el juego)"
                +"\n2 Numero fijo              (Ingrese f para finalizar su turno)");
        String opcion = admin.leer(3);
        return opcion;
    }
    
    //Muestra y calcula el resultado de un lanzamiento aleatorio
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
    
    //Si existe algun error con alguna serpiente o escalera, nos pregunta si se desea ingresar alguna otra nuevamente
    public boolean repetirCoordenadas(String tipo, boolean repetir, boolean problema){
        do{
            problema = tab.llenarMatriz(tipo);
            if(problema){
                System.out.println("Hubo una o más "+ tipo+"s con problemas, ¿desea ingresar más "+ tipo+"s?\n1. Si\n2. No");
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
            }else{
                repetir = false;
            }
        }while(repetir);
        return true;
    }
    
    //Cuenta las rondas que se dan en el juego
    public int contarRonda(int jugadorTurno, int ronda){
        if(jugadorTurno==0){
            ronda++;
        }
        return ronda;
    }
}