import java.io.*;
import java.util.*;
import java.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author catalina
 */
public class Pb1 {
    static int[][] culori = new int[45][45];
    static int min = Integer.MAX_VALUE;
    static boolean valid = false;
    static int[] cop = new int[30];

    /* calculeaza costul partial pana la momentul k */
    static int sumP(int k, int [][]mat, int sol[]){
        int sum = 0;
        for(int j = 0 ;j < k ; j ++ ){
            for(int l = 0 ;l < j ;l ++){
                if(mat[j][l] == 1){
                    sum += culori[sol[j]][sol[l]];
                }
            }
        }
        return sum;     
    }
    
    /* functia de validare a uneu culori */
    static int valid(int k, int[][] mat, int c, int []sol) {
        for (int i = 0; i < k ; i++) {
            if (sol[i] == c && mat[k][i] == 1) {
                return 0;
            }
        }
        return 1;
    }
    
    /* functia de calcul recursiv a solutiei */
    static void back(int k, int colNo, int[][] mat,int sol[], int N) {
        int sum = sumP(k,mat,sol);
        if (k == N) {
            /* validare solutie doar daca suma este mai mica decat minimul 
               existent */
            if(sum < min){
                min = sum;
                /* se face o copie la solutia gasita */
                for (int i = 0; i < N; i++) {
                    cop[i] = sol[i];
                }
                /* daca se gaseste solutie */
                valid = true;           
            }
        } else {
            /* pentru fiecare culoare se vefirica daca este valida si se
               apeleaza recursiv functia */
            for (int i = 0; i < colNo; i++) {
                if (valid(k, mat,i,sol) == 1) {
                    sol[k] = i;
                    if( sumP(k, mat, sol) < min)
                        back(k + 1, colNo, mat,sol, N);
                    else
                         return;     
                }
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        BufferedReader f = new BufferedReader(new FileReader("kcol.in"));
        PrintWriter out  =  new PrintWriter("kcol.out","UTF-8");
        
        int N, M, C;
        int nod1, nod2, c1, c2, cost;
        
        String line = new String();
        String[] parsare = new String[4];
        
        /* citire N , M ,C */
        line = f.readLine();
        parsare = line.split(" ");

        N = Integer.parseInt(parsare[0]);
        M = Integer.parseInt(parsare[1]);
        C = Integer.parseInt(parsare[2]);
        
        /* declarare matrice de adiacenta */   
        int[][] muchi = new int[N][N];
        
        /* completare matrice de adiacenta */
        for (int i = 0; i < M; i++) {
            line = f.readLine();
            parsare = line.split(" ");
            nod1 = Integer.parseInt(parsare[0]);
            nod2 = Integer.parseInt(parsare[1]);
            muchi[nod1][nod2] = 1;
            muchi[nod2][nod1] = 1;
        }

        line = f.readLine();
        /* citire si completare matricea de culori si costuri */
        while ((line = f.readLine()) != null) {
            if (!line.isEmpty()) {
                parsare = line.split(" ");
                c1 = Integer.parseInt(parsare[0]);
                c2 = Integer.parseInt(parsare[1]);
                cost = Integer.parseInt(parsare[2]);
                culori[c1][c2] = cost;
                culori[c2][c1] = cost;
               
            }
        }

       int []sol = new int[N];
       back(0,C,muchi,sol, N);
       /* daca avem solutie valida se scrie in fisier */
        if ( valid){
             out.println(min);
             for (int i = 0 ;i < N; i ++){
                out.print(i);
                out.print(" ");
                out.println(cop[i]);
             }
             
        }
            else{
                out.println("-1");
            }
        out.close();
        f.close();  
    }
}
