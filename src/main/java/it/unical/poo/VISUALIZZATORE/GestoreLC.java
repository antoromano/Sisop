package VISUALIZZATORE;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GestoreLC extends Gestore{

    Lock l;
    Condition codaPiena;
    Condition codaVuota;
    int N;

    public GestoreLC(int maxSize) {
        super(maxSize);
        l= new ReentrantLock();
        codaPiena = l.newCondition();
        codaVuota = l.newCondition();
        this.N=0;
    }

    @Override
    public void inserisci(String[] x) throws InterruptedException {
        l.lock();
        try{
            while(N+x.length>=maxSize){
                codaPiena.await();
            }
            for(int i = 0;i<x.length;i++){
                lista.addLast(x[i]);
            }
            N = N + x.length;
            codaVuota.signal();
        }finally{l.unlock();}
    }

    @Override
    public String preleva() throws InterruptedException {
        String r = null;
        l.lock();
        try{
            while(N<=0){
                codaVuota.await();
            }
            r = lista.removeFirst();
            N--;
            codaPiena.signal();
        }finally{l.unlock();}
        return r;
    }
    
}
