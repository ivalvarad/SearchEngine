/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchengine;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Iva
 */
public class SearchEngine {
    private Index myIndex; //a esto hay que pasarle la cantidad de términos del diccionario
    private Parser myParser;
    private QueryProcessor myQP;
    
     /*
       The Interface is going to call this method once the search button is clicked, so here
       is where all the things are done.
       Then, the interface is going to take the ArrayList this method returns and is going to show
       the output based on that. 
    */
    
    public SearchEngine(){
        myParser = new Parser("..\\postings.txt");
        try {
            myIndex = new Index(myParser.getNumberLines()); //a esto hay que pasarle la cantidad de términos del diccionario
        } catch (FileNotFoundException ex) {}
        myQP = new QueryProcessor(myIndex);
    }
    
    //builds the index
    public void buildIndex(){
        String postings = "";
        try{
            postings = myParser.getStrFile();
        }catch(FileNotFoundException ex){System.out.println("ERROR: failed to convert the file.");}
        String lines[] = postings.split("\n"); //array with a line in each field
        for(int i = 0; i < lines.length; ++i){
            String line = lines[i];
            int blankIdx = line.indexOf(" "); //index of the first blank
            String term = new String (line.substring(0, line.indexOf(' '))); //cut from beginning to the first blank
            IndexEntry newEntry = new IndexEntry(term); //create a new IndexEntry containing the term
            
            line = line.substring(blankIdx+1); //remove the term from the line
            
            blankIdx = line.indexOf(" "); //index of the first blank after the first docID
            
            do{ //if there are blanks (means there are docID's)
                String docID = "";
                if(blankIdx!=-1){
                    docID = new String(line.substring(0,blankIdx)); 
                    line = line.substring(blankIdx+1); //remove the docID from the line
                    blankIdx = line.indexOf(" "); //recalculate the index of the first blank
                }
                if(blankIdx == -1){
                    docID = new String(line);
                }
                newEntry.addDocument(docID);
            }while(blankIdx!=-1);
            myIndex.insert(newEntry);
        }
        System.out.println(myIndex.toString());
    }
    
    public ArrayList<String> processQuery(String query){
        buildIndex();
        return myQP.processQuery(query);
    }
    
}
