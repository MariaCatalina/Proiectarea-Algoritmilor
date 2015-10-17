/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author catalina
 */
public class Pair {
    private int  key,value;
    
    public Pair(int key, int value){
        this.key = key;
        this.value = value;
    }
    /* get prima valoare din pereche */
    public int getKey(){
        return key;
    }
		/* get a doau valoare din pereche */
    public int getValue(){
        return value;
    }
}
