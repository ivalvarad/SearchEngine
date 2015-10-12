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
    Parser myParser = new Parser();
    Index myIndex = new Index(myParser);
    QueryProcessor myQP = new QueryProcessor(myIndex);
    
    public ArrayList<String> processQuery(String query){
        return myQP.processQuery(query);
    }
    
}
