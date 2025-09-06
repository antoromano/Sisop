package FILOSOFI;

import java.util.concurrent.Semaphore;

public class TavoloSem extends Tavolo {

    Semaphore[] semafori;
    Semaphore mutex;

    public TavoloSem() {
        semafori = new Semaphore[NF];
        mutex = new Semaphore(1);
        for (int i = 0; i < NF; i++) {
            semafori[i] = new Semaphore(1);
        }
    }

    @Override
    public void prendiForchette(int i) throws InterruptedException {
        semafori[i].acquire();
        semafori[(i + 1) % 5].acquire();
    }

    @Override
    public void lasciaForchette(int i) throws InterruptedException {
        semafori[i].release();
        semafori[(i + 1) % 5].release();
    }

    public void test() {
        for (int i = 0; i < NF; i++) {
            Filosofo f = new Filosofo(this, i);
            f.start();
        }
    }

    public static void main(String[] args) {
        TavoloSem t = new TavoloSem();
        t.test();
    }

}
