/*
 * This class is in charge of processing the query from the Interface and returning
 * the results found.
 */
package searchengine;

/**
 *
 * @author Iva
 */

public class QueryProcessor {
    
    //receives a string with the search query from the user
    //in some way, it has to fix the query (ignore stop words and that kind of stuff)
    //then it has to process the query and look in the Index for the resulting docs
    //then it has to return the results of the query to the controller
    
    private Index index;
    public QueryProcessor(Index index){
        this.index = index; 
    }
    
    
}
