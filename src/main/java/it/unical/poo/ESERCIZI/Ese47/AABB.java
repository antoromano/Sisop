package ESERCIZI.Ese47;

import java.util.concurrent.Semaphore;

public class AABB {
    private static Semaphore sa = new Semaphore(2);
    private static Semaphore sb = new Semaphore(0);
    private static int cont = 2;

    public static void main(String[] args) {
        while (true) {
            new A().start();
            new A().start();
            new B().start();
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
                cont--;
                if (cont == 0) {
                    sb.release(2);
                }
            } catch (InterruptedException e) {
            }
        }
    }

    private static class B extends Thread {
        public void run() {
            try {
                sb.acquire();
                System.out.print(" B ");
                cont++;
                if (cont == 2) {
                    sa.release(2);
                }
            } catch (InterruptedException e) {
            }
        }
    }

    // sa =
    // sb =
    // co =
    // ou =
}
