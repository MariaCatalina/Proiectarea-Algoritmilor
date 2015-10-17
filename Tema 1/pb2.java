import java.io.*;
import java.lang.reflect.Array;
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

    public static int search(long K, int[] vector, int lower, int upper) {
        long mic = 0, mare = 0, index = 0;
        int middle = 0, dif = 0;

        if (lower > upper) {
            return upper;
        } else if (lower == upper || lower == (upper + 1)) {
            return upper;
        } else if (lower < upper) {
            middle = lower + (upper - lower) / 2;
            if (middle == K) {
                return middle;
            } else {
                /* calculul diferentelor pentru fiecare element din vector */
                for (int i = 0; i < vector.length; i++) {
                    dif = (int) (middle - vector[i]);
                    if (dif > 0) {
                        /* apelare funcitie de cautare binara pentru a determina
                         pozitia la care se afla elementul cautat */
                        index = Arrays.binarySearch(vector, dif);
                        if (index >= 0) {
                            mic += index;
                            mare += index + 1;
                        }
                        if (index < 0) {
                            mic += (~index);
                            mare += (~index);
                        }
                    }
                }
                /* in functie de sumele calculate se apeleaza recursiv */
                if (mic <= mare) {
                    if (K > mic && K <= mare) {
                        return middle;
                    } else if (K <= mic) {
                        middle = search(K, vector, lower, middle - 1);
                    } else if (K >= mare) {
                        middle = search(K, vector, middle + 1, upper);
                    }
                } else {
                    return -1;
                }
            }
        }
        return middle;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        BufferedReader f = new BufferedReader(new FileReader("patrat.in"));
        BufferedWriter out = new BufferedWriter(new FileWriter("patrat.out"));

        String line = new String();
        String[] parsare = new String[2];

        int N, i = 0;
        long K;
        line = f.readLine();
        parsare = line.split(" ");

        /* Citire valori N si K */
        N = Integer.parseInt(parsare[0]);
        K = Long.parseLong(parsare[1]);

        int[] vector = new int[N];

        /* citire numere fisier */
        while ((line = f.readLine()) != null) {
            vector[i++] = Integer.parseInt(line);
        }
        Arrays.sort(vector);

        int l = search(K, vector, 2 * vector[0], 2 * vector[N - 1]);
        out.write(Integer.toString(l));
        out.newLine();
        out.close();
        f.close();

    }

}
