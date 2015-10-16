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
public class Index {
    
    private int nEntries;
    private ArrayList<IndexEntry> table;
    
    public Index(int nEntries){
        this.nEntries = nEntries;
        this.table = new ArrayList<>();
        for(int i = 0; i < nEntries; i++){
            table.add(i,null);
        }
    }
    
    // applies the hash function to a term
    public int funHash(String term){
        char ch[] = term.toCharArray();
        int i, sum;
        for(sum=0, i=0; i < term.length(); i++){
          sum += ch[i];
        }
        return sum % nEntries;
    }
    
    //inserts a new IndexEntry in the Index (probably never gonna happen)
    public void insert(IndexEntry entry){
        int i = funHash(entry.getTerm());
        table.add(i, entry);
    }
    
    //inserts a new IndexEntry with term in the Index
    public void insert(String term){
        int i = funHash(term);
        table.add(i, new IndexEntry(term));
    }
    
    //returns the object IndexEntry with the term "term"
    public IndexEntry getEntry(String term){
        IndexEntry index = null;
        for(int i=0; i< nEntries; i++){
            if(table.get(i).getTerm().compareTo(term)==0){
                index = table.get(i);
            }
        }
        return index;
    }
    
    //return the index where the object IndexEntry with the term "term" is stored at
    public int getIndex(String term){
        int index = -1;
        for(int i=0; i < nEntries; i++){
            if(table.get(i).getTerm().compareTo(term)==0){
                index = i;
            }
        }
        return index;
    }
    
    //associated a docID with a term
    public void associate(String term, int docID){
        table.get(getIndex(term)).addDocument(docID);
    }
    
    //returns true if the docID is already associated to the term, false otherwise
    public boolean isAssociated(String term, int docID){
        boolean result = false;
        IndexEntry temp = table.get(getIndex(term));
        if(temp.hasDocument(docID)==true){
            result = true;
        }
        return result;
    }
    
    //returns a formatted String of this Index
    public String toString(){
        String result = "";
        for(int i=0; i < nEntries; i++){
            result += table.get(i).toString()+"\n";
        }
        return result; 
    }
}
