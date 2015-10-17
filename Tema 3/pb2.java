import java.io.*;
import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author catalina
 */
public class pb2 {

    /**
     * @param args the command line arguments
     */
    public static int nrPrieteni(int N, int[][] graph, int A, int B) {
        int inc = 0;
        /* parcurgerea tuturor persoanelor */
        for (int i = 0; i < N; i++) {
            if (graph[A][i] == 1) {
                /* pentru fiecare persoana se parcurg prietenii de 
                   ordinul 2 */
                for (int j = 0; j < N; j++) {
                  /* daca a ajuns la B se sterge legatura si se incrementeaza */
                    if (graph[i][j] == 1) {
                        if (j == B) {
                            graph[i][j] = 0;
                            inc++;
                        }
                        /* se parcurg prietenii de ordinul 3 */
                        for (int k = 0; k < N; k++) {
                            if (graph[j][k] == 1) {
                                /* daca s-a gasit B se sterge legatura si se
                                incrementeaza valoarea */
                                if (k == B) {
                                    graph[j][k] = 0;
                                    inc++;
                                }
                            }
                        }
                    }
                }
            }
        }
        return inc;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner f = new Scanner(new FileReader("prieteni.in"));
        PrintWriter out = new PrintWriter("prieteni.out");
        
        int N, A, B, pA, pB, min;

        /* citire N */
        N = f.nextInt();
        /* declarare matrice de adiacenta */
        int graph[][] = new int[N][N];
        /* citire matrice de adiacenta */
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                graph[i][j] = f.nextInt();
            }
        }

        /* citire A si B */
        A = f.nextInt();
        B = f.nextInt();

        /* se apeleaza functia atat din A cat si din B */
        pA = nrPrieteni(N, graph, A, B);
        pB = nrPrieteni(N, graph, B, A);
        /* calcularea valorii minime dintre cele 2 numere */
        min = Math.min(pA, pB);
        /* afisarea in fisier */
        out.write(String.valueOf(min) + "\n");

        out.close();
        f.close();
    }
}
