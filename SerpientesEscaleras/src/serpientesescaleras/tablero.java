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
public class tablero {
    administrador admin = new administrador();
    String matriz[][] = new String [10][10];
    public void llenarMatriz(String tipo){
        administrador admin = new administrador();
        /*Escaleras = 4
          Serpientes = 5
          Espacio vacio = 0
          Jugadores = 1,2,3*/
        System.out.println("Ingrese las cordenadas para " + tipo + ", con el formato:\nx1,y1;x2,y2;x3,y3");
        String coordenada = admin.leer(2);
        String[] coordenadasXY = admin.separador(coordenada);
         
        int[] coordenadasSeparadasXY = admin.separadorComas(coordenadasXY);
        /*for(int a=0;a<coordenadasXY.size();a++){  
            System.out.println(coordenadasXY.get(a));
        }
        for(int a=0;a<coordenadasSeparadasXY.size();a++){
            System.out.println(coordenadasSeparadasXY.get(a));
        }*/
        agregarES(coordenadasSeparadasXY, tipo);
        //imprimirMatriz();
    }
    
    public void agregarES(int[] coordenadasSeparadasXY, String tipo){
        for(int cordenadaX=0;cordenadaX<coordenadasSeparadasXY.length;cordenadaX++){
            int x = coordenadasSeparadasXY[cordenadaX];
            int cordenadaY = cordenadaX + 1;
            int y = coordenadasSeparadasXY[cordenadaY];
            if(x==0&&tipo.equals("escalera")){
                System.out.println("No se puede colocar escalera en: "+(10-x)+", "+(10-y));
            }else if(matriz[x][y].equals("0")&&tipo.equals("escalera")){
                matriz[x][y]="4";
            }else if(tipo.equals("escalera")){
                System.out.println("No se puede colocar escalera en: "+(10-x)+", "+(10-y));
            }else if(x==9&&tipo.equals("serpiente")){
                System.out.println("No se puede colocar serpiente en: "+(10-x)+", "+(10-y));
            }else if(matriz[x][y].equals("0")&&tipo.equals("serpiente")){
                matriz[x][y]="5";
            }else if(tipo.equals("serpiente")){
                System.out.println("No se puede colocar serpiente en: "+(10-x)+", "+(10-y));
            }
            cordenadaX += 1;
        }
    }

    public void imprimirMatriz(){
        for(int a=0;a<10;a++){
            System.out.println("-----------------------------------------------------------------------");
            for(int b=0;b<10;b++){
                String casilla= matriz[a][b];
                switch(casilla){
                    case "0":
                        System.out.print("|");
                        for(int veces=0;veces<=5;veces++){
                            System.out.print(" ");
                        }
                        break;
                    default:
                        System.out.print("|");
                        if(matriz[a][b].equals("4")){
                            System.out.print("E");
                        }else if(matriz[a][b].equals("5")){
                            System.out.print("S");
                        }else{
                            System.out.print(matriz[a][b]);
                        }
                        
                        for(int veces=matriz[a][b].length();veces<=5;veces++){
                            System.out.print(" ");
                        }
                        break;
                }
                
            }
            System.out.print("|\n");
        }
        System.out.println("-----------------------------------------------------------------------");
    }
    
    public void iniciarMatriz(){
        for(int a=0;a<10;a++){
            for(int b=0;b<10;b++){
                if(a==0 && b==0){
                    matriz[a][b]="$";
                }else if(a==9 && b==9){
                    matriz[a][b]="*";
                }else{
                    matriz[a][b]="0";
                }
            }
        }
    }
    
    public void jugadorTablero(String jugador, int x, int y){
        matriz[x][y]= matriz[x][y]+","+jugador;
    }
    
    public String[] obtenerPosicion(int turno){
        String turnoDelJugador = Integer.toString(turno+1);
        String[] coordenada = new String[1];
        //String match = "["+turnoDelJugador+"]";
        for(int x=0;x<matriz.length;x++){
            for(int y=0;y<matriz[0].length;y++){
                String[] separacion = matriz[x][y].split(",");
                for(int po=0;po<separacion.length;po++){
                    if(separacion[po].equals(Integer.toString(turno+1))){
                       coordenada[0] = Integer.toString(x) + ","+ Integer.toString(y);
                       return coordenada;
                    }
                }
            }
        }
        return coordenada;
    }
    
    public void avanzar(int[] coordenadasDeTurno, int espacios, int jugadorTurno){
        boolean casillaVacia = true;
        int[] coordenadasXY = new int[2];
        if(10-coordenadasDeTurno[1]-espacios<0 && coordenadasDeTurno[0]==10){
            matriz[0][0] = matriz[0][0]+","+Integer.toString(jugadorTurno+1);
            borrarPosicionAnterior(coordenadasDeTurno, jugadorTurno);
        }else if(10-coordenadasDeTurno[1]-espacios<0){
            int nuevaY = 10-Math.abs(10-coordenadasDeTurno[1]-espacios);
            int nuevaX = 10-(coordenadasDeTurno[0]+1);
            int fila = 1;
            while(nuevaY<0){
                if (nuevaX>0){
                    nuevaX = nuevaX-fila;
                    nuevaY = 10+nuevaY;
                }else{
                    nuevaX = 0;
                    nuevaY = 0;
                }
            }
            coordenadasXY[0]=nuevaX;
            coordenadasXY[1]=nuevaY;
            cambio(coordenadasXY, nuevaX, nuevaY, jugadorTurno);
            /*casillaVacia = casillaVacia(coordenadasXY);
            if(casillaVacia){
                matriz[nuevaX][nuevaY] = Integer.toString(jugadorTurno+1);
            }else{
                int tipo = verificarContenidoCasilla(nuevaX,nuevaY);
                switch(tipo){
                    case 4:
                        nuevaX--;
                        matriz[nuevaX][nuevaY] = Integer.toString(jugadorTurno+1);
                        break;
                    case 5:
                        nuevaX++;
                        matriz[nuevaX][nuevaY] = Integer.toString(jugadorTurno+1);
                        break;
                    default:
                        matriz[nuevaX][nuevaY] = matriz[nuevaX][nuevaY]+","+Integer.toString(jugadorTurno+1);
                }
            }*/
            borrarPosicionAnterior(coordenadasDeTurno, jugadorTurno);
        }else{
            int nuevaX = 10-coordenadasDeTurno[0];
            int nuevaY = Math.abs(10-coordenadasDeTurno[1]-espacios);
            coordenadasXY[0]=nuevaX;
            coordenadasXY[1]=nuevaY;
            cambio(coordenadasXY, nuevaX, nuevaY, jugadorTurno);
            /*casillaVacia = casillaVacia(coordenadasXY);
            if(casillaVacia){
                matriz[nuevaX][nuevaY] = Integer.toString(jugadorTurno+1);
            }else{
                int tipo = verificarContenidoCasilla(nuevaX,nuevaY);
                switch(tipo){
                    case 4:
                        nuevaX--;
                        matriz[nuevaX][nuevaY] = Integer.toString(jugadorTurno+1);
                        break;
                    case 5:
                        nuevaX++;
                        matriz[nuevaX][nuevaY] = Integer.toString(jugadorTurno+1);
                        break;
                    default:
                        matriz[nuevaX][nuevaY] = matriz[nuevaX][nuevaY]+","+Integer.toString(jugadorTurno+1);
                }
            }*/
            borrarPosicionAnterior(coordenadasDeTurno, jugadorTurno);
        }
    }
    
    public boolean casillaVacia(int[] coordenadasXY){
        boolean casillaVacia = false;
        /*int x = coordenadasXY[0];
        int y = coordenadasXY[1];*/
        if(matriz[coordenadasXY[0]][coordenadasXY[1]].equals("0")){
           casillaVacia = true;
        }
        return casillaVacia;
    }
    
    public void borrarPosicionAnterior(int[] coordenadasDeTurno, int turno){
        coordenadasDeTurno[0]=10-coordenadasDeTurno[0];
        coordenadasDeTurno[1]=10-coordenadasDeTurno[1];
        boolean casillaVacia = casillaVacia(coordenadasDeTurno);
        String casilla = "";
        if(casillaVacia){
            matriz[10-coordenadasDeTurno[0]][10-coordenadasDeTurno[1]]="0";
        }else{
            String[] separacion = matriz[coordenadasDeTurno[0]][coordenadasDeTurno[1]].split(",");
            for(int po=0;po<separacion.length;po++){
                if(separacion[po].equals(Integer.toString(turno+1))){
                    separacion[po]="0";
                }
                if(!separacion[po].equals("0")){
                    if (casilla.length()>0){
                        casilla = casilla+","+separacion[po];
                    }else{
                        casilla=separacion[po];
                    }
                }
            }
            matriz[coordenadasDeTurno[0]][coordenadasDeTurno[1]]=casilla;
        }
    }
    
    public boolean finJuego(){
        boolean fin = false;
        if(matriz[0][0].length()>1){
            fin = true;
        }
        return fin;
    }
    
    public int verificarContenidoCasilla(int x, int y){
        int contenido=0;
        if(matriz[x][y].equals("4")){
            contenido = 4;
        }else if(matriz[x][y].equals("5")){
            contenido = 5;
        }
        return contenido;
    }
    
    public void cambio(int[] coordenadasXY, int nuevaX, int nuevaY, int jugadorTurno){
        boolean casillaVacia = casillaVacia(coordenadasXY);
        if(casillaVacia){
            matriz[nuevaX][nuevaY] = Integer.toString(jugadorTurno+1);
        }else{
            int tipo = verificarContenidoCasilla(nuevaX,nuevaY);
            switch(tipo){
                case 4:
                    nuevaX--;
                    coordenadasXY[0]=nuevaX;
                    cambio(coordenadasXY, nuevaX, nuevaY, jugadorTurno);
                    break;
                case 5:
                    nuevaX++;
                    coordenadasXY[0]=nuevaX;
                    cambio(coordenadasXY, nuevaX, nuevaY, jugadorTurno);
                    break;
                default:
                    matriz[nuevaX][nuevaY] = matriz[nuevaX][nuevaY]+","+Integer.toString(jugadorTurno+1);
            }
        }
    }
}
