/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code_challenge;
/**
 *
 * @author Gaston
 */public class VerificadorCodigos{
    
     static String productos[];
    
    /*Main para ejecucion de pruebas*/
    public static void main (String [] args){
        
        cargaTest();
        
        
        
    }
    
    /*Modulo para cargar un set de datos de prueba para testear, 
    con formato XXX-NNNNN-Y, donde  X: letra(A-Z), N: numero(0-9), Y: numero(0-9)*/
    public static void cargaTest(){
        productos[0] = "ABC-12345-8";
        productos[1] = "DCR-88578-9";
        productos[2] = "";
        productos[3] = "";
        productos[4] = "";  
    }
    
    
    /*Modulo que verifica si el producto es prioritario en base a su codigo (empieza con P o W)*/
    public static boolean esPrioritario(String codigo){
        boolean prioridad;
        char comienzo;
        
        comienzo = codigo.charAt(0);
        prioridad = (comienzo=='P' || comienzo=='W');
        
        return prioridad;
    }
    
    /*Modulo que corrobora si el digito verificador es correcto (numero entre 0 y 9)*/
    public boolean verificar(String codigo){
        boolean verificador;
        int indice;
        
        indice = ubicarVerificador(codigo);
        verificador = Character.isDigit(codigo.charAt(indice));
        
        return verificador;
    }
    
    //pensado en la posibilidad de que el codigo alfabetico del producto aumentara en cifras
    private int static ubicarCodigo(String codigo){
        
    }
    
    //pensado en la posibilidad de que el codigo alfabetico del producto y/o numero de region aumentaran en cifras
    private int static ubicarVerificador(String codigo){
        
    }
    
/*Realizar una función que recibe una lista de productos (lista/array de los códigos), y las
ordena alfabéticamente según su código alfabético (XXX), de menor a mayor.*/
    
/*Realizar una función que recibe 2 listas de productos (listas/arrays de los códigos) y
devuelve una lista/array que representa la Unión de los elementos.*/

/*Realizar una función que recibe 2 listas de productos (listas/arrays de los códigos) y
devuelve una lista/array que representa la Intersección de los elementos*/
}
