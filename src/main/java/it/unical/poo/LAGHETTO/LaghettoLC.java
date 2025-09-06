package LAGHETTO;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LaghettoLC extends Laghetto {

    public LaghettoLC(int P, int N) {
        super(P, N);
    }

    ReentrantLock lock = new ReentrantLock();
    Condition pesca = lock.newCondition();
    Condition ripopola = lock.newCondition();

    @Override
    public void inizia(int t) throws InterruptedException {
        lock.lock();
        try {
            if (t == 0) {// PESCATORE
                while(nPesci<=minPesci){
                    pesca.await();
                }
                nPesci--;

            } else {// ADDETTO
                while(nPesci > minPesci){
                    ripopola.await();
                }
                nPesci = nPesci + 10;
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void finisci(int t) throws InterruptedException {
        if (t == 0) {// PESCATORE
            if (nPesci <= minPesci) {
                ripopola.signalAll();
            }
        } else {// ADDETTO
            if (nPesci > minPesci) {
                pesca.signalAll();
            }
        }
        System.out.println("Ci sono " + nPesci + " pesci.");
    }

    public static void main(String[] args) {
        LaghettoSem l = new LaghettoSem(40, 5);
        l.test();
    }

}
