/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dialog;

/**
 *
 * @author WeFree
 */
public class Paire {
    private String key;
    private int value;
    
    public Paire(String k, int v){
        key = k;
        value = v;
    }
    
    public String getKey(){
        return key;
    }
    
    
    public int getValue(){
        return value;
    }
}
