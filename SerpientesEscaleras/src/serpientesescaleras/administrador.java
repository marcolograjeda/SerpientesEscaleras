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
        if(opcion==1){
            if(leer.matches("[0-9]*")){
                return leer;
            }else{
                System.out.println("Ingrese un numero");
                leer(1);
            }
        }else if(opcion==2){
            if(leer.matches("[[0-9],[0-9];]+")){
                return leer;
            }else{
                System.out.println("Ingrese los datos en el formato indicado, solo numeros");
                leer(2);
            }
        }
        return leer;       
    }
    
    public ArrayList separador(String cadena/*, String caracter*/){
        StringTokenizer token = new StringTokenizer(cadena, ";"/*, caracter*/);
        int contadorCoordenadas = token.countTokens();
        ArrayList<String> coordenadas = new ArrayList();
        for(int contar = 1;contar<=contadorCoordenadas;contar++){
            coordenadas.add(token.nextToken());
        }
        return coordenadas;
    }
    
    public ArrayList separadorComas(ArrayList coordenada){
        ArrayList<Integer> coordenadasSeparadasXY = new ArrayList();
        for(int cadena=0;cadena<coordenada.size();cadena++){
            StringTokenizer token = new StringTokenizer(coordenada.get(cadena).toString(), ",");
            int contadorCoordenadas = token.countTokens();
            for(int contar = 0;contar<contadorCoordenadas;contar++){
                coordenadasSeparadasXY.add(10-Integer.parseInt(token.nextToken()));
            }
        }
        return coordenadasSeparadasXY;
    }
}