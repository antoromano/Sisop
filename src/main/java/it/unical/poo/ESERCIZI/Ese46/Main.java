package ESERCIZI.Ese46;

import java.util.concurrent.Semaphore;

public class Main {

    private static Semaphore sa = new Semaphore(2);
    private static Semaphore sb = new Semaphore(0);
    private static int cont = 1;

    public static void main(String[] args) {
        while (true) {
            new A().start();
            new B().start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }

    private static class A extends Thread {
        public void run() {
            try {
                sa.acquire();
                System.out.print(" A ");
                if(cont== 0){
                    sb.release();
                }
                cont--;
            } catch (InterruptedException e) {
            }
        }
    }

    private static class B extends Thread {
        public void run() {
            try {
                sb.acquire();
                System.out.print(" B ");
                cont = 1;
                sa.release();
                sa.release();

            } catch (InterruptedException e) {
            }
        }
    }

    // sa =  2 1 0 
    // sb = -1 0 1 0
    // co = 
    // ou =  A A B A
}
