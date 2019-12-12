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
    
    /*Main para ejecucion de pruebas*/
    public static void main (String [] args){
        String productos1[] = new String [5];
        String productos2[] = new String [5];
        String productos3[];
        
        cargaTest(productos1);
        cargaTest1(productos2);
       /* 
        ordenarAlfabeticamente(productos1);
        imprimirLista(productos2);
        
        ordenarAlfabeticamente(productos2);
        imprimirLista(productos2);
        
        productos3 = unirListas(productos1, productos2);
        imprimirLista(productos3);
        
        productos3 = intersectarListas(productos1, productos2);
        imprimirLista(productos3);
        */
        
    }
    
    /*Modulo para cargar un set de datos de prueba para testear, 
    con formato XXX-NNNNN-Y, donde  X: letra(A-Z), N: numero(0-9), Y: numero(0-9)*/
    public static void cargaTest(String productos[]){
        productos[0] = "ABC-12345-8";
        productos[1] = "BCA-34389-10";
        productos[2] = "ZZY-85432-6";
        productos[3] = "RRM-33333-3";
        productos[4] = "ZZY-85432-6";
    }
    
    public static void cargaTest1(String productos[]){
        productos[0] = "BCA-34379-10";
        productos[1] = "BCA-34389-10";
        productos[2] = "ZZY-85432-7";
        productos[3] = "RRM-33333-3";
        productos[4] = "BAC-34389-10";
    }
    
    
    /*Modulo que verifica si el producto es prioritario en base a su codigo (empieza con P o W) 
    y devuelve true o false*/
    public static boolean esPrioritario(String codigo){
        boolean prioridad;
        char comienzo;
        
        comienzo = codigo.charAt(0);
        prioridad = (comienzo=='P' || comienzo=='W');
        
        return prioridad;
    }
    
    /*Modulo que corrobora si el digito verificador es correcto (numero entre 0 y 9) 
    devolviendo true o false*/
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
        int indice = 0, sumatoria = 0;
       
        for(indice = 0; indice < codigoReg.length(); indice++)
            sumatoria = sumatoria + Integer.parseInt(codigoReg.substring(indice, indice+1));
        
        if(sumatoria > 9)
            sumatoria = calcularVerificador(String.valueOf(sumatoria));
        
        return sumatoria;
    }
    
    /*Modulo que recibe una lista de productos y los ordena alfabeticamente segun su codigo 
    alfabetico de menor a mayor. Se utilizo Quicksort por su eficiencia computacional (n log n, en el 
    peor de los casos) frente a otros algoritmos de ordenamiento*/
    public static void ordenarAlfabeticamente(String [] productos){
        ordenarAlfabeticamenteAux(productos, 0, productos.length-1);
    }
    
    /*Modulo auxiliar del anterior con la logica para ordenar el arreglo correctamente*/
    private static void ordenarAlfabeticamenteAux(String [] productos, int inicio, int fin){
        int izquierda, derecha;
        String pivote, auxiliar;
        
        if(inicio >= fin) return;
            pivote = productos[inicio];
            izquierda = inicio + 1;
            derecha = fin;
            while(izquierda <= derecha){
                        while(izquierda <= fin && productos[izquierda].compareTo(pivote) > 0){
                                izquierda++;
                        }
                        while(derecha > inicio && productos[derecha].compareTo(pivote) <= 0){
                                derecha--;
                        }
                        if(izquierda < derecha){
                                auxiliar = productos[izquierda];
                                productos[izquierda] = productos[derecha];
                                productos[derecha] = auxiliar;
                        }
            }
            if(derecha > inicio){
                auxiliar = productos[inicio];
                productos[inicio] = productos[derecha];
                productos[derecha] = auxiliar;
            }
                
            ordenarAlfabeticamenteAux(productos, inicio, derecha-1);
            ordenarAlfabeticamenteAux(productos, derecha+1, fin);
    }
      
    /*Modulo que imprime una lista de productos por pantalla*/
    public static void imprimirLista(String [] lista){
        for(int i = 0; i < lista.length; i++)
            System.out.println(lista[i]);
    }
    
    /*Modulo que recibe 2 listas de productos y devuelve la union de ambas. Si un producto esta en 
    ambas listas, en la resultante solo estara una vez*/
    public static String[] unirListas(String [] lista1, String [] lista2){
        
        boolean repetido;
        int index = 0, index3 = 0;
        String [] lista3 = new String [lista1.length + lista2.length];
        
        /*while(index < lista1.length){
            repetido = verificarExistencia(lista1[index], lista3, index3);
            if(!repetido){
                lista3[index3] = lista1[index];
                index++;
                index3++;
            }
            else {
                index++;
            }
        }*/
        index3 = unirListasAux(lista1, index, lista3, index3);
        index = 0;
        
        /*while(index < lista2.length){
            repetido = verificarExistencia(lista2[index], lista3, index3);
            if(!repetido){
                lista3[index3] = lista2[index];
                index++;
                index3++;
            }
            else {
                index++;
            }
        }*/
        index3 = unirListasAux(lista2, index, lista3, index3);
        
        lista3 = optimizarMemoria(lista3, index3);
        
        return lista3;
    }
    
    /*Modulo que sirve de auxiliar para verificar y asignar en caso de union, para evitar repetir codigo*/
    private static int unirListasAux(String [] lista, int indiceLista, String [] resultado, int indiceResultado){
        boolean repetido;
        
        while(indiceLista < lista.length){
            repetido = verificarExistencia(lista[indiceLista], resultado, indiceResultado);
                if(!repetido){
                    resultado[indiceResultado] = lista[indiceLista];
                    indiceLista++;
                    indiceResultado++;
                }
                else {
                    indiceLista++;
                }
        }
        return indiceResultado;
    }
    
    /*Modulo auxiliar que verifica si un producto ya pertenece a la union de listas*/
    private static boolean verificarExistencia(String codigo, String [] lista, int maximoCargado){
        boolean existe = false;
        int i = 0;
        
        while(i < maximoCargado && !existe){
            existe = codigo.equalsIgnoreCase(lista[i]);
            i++;
        }
        
        return existe;
    }
    
    /*Modulo que optimiza la memoria en caso que el arreglo resultante ocupe mas memoria de la 
    requerida por la union (por ejemplo, si son dos listas grandes con muchos productos en comun)*/
    private static String[] optimizarMemoria(String [] lista, int index){
        String [] nuevaLista = new String [index];
        
        System.arraycopy(lista, 0, nuevaLista, 0, index);
        
        return nuevaLista;
    }

    /*Modulo que recibe 2 listas de productos y devuelve la interseccion de ambas, la resultante siempre
    toma el menor tamaño entre ambas*/
    public static String[] intersectarListas(String [] lista1, String [] lista2){
        String [] lista3;
        boolean repetido;
        int index = 0, index3 = 0;
        
        if(lista1.length > lista2.length){
            lista3 = new String [lista2.length];
            /*
            while(index < lista2.length){
                repetido = verificarExistencia(lista2[index], lista1, lista1.length);
                if(repetido){
                    lista3[index3] = lista2[index];
                    index++;
                    index3++;
                }
                else {
                    index++;
                }
            }*/
            index3 = intersectarListasAux(lista1, lista2, index, lista3, index3);
        }
        else{
            lista3 = new String [lista1.length];
            /*
            while(index < lista1.length){
                repetido = verificarExistencia(lista1[index], lista2, lista2.length);
                if(repetido){
                    lista3[index3] = lista1[index];
                    index++;
                    index3++;
                }
                else {
                    index++;
                }
            }*/
            index3 = intersectarListasAux(lista2, lista1, index, lista3, index3);
        }
            
        
        lista3 = optimizarMemoria(lista3, index3);
        
        return lista3;
    }
    
    /*Modulo que sirve de auxiliar para verificar y asignar en caso de interseccion, 
    para evitar repetir codigo*/
    private static int intersectarListasAux(String [] lista1, String [] lista2, int indiceLista, String [] resultado, int indiceResultado){
        boolean repetido;
        
        while(indiceLista < lista1.length){
            repetido = verificarExistencia(lista1[indiceLista], lista2, lista2.length);
            if(repetido){
                resultado[indiceResultado] = lista1[indiceLista];
                indiceLista++;
                indiceResultado++;
            }
            else {
                indiceLista++;
            }
        }
        
        return indiceResultado;
    }
}
