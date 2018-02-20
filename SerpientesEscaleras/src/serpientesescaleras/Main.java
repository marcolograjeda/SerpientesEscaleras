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
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("¡Bienvenido!");
        ejecucion ejc = new ejecucion();
        ejc.Menu();
        //int numero = Integer.parseInt(ejc.leer(1));
        //System.out.println(numero);
        /*
        int matriz[][] = new int [10][10];
        for(int a=0;a<2;a++){
            for(int b=0;b<2;b++){
                matriz[a][b]=0;
            }
        }
        
        for(int a=0;a<10;a++){
            for(int b=0;b<10;b++){
                System.out.print(matriz[a][b]);;
            }
            System.out.println();
        }
        
        */
    }    
}
