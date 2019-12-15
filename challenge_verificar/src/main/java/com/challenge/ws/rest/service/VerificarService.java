package com.challenge.ws.rest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import com.challenge.ws.rest.code.Codigo;

@Path("/verificaciones")
public class VerificarService {

	@POST  //para invocar el metodo
	@Path("/verificarCodigo")
	@Consumes({MediaType.APPLICATION_JSON}) //recibe
	@Produces({MediaType.APPLICATION_JSON}) //produce
	
    public static Codigo verificar(Codigo code){
        int verificadorOriginal, verificadorCalculado;
        String codigoRegion;
        
        verificadorOriginal = Integer.parseInt(getVerificador(code.getCodigo()));
        codigoRegion = getCodigoNumerico(code.getCodigo());
        verificadorCalculado = calcularVerificador(codigoRegion);
        code.setVerificadorValido((verificadorOriginal == verificadorCalculado));
        
        return code;
    }
	
	/*Modulo que retorna el digito verificador como caracter*/
    private static String getVerificador(String codigo){
        String verificador;
        int ubicacion;
        
        ubicacion = codigo.lastIndexOf("-") + 1;
        verificador = codigo.substring(ubicacion);
        
        return verificador;
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
    
    /*Modulo que retorna el digito verificador calculado a partir de el codigo de region*/
    private static int calcularVerificador(String codigoReg){
        int indice = 0, sumatoria = 0;
       
        for(indice = 0; indice < codigoReg.length(); indice++)
            sumatoria = sumatoria + Integer.parseInt(codigoReg.substring(indice, indice+1));
        
        if(sumatoria > 9)
            sumatoria = calcularVerificador(String.valueOf(sumatoria));
        
        return sumatoria;
    }
}
