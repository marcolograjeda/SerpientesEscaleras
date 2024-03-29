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
public class tablero {
    administrador admin = new administrador();
    String matriz[][] = new String [10][10];
    
    //Metodo que sirve para pedir las coordenadas de las serpientes y las escaleras
    public boolean llenarMatriz(String tipo){
        administrador admin = new administrador();
        /*Escaleras = 4
          Serpientes = 5
          Espacio vacio = 0
          Jugadores = 1,2,3*/
        System.out.println("Ingrese las cordenadas para " + tipo + ", con el formato:\nx1,y1;x2,y2;x3,y3");
        String coordenada = admin.leer(2);
        String[] coordenadasXY = admin.separador(coordenada);
        int[] coordenadasSeparadasXY = admin.separadorComas(coordenadasXY, 2);
        boolean malaPosicion = agregarES(coordenadasSeparadasXY, tipo);
        return malaPosicion;
    }
    
    //Agrega las serpientes y escaleras al tablero, verifica que la casilla este habilitada para eso
    public boolean agregarES(int[] coordenadasSeparadasXY, String tipo){
        boolean malaPosicion = false;
        for(int cordenadaX=0;cordenadaX<coordenadasSeparadasXY.length;cordenadaX++){
            int x = coordenadasSeparadasXY[cordenadaX];
            int cordenadaY = cordenadaX + 1;
            int y = coordenadasSeparadasXY[cordenadaY];
            if(x==0&&tipo.equals("escalera")){
                System.out.println("No se puede colocar escalera en: "+(x)+", "+(y));
                malaPosicion = true;
            }else if(matriz[x][y].equals("0")&&tipo.equals("escalera")){
                matriz[x][y]="4";
            }else if(tipo.equals("escalera")){
                System.out.println("No se puede colocar escalera en: "+(x)+", "+(y));
                malaPosicion = true;
            }else if(x==9&&tipo.equals("serpiente")){
                System.out.println("No se puede colocar serpiente en: "+(x)+", "+(y));
                malaPosicion = true;
            }else if(matriz[x][y].equals("0")&&tipo.equals("serpiente")){
                if(matriz[x+1][y].equals("4")){
                    System.out.println("No se puede colocar una serpiente sobre una escalera: "+ (x)+", "+(y));
                    malaPosicion = true;
                }else{
                    matriz[x][y]="5";
                }
                
            }else if(tipo.equals("serpiente")){
                System.out.println("No se puede colocar serpiente en: "+(x)+", "+(y));
                malaPosicion = true;
            }
            cordenadaX += 1;
        }
        return malaPosicion;
    }

    //Muestra la matriz
    public void imprimirMatriz(){
        for(int a=0;a<10;a++){
            System.out.println("-------------------------------------------------------------------------------------------");
            for(int b=0;b<10;b++){
                String casilla= matriz[a][b];
                switch(casilla){
                    case "0":
                        System.out.print("|");
                        for(int veces=0;veces<=7;veces++){
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
                        
                        for(int veces=matriz[a][b].length();veces<=7;veces++){
                            System.out.print(" ");
                        }
                        break;
                }
                
            }
            System.out.print("|\n");
        }
        System.out.println("-------------------------------------------------------------------------------------------");
    }
    
    //Llena la matriz logica que es la base del juego
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
    
    //Situa a los jugadores en el inicio
    public void jugadorTablero(String jugador, int x, int y){
        matriz[x][y]= matriz[x][y]+","+jugador;
    }
    
    //Devuelve la posicion del jugador que esta en el turno
    public String[] obtenerPosicion(int turno){
        String turnoDelJugador = Integer.toString(turno+1);
        String[] coordenada = new String[1];
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
    
    //Manda al jugador a su nueva posicion
    public void avanzar(int[] coordenadasDeTurno, int espacios, int jugadorTurno){
        boolean casillaVacia = true;
        int[] coordenadasXY = new int[2];
            if(10-coordenadasDeTurno[1]-espacios<0){
            int nuevaY = 10-Math.abs(10-coordenadasDeTurno[1]-espacios);
            int nuevaX = 10-(coordenadasDeTurno[0]+1);
            if(nuevaX<0){
                nuevaX = 0;
                nuevaY = 0;
            }
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
            boolean cambioPosicion = true;
            boolean mismaCasilla = false;
            mismaCasilla = cambio(coordenadasXY, nuevaX, nuevaY, jugadorTurno);
            if(mismaCasilla){
                nuevaX++;
            }
            if((10-coordenadasDeTurno[0])==nuevaX&&(10-coordenadasDeTurno[1])!=nuevaY){
               cambioPosicion = false;
            }
            if(mismaCasilla==false){
                    borrarPosicionAnterior(coordenadasDeTurno, jugadorTurno);
            }
        }else{
            int nuevaX = 10-coordenadasDeTurno[0];
            int nuevaY = Math.abs(10-coordenadasDeTurno[1]-espacios);
            coordenadasXY[0]=nuevaX;
            coordenadasXY[1]=nuevaY;
            cambio(coordenadasXY, nuevaX, nuevaY, jugadorTurno);
            borrarPosicionAnterior(coordenadasDeTurno, jugadorTurno);
        }
    }
    
    //Verifica que en la casilla no exista ningun otro elemento
    public boolean casillaVacia(int[] coordenadasXY){
        boolean casillaVacia = false;
        if(matriz[coordenadasXY[0]][coordenadasXY[1]].equals("0")){
           casillaVacia = true;
        }
        return casillaVacia;
    }
    
    //Elimina la posicion del jugador que se movio unicamente es llamado si el jugador se mueve de su posicion
    public void borrarPosicionAnterior(int[] coordenadasDeTurno, int turno){
        coordenadasDeTurno[0]=10-coordenadasDeTurno[0];
        coordenadasDeTurno[1]=10-coordenadasDeTurno[1];
        boolean casillaVacia = casillaVacia(coordenadasDeTurno);
        String casilla = "0";
        if(casillaVacia){
            matriz[10-coordenadasDeTurno[0]][10-coordenadasDeTurno[1]]="0";
        }else{
            String[] separacion = matriz[coordenadasDeTurno[0]][coordenadasDeTurno[1]].split(",");
            for(int po=0;po<separacion.length;po++){
                if(separacion[po].equals(Integer.toString(turno+1))){
                    separacion[po]="0";
                }
                if(!separacion[po].equals("0")){
                    if (!casilla.equals("0")){
                        casilla = casilla+","+separacion[po];
                    }else{
                        casilla=separacion[po];
                    }
                }
            }
            matriz[coordenadasDeTurno[0]][coordenadasDeTurno[1]]=casilla;
        }
    }
    
    //Revisa que algun jugador haya ganado
    public boolean finJuego(){
        boolean fin = false;
        if(matriz[0][0].length()>1){
            fin = true;
        }
        return fin;
    }
    
    //Si la casilla tiene algun contenido verifica si es serpiente o escalera
    public int verificarContenidoCasilla(int x, int y){
        int contenido=0;
        if(matriz[x][y].equals("4")){
            contenido = 4;
        }else if(matriz[x][y].equals("5")){
            contenido = 5;
        }
        return contenido;
    }
    
    //Se encarga de realizar todo el procedimiento de mover al jugador y llama a los metodos necesarios 
    public boolean cambio(int[] coordenadasXY, int nuevaX, int nuevaY, int jugadorTurno){
        boolean mismaCasilla = false;
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
                    mismaCasilla =cambio(coordenadasXY, nuevaX, nuevaY, jugadorTurno);
                    break;
                default:
                    String[] separacion = matriz[nuevaX][nuevaY].split(",");
                    for(int po=0;po<separacion.length;po++){
                        if(separacion[po].equals(Integer.toString(jugadorTurno+1))){
                            mismaCasilla=true;
                        }
                    }
                    if(mismaCasilla==false){
                        matriz[nuevaX][nuevaY] = matriz[nuevaX][nuevaY]+","+Integer.toString(jugadorTurno+1);
                    }
            }
        }
        return mismaCasilla;
    }
}