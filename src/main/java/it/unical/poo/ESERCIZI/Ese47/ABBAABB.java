package ESERCIZI.Ese47;

import java.util.concurrent.Semaphore;

public class ABBAABB {
        private static Semaphore sa = new Semaphore(1);
        private static Semaphore sb = new Semaphore(0);
        private static int cont = 1;
        private static int i = 1;
    
        public static void main(String[] args) {
            while (true) {
                new A().start();
                new B().start();
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
                        cont = i + 1;
                        i++;
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
                    sa.release(cont);
                } catch (InterruptedException e) {
                }
            }
        }
    
        // sa =
        // sb =
        // co =
        // ou =
}
