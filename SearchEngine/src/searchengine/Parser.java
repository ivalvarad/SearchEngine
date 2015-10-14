/*
 * This class is on charge of manipulate stream from a file 
 */
package searchengine;

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
public class Parser {
    
    private String fileName;
    
    public Parser(String fileName){
        this.fileName = fileName;
    }
    
    public String getStrFile() throws FileNotFoundException{
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Iva\\Documents\\II-2015\\CI-2414 Recup\\SearchEngine\\"+fileName));
        String everything = "";
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            br.close();
            everything = sb.toString();
        } catch (IOException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.print(everything);
        return everything;
    }
    
}
