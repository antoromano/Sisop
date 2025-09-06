package CASELLO;

import java.util.concurrent.Semaphore;

public class CaselloS extends Casello{

    protected Semaphore[] semafori;
    protected Semaphore mutex;

    public CaselloS(int N,int T) {
        super(N,T);
        semafori = new Semaphore[N];
        mutex = new Semaphore(1);
        for(int i = 0;i<N;i++){
            semafori[i] = new Semaphore(1);
        }
    }
    @Override
    public void accedi(int porta) throws InterruptedException{
        semafori[porta].acquire();
    }
    @Override
    public void pagaEAbbandona(int porta,int km) throws InterruptedException{
        mutex.acquire();
        incasso = incasso + (km*T);
        semafori[porta].release();
        mutex.release();
    }
    public static void main(String[]args) throws InterruptedException{
        Casello casello =  new CaselloS(5, 2);
        casello.esegui(80);
    }
}
