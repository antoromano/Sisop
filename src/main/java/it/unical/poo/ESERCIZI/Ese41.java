package ESERCIZI;

import java.util.concurrent.Semaphore;

public class Ese41 {
    Semaphore mutex;

    public Ese41() {
        mutex = new Semaphore(0);
    }

    public void esegui() {
        p1 processo1 = new p1(mutex);
        p2 processo2 = new p2(mutex);
        processo1.start();
        processo2.start();
    }

    public static void main(String[] args) {
        Ese41 e = new Ese41();
        e.esegui();
    }
}

class p1 extends Thread {
    Semaphore mutex;

    public p1(Semaphore m) {
        this.mutex = m;
    }

    public void run() {

        System.out.println("B");
        mutex.release();
    }
}

class p2 extends Thread {
    Semaphore mutex;

    public p2(Semaphore m) {
        this.mutex = m;
    }

    public void run() {
        try {
            mutex.acquire();
            System.out.println("A");

        } catch (InterruptedException e) {
        }
    }
}
