/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchengine;

import java.util.ArrayList;

/**
 *
 * @author Iva
 */
public class SearchEngine {
    Index myIndex = new Index(5);
    QueryProcessor myQP = new QueryProcessor(myIndex);
    
    /*
       The Interface is going to call this method once the search button is clicked, so here
       is where all the things are done.
       Then, the interface is going to take the ArrayList this method returns and is going to show
       the output based on that. 
    */
    
    //receive the path of the file where the data for the index is stored at
    //fill the index
    public ArrayList<String> processQuery(String query){
        //parse the query
        //actually myQP.processQuery(query); would be the last step, this is just an example.
        return myQP.processQuery(query);
    }
    
}
