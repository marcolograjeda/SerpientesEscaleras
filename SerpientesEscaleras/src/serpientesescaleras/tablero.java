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
        ArrayList<String> coordenadasXY = admin.separador(coordenada);
        ArrayList<Integer> coordenadasSeparadasXY = admin.separadorComas(coordenadasXY);
        /*for(int a=0;a<coordenadasXY.size();a++){  
            System.out.println(coordenadasXY.get(a));
        }
        for(int a=0;a<coordenadasSeparadasXY.size();a++){
            System.out.println(coordenadasSeparadasXY.get(a));
        }*/
        for(int cordenadaX=0;cordenadaX<coordenadasSeparadasXY.size();cordenadaX++){
            int x = coordenadasSeparadasXY.get(cordenadaX);
            int cordenadaY = cordenadaX + 1;
            int y = coordenadasSeparadasXY.get(cordenadaY);
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
        imprimirMatriz();
    }

    public void imprimirMatriz(){
        for(int a=0;a<10;a++){
            for(int b=0;b<10;b++){
                System.out.print(matriz[a][b]);
            }
            System.out.println();
        }
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
}
