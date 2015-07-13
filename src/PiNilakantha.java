import java.util.Scanner;

/**
 * Created by mateus on 7/13/15.
 */
public class PiNilakantha {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Número de Threads: ");
            int num = Integer.parseInt(scanner.nextLine());

            int fatia = 100000000/num;
            Thread threads[] = new Thread[num];
            double[] parciais = new double[num];


            long timeBegin = System.currentTimeMillis();
            for(int i = 0; i < num; i++) {
                threads[i] = new TSomaNilakanthaParcial(i, i*fatia,(i*fatia)+(fatia-1), parciais);
                threads[i].start();
            }

            for(int i = 0; i < num; i++) {
                threads[i].join();
            }

            double pi = 0;

            for(int i = 0; i < num; i++) {
                pi += parciais[i];
            }

            pi += 3;
            long timeEnd = System.currentTimeMillis();
            System.out.println("Valor do Pi Nilakantha: " + pi);
            System.out.println("O processamento durou: " + (timeEnd-timeBegin) + " milisegundos");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class TSomaNilakanthaParcial extends Thread {
    int id;
    long inicio, fim;
    double soma;
    double parciais[];

    public TSomaNilakanthaParcial(int tid, long from, long to, double parciais[]) {
        this.id = tid;
        this.inicio = from;
        this.fim = to;
        this.parciais = parciais;
        soma = 0;
    }

    public void run() {
        for(long n = inicio; n < fim; n++) {
            soma += ((Math.pow(-1,n))*4)/((2*n+2)*(2*n+3)*(2*n+4));
        }

        parciais[id] = soma;
    }
}
