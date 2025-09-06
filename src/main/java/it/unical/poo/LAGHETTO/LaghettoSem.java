package LAGHETTO;

import java.util.concurrent.Semaphore;

public class LaghettoSem extends Laghetto{

    public LaghettoSem(int P, int N) {
        super(P, N);
    }
    Semaphore mutex = new Semaphore(1);
    Semaphore semPescatore=new Semaphore(0);
    Semaphore semAddetto=new Semaphore(1);

    @Override
    public void inizia(int t) throws InterruptedException {
        if(t == 0){//PESCATORE
            semPescatore.acquire();
            mutex.acquire();
            nPesci--;
            mutex.release();
        }else{//ADDETTO
            semAddetto.acquire();
            mutex.acquire();
            nPesci = nPesci + 10;
            mutex.release();
        }
    }

    @Override
    public void finisci(int t) throws InterruptedException {
        if(t == 0){//PESCATORE
            if(nPesci<=minPesci){
                semAddetto.release();
            }else{
                semPescatore.release();
            }
        }else{//ADDETTO
            if(nPesci> minPesci){
                semPescatore.release();
            }else{
                
                semAddetto.release();
            }
        }
        System.out.println("Ci sono " + nPesci + " pesci.");
    }
    
    public static void main(String[]args){
        LaghettoSem l = new LaghettoSem(40, 5);
        l.test();
    }
}
