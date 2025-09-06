package ESERCIZI.Ese44;

import java.util.concurrent.Semaphore;

public class Main {
    private static Semaphore sa = new Semaphore(1);
    private static Semaphore sb = new Semaphore(0);
    private static Semaphore sc = new Semaphore(0);
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new A().start();
            new B().start();
            new C().start();
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){}
        }
    }

    private static class A extends Thread {
        public void run() {
            try{
            sa.acquire();
            System.out.print(" A ");
            sb.release();
            }catch(InterruptedException e){}
        }
    }

    private static class B extends Thread {
        public void run() {
            try {
                sb.acquire();
                System.out.print(" B ");
                sc.release();
            } catch (InterruptedException e) {}
        }
    }

    private static class C extends Thread{
        public void run(){
            try{
                sc.acquire();
                System.out.println(" C ");
                sa.release();
            }catch(InterruptedException e){}
        }
    }
}
