package FILOSOFI;

import java.util.concurrent.Semaphore;

public class Tavolo2Sem extends Tavolo{
    Semaphore[] semafori = new Semaphore[NF];
 
    public Tavolo2Sem(){
        super();
        for(int i = 0;i<NF;i++){
            semafori[i] = new Semaphore(1);
        }
    }
    
    @Override
    public void prendiForchette(int i) throws InterruptedException {
        semafori[i].acquire();
        semafori[destra(i)].acquire();
    }

    @Override
    public void lasciaForchette(int i) throws InterruptedException {
        semafori[destra(i)].release();
        semafori[i].release();
    }
}
