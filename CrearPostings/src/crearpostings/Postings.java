/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crearpostings;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author JN1
 */
public class Postings {
    private String[] diccionario;
    private String[] postings;
    
    public Postings(String ruta){
        File archivo = new File(ruta+"diccionario.txt");
        String linea = "";
        String temp = "";
        try{
          Scanner entrada = new Scanner(archivo);
          do{
             linea = entrada.nextLine();
             temp += linea + " ";
          }while(entrada.hasNextLine());
          entrada.close();
        }catch(Exception e){}
        diccionario = temp.split(" ");
        postings = new String[diccionario.length];
        for(int i = 0; i < postings.length; ++i){
            postings[i] = "";
        }
    }
    
    public void modificarArchivo(File archivo){
       String linea = "";
       String[] temp;
       try{
          Scanner entrada = new Scanner(archivo);
          do{
             linea = entrada.nextLine();
             agregarPosting(linea, archivo.getName());
          }while(entrada.hasNextLine());
          entrada.close();
       }catch(Exception e){}
    }
    
    private void agregarPosting(String palabra, String id){
        //compara la palabra con el diccionario para agregarla a la lista de postings
        for(int i = 0; i < diccionario.length; ++i){
            if(palabra.contentEquals(diccionario[i])){
                String[] temp = postings[i].split(" ");
                if(!id.contentEquals(temp[temp.length-1])){
                    postings[i] += id+" ";
                }
            }
        }
    }
    
    public void escribirArchivo(File archivo){
       try{
          PrintWriter escritura = new PrintWriter(archivo);
          //escribir lista de postings
          for(int i= 0; i < diccionario.length; ++i){
              escritura.println(diccionario[i]+ " " + postings[i]);
          }
          escritura.close();
       }catch(Exception e){}
    }
}
