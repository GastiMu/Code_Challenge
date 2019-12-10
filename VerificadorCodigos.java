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
    
     static String productos[] = new String [5];
    
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
    
    
    /*Modulo que verifica si el producto es prioritario en base a su codigo (empieza con P o W) y devuelve true o false*/
    public static boolean esPrioritario(String codigo){
        boolean prioridad;
        char comienzo;
        
        comienzo = codigo.charAt(0);
        prioridad = (comienzo=='P' || comienzo=='W');
        
        return prioridad;
    }
    
    /*Modulo que corrobora si el digito verificador es correcto (numero entre 0 y 9) devolviendo true o false*/
    public static boolean verificar(String codigo){
        boolean correcto;
        int indiceVerif, verificadorOriginal, verificadorCalculado;
        String codigoRegion;
        
        verificadorOriginal = Integer.parseInt(getVerificador(codigo));
        codigoRegion = getCodigoNumerico(codigo);
        verificadorCalculado = calcularVerificador(codigoRegion);
        correcto = (verificadorOriginal == verificadorCalculado);
        
        return correcto;
    }
    
    /*Modulo que retorna el codigo de la region geografica como cadena*/
    private static String getCodigoNumerico(String codigo){
        int comienzo, fin;
        String codigoNumerico;
                
        comienzo = codigo.indexOf("-") + 1;
        fin = codigo.lastIndexOf("-");
        codigoNumerico = codigo.substring(comienzo, fin);
        
        return codigoNumerico;
    }
    
    /*Modulo que retorna el digito verificador como caracter*/
    private static String getVerificador(String codigo){
        String verificador;
        int ubicacion;
        
        ubicacion = codigo.lastIndexOf("-") + 1;
        verificador = codigo.substring(ubicacion);
        
        return verificador;
    }
    
    /*Modulo que retorna el digito verificador calculado a partir de el codigo de region*/
    private static int calcularVerificador(String codigoReg){
        int verificador, indice = 0, sumatoria = 0;
       
        for(indice = 0; indice < codigoReg.length(); indice++)
            sumatoria = sumatoria + Integer.parseInt(codigoReg.substring(indice, indice+1));
        
        if(sumatoria > 9)
            sumatoria = calcularVerificador(String.valueOf(sumatoria));
        
        return sumatoria;
    }
    
/*Realizar una función que recibe una lista de productos (lista/array de los códigos), y las
ordena alfabéticamente según su código alfabético (XXX), de menor a mayor.*/
    
/*Realizar una función que recibe 2 listas de productos (listas/arrays de los códigos) y
devuelve una lista/array que representa la Unión de los elementos.*/

/*Realizar una función que recibe 2 listas de productos (listas/arrays de los códigos) y
devuelve una lista/array que representa la Intersección de los elementos*/
}
