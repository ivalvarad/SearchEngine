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
    Index myIndex = new Index(3); //a esto hay que pasarle la cantidad de términos del diccionario
    Parser myParser = new Parser("C:\\Users\\Iva\\Documents\\II-2015\\CI-2414 Recup\\SearchEngine\\postings.txt");
    QueryProcessor myQP = new QueryProcessor(myIndex);
    
    /*
       The Interface is going to call this method once the search button is clicked, so here
       is where all the things are done.
       Then, the interface is going to take the ArrayList this method returns and is going to show
       the output based on that. 
    */

    public void buildIndex(){
        String postings = "";
        try{
            postings = myParser.getStrFile();
        }catch(FileNotFoundException ex){}
        String[] lines = postings.split("\n"); //array with a line in each field
        for(int i = 0; i < lines.length; i++){
            String line = lines[i];
            int blankIdx = line.indexOf(' '); //index of the first blank
            String term = line.substring(0, line.indexOf(' ')); //cut from beginning to the first blank
            IndexEntry newEntry = new IndexEntry(term); //create a new IndexEntry containing the term
            
            line = line.substring(blankIdx+1); //remove the term from the line
            blankIdx = line.indexOf(' '); //index of the first blank
            while(blankIdx!=-1){ //if there are blanks (means there are docID's)
                String strDocID = line.substring(0,blankIdx); 
                int docID = Integer.valueOf(strDocID); //get one of the DocID's
                newEntry.addDocument(docID); //add the new docID to the entry
                line = line.substring(blankIdx+1); //remove the docID from the line
                blankIdx = line.indexOf(' '); //recalculate the index of the first blank
            }
            myIndex.insert(newEntry);
        }
        System.out.print(myIndex.toString());
    }
    
    public ArrayList<String> processQuery(String query){
        try {
            //parse the query
            //actually myQP.processQuery(query); would be the last step, this is just an example.
            myParser.getStrFile();
        } catch (FileNotFoundException ex) {}
        buildIndex();
        return myQP.processQuery(query);
    }
    
}
