package Project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class SortingThreads implements Runnable{
   int a;
    public SortingThreads(int a) {
        this.a=a;
    }

    @Override
    public void run() {
        for (int i=a;i<a+Main.rowsPerThread;i++) {
            Arrays.sort(Main.tab[i]);
        }
    }
}

public class Main {
    static int[][] tab =tabRandom();
    static int rowsPerThread =0;

    static int[][] tabRandom() {
        Random random = new Random();
        int[][] table = new int[16][1000];
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                table[i][j] = random.nextInt(1000001);
            }
        }
        return table;
    }

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("ile wątków? ");
        int numberOfThreads = scanner.nextInt();
        scanner.close();
        rowsPerThread =16/numberOfThreads;

        ArrayList<Thread> threads =new ArrayList<>();
        int q=0;
        long mili=System.currentTimeMillis();
        for (int i=0;i<numberOfThreads;i++) {
            threads.add(new Thread(new SortingThreads(q)));
            threads.get(i).start();
            q+=rowsPerThread;

        }
        int a=0;
        while (threads.get(0).isAlive()) a++;
        System.out.println(a);

        long executionTime = System.currentTimeMillis() - mili;
        System.out.println(executionTime);
    }
}
