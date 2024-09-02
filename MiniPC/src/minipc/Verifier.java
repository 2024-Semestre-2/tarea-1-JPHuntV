/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



package minipc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

/**
 *
 * @author jeanp
 */
public class Verifier {
    static final String[] REGISTROSVALIDOS = {"AX", "BX", "CX", "DX"};
    static final String[] OPERADORESVALIDO = {"MOV", "LOAD", "ADD", "SUB", "STORE"}; 
    static Dictionary<String, String> binarios;
    static{
        binarios = new Hashtable<>();
        binarios.put("LOAD", "0001");
        binarios.put("STORE", "0010");
        binarios.put("MOV","0011");
        binarios.put("SUB","0100");
        binarios.put("ADD","0101");
        binarios.put("AX","0001");
        binarios.put("BX","0010");
        binarios.put("CX","0011");
        binarios.put("DX","0100");
    }
    /*
        valida que el archvo con la ruta señalada cumpla con las caracteristicas
        del lenguaje asm
    */
    public static ArrayList<String[]> validarArchivo(String nombre, MiniPC miniPC) throws IOException{
        int tamMemoria = miniPC.getTamMemoria();
        System.err.println(tamMemoria);
        System.err.println(nombre);
        ArrayList<String[]> lista = convertirArchivoLista(nombre);
        String mensaje = validarLista(lista, tamMemoria);
        boolean valido = mensaje == "Validación exitosa.";
        if(lista!= null && valido){
            int tamanioLista =  Arrays.asList(lista.toArray()).size();
            if(tamanioLista > tamMemoria){
                MiniPC.mostrarError("El archivo excede el tamaño de la memoria");
                return null;
            }
        }else{
            MiniPC.mostrarError(mensaje);
            return null;
        }
        return lista;
    }
    
    /*
        ValidarLista
        valida que todos los elementos de la lista de instrucciones
        pertenescan a la gramatica asm
    */
    public static String validarLista(ArrayList<String[]> lista, int tamMemoria) {
        int tamanioLista = lista.size();
        System.err.println(tamanioLista);
        if (tamanioLista > tamMemoria) return "Error: La cantidad de instrucciones excede el tamaño de la memoria.";
        if (tamanioLista < 1) return "Error: La cantidad de instrucciones es menor a 1.";
    
        for (String[] str : lista) {
            String instruccion = Arrays.toString(str);
            instruccion = instruccion.substring(1, instruccion.length() - 1);
            boolean operadorValido = Arrays.asList(OPERADORESVALIDO).contains(str[0]);
            boolean registroValido = Arrays.asList(REGISTROSVALIDOS).contains(str[1]);
            boolean esEntero = true;
    
            if (str.length == 3) {
                esEntero = esEntero(str[2]);
                if (!esEntero) return "Error: El valor debe ser un entero. \n" + instruccion;
                if (Integer.parseInt(str[2]) > 127) return "Error: El valor es mayor a 127. \n" +instruccion;
            }
    
            if (!operadorValido) return "Error: Operador inválido. \n" + instruccion;
            if (!registroValido) return "Error: Registro inválido. \n" + instruccion;
            if (!esEntero) return "Error: El tercer elemento no es un entero válido. \n" +instruccion;
        }
    
        return "Validación exitosa.";
    }
    
    /*
        esEntero
        Valida si un string contiene un numero
    */
    public static boolean esEntero(String strNum) {
    if (strNum == null) {
        return false;
    }
    try {
        int num = Integer.parseInt(strNum);
    } catch (NumberFormatException nfe) {
        return false;
    }
    return true;
}
    
    //lee el contenido del archivo y lo transforma a una lista de  de listas
    //con la siguiente estructura [[operador, registro, valor(opt)],...,...]
    public static ArrayList<String[]> convertirArchivoLista(String nombre) throws FileNotFoundException, IOException{
        List<String> lineasArchivo = new ArrayList<String>();  
        BufferedReader bf = new BufferedReader(new FileReader(nombre));
        String linea = bf.readLine();      
        while (linea != null) {
            if(!linea.equals("")){
                lineasArchivo.add(linea);//ignora lineas en blanco   
            }
            linea = bf.readLine();  
        }
        bf.close();       
        String[] array = lineasArchivo.toArray(new String[0]);
        ArrayList<String[]> lista = new ArrayList<String[]>();
        
        for (String str : array) {
            String[] instruccion = divideString(str);//agrega dicha lista a la lista de instrucciones
            if(instruccion == null) return null;
            String instruccionStr = Arrays.toString(instruccion);
            lista.add(instruccion);
        }
        if(lista.isEmpty()){
            return null;//la lista no puede ser vacia
        }
        return lista;
    
    }
    
    //divide cada una de las lineas en listas
    //por espacio y coma
    //retorna nulo sino 
    public static String[] divideString(String input) {
        String[] dividido = null;
        String[] lista = input.split(",");
        if (lista.length == 2) {
            dividido = new String[3];
            String valor = lista[1].trim();
            String operadorYRegistro = lista[0].trim();    
            String[] res = divideStringAux(operadorYRegistro);//dividir primer elemento
            if(res != null){
                dividido[0] = res[0].toUpperCase();
                dividido[1] = res[1].toUpperCase();
                dividido[2] = valor; // Value
            }else{
                dividido = null;
            }
        }
        else if(lista.length == 1){
            dividido = new String[2];
            String operadorYRegistro = lista[0].trim();          
            String[] res = divideStringAux(operadorYRegistro);//dividir primer elemento
            if(res != null){
                dividido[0] = res[0].toUpperCase();
                dividido[1] = res[1].toUpperCase();
            }else{
                dividido = null;
            }
        }
        else dividido = null;
        
        return dividido;
    }
    
    //divide el primer elemento que se obtuvo
    //este elemento contiene ambos el operador y el registro
    //se divide en el espacio
    public static String[] divideStringAux(String operadorYRegistro){
        String[] res = new String[2];
        String[] operadorYRegistroLista = operadorYRegistro.split("\\s+");
        if (operadorYRegistroLista.length == 2) {
            res[0] = operadorYRegistroLista[0]; // Operador
            res[1] = operadorYRegistroLista[1]; // registro
            return res;
        }
        return null;
    }
    
    
    /*
        escogerPosicion
        escoge una posicion aleatoria en memoria para ubicar las entradas
        -las primeras diez posiciones están reservadas
        -la posicion debe tener como minimo n posiciones libres despues del indice
        siendo n el largo del la lista de instrucciones
    */
    public static int escogerPosicion(int largo, int tamMemoriaOS, int tamMemoria){
        Random rand = new Random();
        int posicion = 0;
        posicion = rand.nextInt(tamMemoriaOS, tamMemoria-largo);
        return posicion;
    }
    
    /*
        TransformarBinario
        transforma la lista de instrucciones de ensamblador a binario
    */
    public static ArrayList<String[]> transformarBinario(ArrayList<String[]> instruccionesASM){
        ArrayList<String[]> listaBinario = new ArrayList<>();
        for(String[] str : instruccionesASM){
            String[] temp = new String[3];
            temp[0] = binarios.get(str[0].toUpperCase());//verifica que el registro pertenezca a la gramatica
            temp[1] = binarios.get(str[1].toUpperCase());
            if(str.length == 3){
                int numero = Integer.parseInt(str[2]);
                String numBin = numeroABinario(numero);
                temp[2] = numBin;
            }else{
                temp[2] = "00000000";
            }
            listaBinario.add(temp);
        }
        return listaBinario;
    }
    
    /*
        numeroABinario
        transforma de decimal a binario
    */
    public static String numeroABinario(int numero){
        String binario = Integer.toBinaryString(Math.abs(numero));
        for(int i = 7-binario.length(); i>0; i--){
            binario = "0" + binario;
        }
        if(numero<0){
            binario = "1" + binario;
        }else{
            binario = "0" + binario;
        }
          System.out.println(binario); 
        return binario;
    }
    
    /*
        getDecimal
        transforma de binario a decimal
    */
    public static int getDecimal(String valorBin) {
        int res = Integer.parseInt(valorBin.substring(1), 2);
        if (valorBin.charAt(0) == '1') {
            return res * -1;
        }
        return res;
    }
    
    
    
}
