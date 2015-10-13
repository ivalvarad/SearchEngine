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
public class IndexEntry {
    String term;
    int frequency;
    ArrayList<Integer> postingsList;
    
    public IndexEntry(String term){
        this.term = term;
        this.frequency = -1;
        this.postingsList = new ArrayList<Integer>();
    }
    
    public IndexEntry(){
        this.term = "";
        this.frequency = -1;
        this.postingsList = new ArrayList<Integer>();
    }
    
    public void setTerm(String term){
        this.term = term;
    }
    
    public String getTerm(){
        return this.term;
    }
    
    public void addDocument(int docID){
        postingsList.add(docID);
    }
    
    public int getDocument(int index){
        return postingsList.get(index);
    }
    
    public void incrementFrecuency(){
        frequency++;
    }
    
    public int getFrecuency(){
        return frequency;
    }
    
    public ArrayList getPostingsList(){
        return postingsList;
    }
    
    public String toString(){
        String result = "";
        result += "Term: "+term+", Frequency: "+frequency+", Documents: ";
        if(postingsList.size()>0){
            result += postingsList.get(0);
        }
        if(postingsList.size()>1){
            for(int i = 1; i < postingsList.size(); i++){
                result += " | "+ postingsList.get(i);
            }
        }
        return result;
    }
    
}
