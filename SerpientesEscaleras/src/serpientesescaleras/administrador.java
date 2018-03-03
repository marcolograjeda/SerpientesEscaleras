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
                if(!leer.matches("[0-9]*")/*||leer.equals("")*/){
                    System.out.println("Ingrese un numero");
                    leer=leer(1);
                }else if(leer.equals("")){
                    
                }
                break;
            case 2:
                if(!leer.matches("^([0-9],[0-9];?)+$")||leer.equals("")){
                    System.out.println("Ingrese los datos en el formato indicado, solo numeros");
                    leer=leer(2);
                }
                break;
            case 3:
                if((!leer.equals("1"))/*||(!leer.equals("2"))||!leer.equals("f")||!leer.equals("F")*/){
                    if((!leer.equals("2"))){
                        if((!leer.equals("f"))){
                            if((!leer.equals("F"))){
                                if(!(leer.equals(""))){
                                    System.out.println("Ingrese una de las opciones anteriores");
                                    leer=leer(3);
                                }
                            }
                        }
                    }
                }
                break;
        }
        return leer;
    }
    
    public String[] separador(String cadena){
        StringTokenizer token = new StringTokenizer(cadena, ";");
        int contadorCoordenadas = token.countTokens();
        String[] coordenadas = new String[contadorCoordenadas];
        for(int contar = 0;contar<contadorCoordenadas;contar++){
            coordenadas[contar] = token.nextToken();
        }
        return coordenadas;
    }
    
    public int[] separadorComas(String[] coordenada, int opcion){
        int[] coordenadasSeparadasXY = new int[(coordenada.length*2)];
        int posicionVector = 0;
        switch(opcion){
            case 1:
                for(int cadena=0;cadena<coordenada.length;cadena++){
                    StringTokenizer token = new StringTokenizer(coordenada[cadena], ",");
                    int contadorCoordenadas = token.countTokens();
                    for(int contar = 0;contar<contadorCoordenadas;contar++){
                        coordenadasSeparadasXY[posicionVector] = (10-Integer.parseInt(token.nextToken()));
                        posicionVector++;
                    }
                }
                break;
            case 2:
                for(int cadena=0;cadena<coordenada.length;cadena++){
                    StringTokenizer token = new StringTokenizer(coordenada[cadena], ",");
                    int contadorCoordenadas = token.countTokens();
                    for(int contar = 0;contar<contadorCoordenadas;contar++){
                        coordenadasSeparadasXY[posicionVector] = (Integer.parseInt(token.nextToken()));
                        posicionVector++;
                    }
                }
                break;
        }
        return coordenadasSeparadasXY;
    }
    
    public int dado(){
        int dado = (int)(Math.random()*12)+1;
        return dado;
    }
}