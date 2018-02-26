/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serpientesescaleras;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.ArrayList;
/**
 *
 * @author Junior
 */
public class administrador {
    
    public String leer(int opcion){
        Scanner scan = new Scanner(System.in);
        String leer = scan.nextLine();
        switch(opcion){
            case 1:
                if(!leer.matches("[0-9]*")){
                    System.out.println("Ingrese un numero");
                    leer=leer(1);
                }
                break;
            case 2:
                if(!leer.matches("[[0-9],[0-9];]+")){
                    System.out.println("Ingrese los datos en el formato indicado, solo numeros");
                    leer=leer(2);
                }
                break;
        }
        /*if(opcion==1){
            if(leer.matches("[0-9]*")){
                return leer;
            }else{
                System.out.println("Ingrese un numero");
                leer(1);
            }
        }else if(opcion==2){
            
        }
              */
        return leer;
    }
    
    public String[] separador(String cadena/*, String caracter*/){
        StringTokenizer token = new StringTokenizer(cadena, ";"/*, caracter*/);
        int contadorCoordenadas = token.countTokens();
        //ArrayList<String> coordenadas = new ArrayList();
        String[] coordenadas = new String[contadorCoordenadas];
        for(int contar = 0;contar<contadorCoordenadas;contar++){
            coordenadas[contar] = token.nextToken();
        }
        return coordenadas;
    }
    
    public int[] separadorComas(String[] coordenada){
        //ArrayList<Integer> coordenadasSeparadasXY = new ArrayList();
        int[] coordenadasSeparadasXY = new int[(coordenada.length*2)];
        //String[] coordenadasSeparadasXY = new
        int posicionVector = 0;
        for(int cadena=0;cadena<coordenada.length;cadena++){
            StringTokenizer token = new StringTokenizer(coordenada[cadena], ",");
            int contadorCoordenadas = token.countTokens();
            for(int contar = 0;contar<contadorCoordenadas;contar++){
                coordenadasSeparadasXY[posicionVector] = (10-Integer.parseInt(token.nextToken()));
                posicionVector++;
            }
        }
        return coordenadasSeparadasXY;
    }
    
    public int dado(){
        int dado = (int)(Math.random()*12)+1;
        return dado;
    }
}