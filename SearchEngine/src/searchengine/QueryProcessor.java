/*
 * This class is in charge of processing the query from the Interface and returning
 * the results found.
 */
package searchengine;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
	private ArrayList<String> stopwords;
    
    public QueryProcessor(Index index){
        this.index = index; 
    }
    
    //receives String with the query
    //returns an ArrayList with the id of the documents found
    public ArrayList<String> processQuery(String query)
	{
        ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> queryWords = new ArrayList<String>();
		// ignore white-spaces and all that stuff.
		queryWords = separateWords(query);
		// eliminates the stop-words.
		queryWords = processWords(queryWords);
        result.add("Hola");
        return result;
    }
	
	// generates an array of words according to a query that was originally a simple string of chars.
	public ArrayList<String> separateWords(String query)
	{
		ArrayList<String> result = new ArrayList<String>();	
		char c = '';
		string aux = "";
		int pos1 = 0;
		int pos2;
		for(int i = 0; i < query.lenght(); ++i)
		{
			c = query.charAt(i);
			// white spaces are ignored.
			if(isWhitespace(c) == true)
			{
				// generates a substring.
				pos2 = i-1;
				aux = query.substring(pos1, pos2);
				// saves the sub-string.
				result.add(aux);
				// ignores the white-spaces between words.
				c = query.charAt(i+1);
				while(isWhitespace(c) == true)
				{
					++i;
				}
				pos1 = i+1;
			}			
		}
		return result;		
	}
	
	// checks if the word has to be ignored or not.
	// none stop-word will be processed.
	// could generate an empty array of words for being processed as a query.
	public ArrayList<String> processWords(ArrayList<String> queryWords)
	{
		ArrayList<String> result = new ArrayList<String>();
		for(int i = 0; i < queryWords.getLength(); ++i)
		{
			boolean includeW = true;
			for(int j = 0; i < stopwords.getLength() && includeW != false; ++j)
			{
				if(queryWords[i].compareToIgnoreCase(stopwords[j]) == true)
				{
					// this word in the query will be not taken into account.
					includeW = false;					
				}
			}
			if(includeW == true)
			{
				result.add(queryWords[i]);				
			}
		}
		return result;		
	}

	// processes a file with the stop-words
	// and loads them to memory.
	// stop-words are supposed to be one in each line of the file.
	public void processStopWords(String path) throws FileNotFoundException
	{
		BufferedReader br = new BufferedReader(new FileReader(path));
        try {
            String line = br.readLine();
            while (line != null) {
                // obtains the whole line and saves it in the list of stop-words.
				line = br.readLine();
				stopwords.add(line);				
            }
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(QueryProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
}
