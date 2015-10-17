import java.io.*;
import java.util.*;

public class Pb2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        BufferedReader f = new BufferedReader(new FileReader("expozitie.in"), 1000000);
        PrintWriter out = new PrintWriter("expozitie.out", "UTF-8");

        String line = new String();
        String[] parsare = new String[4];

        int N;
        /* citire N */
        line = f.readLine();
        N = Integer.parseInt(line);

        /* declarare coada de prioritari formata din perechi<nod,no_of vecini> */
        int vector[] = new int[N];
        PriorityQueue<Pair> que = new PriorityQueue(N, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.getValue() - o2.getValue();
            }
        });

        int node, leg;
        int[][] matrice = new int[N][N];
        /* se actualizeaza matricea cu nodurile citite si se completeaza
         vectorul cu numarul de vecini gasiti*/
        while ((line = f.readLine()) != null) {
            parsare = line.split(" ");
            node = Integer.parseInt(parsare[0]);
            leg = Integer.parseInt(parsare[1]);
            /* calculez cate legeturi catre vecini sunt */
            vector[node]++;
            vector[leg]++;

            matrice[node][leg] = 1;
            matrice[leg][node] = 1;
        }
        /* se pune in coada cu prioritati primele perechi */
        for (int i = 0; i < N; i++) {
            que.add(new Pair(i, vector[i]));
        }

        boolean[] check = new boolean[N];
        int[] outV = new int[N];

        int[][] print = new int[N][N];

        while (!que.isEmpty()) {
            Pair cop = que.poll();
            if (check[cop.getKey()] == false) {
                if ((cop.getValue() + outV[cop.getKey()]) % 2 == 0) {
                    /* pentru fiecare vecin se actualizeaza datele */
                    for (int i = 0; i < N; i++) {
                        if (matrice[cop.getKey()][i] == 1) {
                            /* se actualizeaza numarul de vecini si se sterge
                             legatura din matrice */
                            vector[i]--;
                            matrice[i][cop.getKey()] = 0;

                            print[cop.getKey()][i] = 1;
                            outV[cop.getKey()]++;
                            /* se adauga in coada nodul dar cu numarul de venici
                             actualizat */
                            que.add(new Pair(i, vector[i]));
                        }
                    }
                } else {
                    int count = 0;
                    /* se pun N-1 arce care ies din nod */
                    for (int i = 0; i < N; i++) {
                        if (matrice[cop.getKey()][i] == 1) {
                            count++;
                            if (count <= vector[cop.getKey()] - 1) {
                                vector[i]--;
                                matrice[i][cop.getKey()] = 0;
                                /* se adauga in coada nodul dar cu numarul de venici
                                 actualizat */
                                que.add(new Pair(i, vector[i]));

                                outV[cop.getKey()]++;
                                print[cop.getKey()][i] = 1;
                            } else {
                                /* ultimul nod se orienteaza inspre el */
                                matrice[i][cop.getKey()] = 0;
                                vector[i]--;

                                que.add(new Pair(i, vector[i]));
                                outV[i]++;
                                print[i][cop.getKey()] = 1;
                            }
                        }
                    }
                }
                check[cop.getKey()] = true;
            }
        }
        /* verificare daca graful se poate orienta corect */
        int valid = 0;
        for (int i = 0; i < N; i++) {
            if (vector[i] % 2 != 0) {
                valid = 1;
            }
        }
        if (valid == 1) {
            /* afisare in fisier */
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (print[i][j] == 1) {
                        out.print(i);
                        out.print(" ");
                        out.println(j);
                    }
                }
            }
        } else {
            out.println("-1");
        }
        out.close();
        f.close();
    }

}
