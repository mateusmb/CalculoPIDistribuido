/**
 * Created by mateus on 7/13/15.
 */

import java.util.Scanner;

public class PiLeibnizGregory {

    public static void main (String args[]) {

        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Numero de threads: ");
            int num = Integer.parseInt(scanner.nextLine());

            int fatia = 100000000/num;
            Thread threads[] = new Thread[num];
            double parciais[] = new double[num];

            long timeBegin = System.currentTimeMillis();
            for (int i = 0; i<num; i++) {
                threads[i] = new TSomaParcial(i, i*fatia, (i*fatia)+(fatia-1), parciais);
                threads[i].start();
            }

            for (int i = 0; i<num; i++) {
                threads[i].join();
            }

            double pi = 0;

            for (int i = 0; i<num; i++) {
                pi += parciais[i];
            }

            pi *= 4;
            long timeEnd = System.currentTimeMillis();
            System.out.println("O valor aproximado do PI eh: " + pi);
            System.out.println("O processamento durou " + (timeEnd-timeBegin) + " milisegundos");

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}


class TSomaParcial extends Thread {

    int id;
    long inicio, fim;
    double soma;
    double parciais[];

    public TSomaParcial(int tid, long from, long to, double parciais[]) {

        this.id = tid;
        this.inicio = from;
        this.fim = to;
        this.parciais = parciais;
        soma = 0;
    }

    public void run() {

        for (long n = inicio; n < fim; n++) {
            soma += (Math.pow((-1L), n)) / (2 * n + 1);
        }

        parciais[id] = soma;
    }
}