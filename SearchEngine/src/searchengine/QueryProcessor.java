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

public class QueryProcessor 
{
    //receives a string with the search query from the user
    //in some way, it has to fix the query (ignore stop words and that kind of stuff)
    //then it has to process the query and look in the Index for the resulting docs
    //then it has to return the results of the query to the controller
    
    private Index index;
    private ArrayList<String> stopwords;
    
    public QueryProcessor(Index index)
    {
        this.index = index;
        stopwords = new ArrayList<>();
        try 
        {
            loadStopWords("..\\stopwords.txt");
        }
        catch (FileNotFoundException ex){}
    }
    
    // receives String with the query
    // returns an ArrayList with the id of the documents found
    public ArrayList<String> processQuery(String query)
    {
        // ArrayList with the id of the documents which match the boolean retrival.
        ArrayList<String> result = new ArrayList<>();
        ArrayList<String> queryWords = new ArrayList<>();
        String post;
        // ignore white-spaces and all that stuff.
	queryWords = separateWords(query);
	// eliminates the stop-words.
	queryWords = eliminateWords(queryWords);
        result = processWords(queryWords);
        return result;
    }
    
    // 
    public ArrayList<String> processWords(ArrayList<String> queryWords)
    {
        ArrayList<String> postListF = new ArrayList<>();
        ArrayList<String> postList = new ArrayList<>();
        IndexEntry entry;
        String aux;
        
        for(int i = 0; i < queryWords.size(); ++i)
        {
            // word to analize.
            aux = queryWords.get(i);
            // get the post-list for the word.
            entry = index.getEntry(aux);
            postList = entry.getPostingsList();                    
            // postlist is still empty.
            if(postListF.isEmpty())
            {
                postListF = postList;
            }
            else
            {
                postListF = setIntersection(postList, postListF);
            }
        }
        return postListF;
    }
    	
    // generates an array of words according to a query that was originally a simple string of chars.
    public ArrayList<String> separateWords(String query)
    {
        ArrayList<String> result = new ArrayList<>();	
	char c;
	String aux;
	int pos1 = 0;
	int pos2;
	for(int i = 0; i < query.length(); ++i)
	{
            c = query.charAt(i);
            // white spaces are ignored.
            if( ignoreChar(c) == true)
            {
                // generates a substring.
		pos2 = i-1;
		aux = query.substring(pos1, pos2);
		// saves the sub-string.
		result.add(aux);
		// ignores the white-spaces between words.
		c = query.charAt(i+1);
		while(ignoreChar(c) == true)
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
    public ArrayList<String> eliminateWords(ArrayList<String> queryWords)
    {
        ArrayList<String> result = new ArrayList<>();
        String aux;
        String stopword;
	for(int i = 0; i < queryWords.size(); ++i)
	{
            boolean includeW = true;
            aux = queryWords.get(i);
            for(int j = 0; i < stopwords.size() && includeW != false; ++j)
            {
                stopword = stopwords.get(j);
                // verifies if both string of characters are equal.
                if(aux.compareToIgnoreCase(stopword) == 0)
		{
                    // this word in the query will be not taken into account.
                    includeW = false;					
		}
            }
            if(includeW == true)
            {
                result.add(aux);				
            }
	}
	return result;		
    }

    // processes a file with the stop-words
    // and loads them to memory.
    // stop-words are supposed to be one in each line of the file.
    public final void loadStopWords(String path) throws FileNotFoundException
    {
        BufferedReader br = new BufferedReader(new FileReader(path));
        try
        {
            String line = br.readLine();
            while (line != null) 
            {
                // obtains the whole line and saves it in the list of stop-words.
		line = br.readLine();
		stopwords.add(line);				
            }
            br.close();
        } 
        catch (IOException ex)
        {
            Logger.getLogger(QueryProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // only alphanumeric substrings of the query will be taken into account.
    // verifies if the given char is alphanumeric or not.
    // false if it is alphanumeric, otherwise true.
    public boolean ignoreChar(char c)
    {
        boolean ans = false;
        // verifies if the character is alphanumeric or not.
        if(!Character.isDigit(c) && !Character.isLetter(c))
        {
            ans = true;
        }    
        return ans;
    }
    
    // intersection between two sets of words.
    // the structure of the set model was simulated as an array.
    public ArrayList<String> setIntersection(ArrayList<String> S1, ArrayList<String> S2)
    {
        ArrayList<String> ans = new ArrayList<>();
        String word1;
        String word2;
        for(int i = 0; i < S1.size(); ++i)
        {
            word1 = S1.get(i);
            for(int j = 0; j < S2.size(); ++j)
            {
                word2 = S2.get(j);
                // element is part of the sets intersection.
                if(word1.compareToIgnoreCase(word2) == 0)
                {
                    ans.add(word1);
                    // it is not needed to end the intern cycle.
                    break;
                }
            }
        }
        return ans;
    }
    
}