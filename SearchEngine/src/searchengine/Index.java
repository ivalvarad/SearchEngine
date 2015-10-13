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
    
    private Parser parser; 
    private int nEntries; 
    private ArrayList<IndexEntry> table;
    
    public Index(Parser parser, int nEntries){
        this.parser = parser;
        this.nEntries = nEntries;
        this.table = new ArrayList<IndexEntry>();
        /*for(int i = 0; i < nEntries; i++){
            table.add(i,new IndexEntry());
        }*/
    }
    
    public String toString(){
        String result = "";
        for(int i=0; i < nEntries; i++){
            result += table.get(i).toString()+"\n";
        }
        return result; 
    }
    
    //buscar otra función hash
    public int funHash(String k){
        return (k.length() % nEntries);
    }
    
    /*
    int sascii(String x, int M) {
        char ch[];
        ch = x.toCharArray();
        int xlength = x.length();

        int i, sum;
        for (sum=0, i=0; i < x.length(); i++)
          sum += ch[i];
        return sum % M;
     }*/
    
    public void insert(IndexEntry entry){
        int i = funHash(entry.getTerm());
        table.add(i, entry);
    }
    
    public IndexEntry search(String term){
        
        return null;
    }
    
    /*
        // Retorna un puntero a la llave o NULL si no se encuentra
	T* search(const T& item){
            T *itemPtr = NULL;
            int i = funHash(item);
            if(i>=0 && i<numEntradas){
                typename std::list<T>::iterator it;
                for(it = tabla[i].begin(); it != tabla[i].end(); ++it){
                    if(*it==item){
                        itemPtr = &*it;
                    }
                }
            }
            return itemPtr;
	}
    */
}
