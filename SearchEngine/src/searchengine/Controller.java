/*
 * The purpose of this class is to control the other ones and organize things in order to them
 * to work properly.
 */
package searchengine;

/**
 *
 * @author Iva
 */
public class Controller {
    Interface myInterface = new Interface();
    Parser myParser = new Parser();
    Index myIndex = new Index(myParser);
    QueryProcessor myQP = new QueryProcessor(myIndex);
    
    public Controller(){
        
    }
    
}
