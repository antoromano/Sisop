package PC;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class BufferSem extends Buffer {

    // variabili interne e definizione dei semafori
    private Semaphore ciSonoElementi = new Semaphore(0);
    private Semaphore ciSonoPostiVuoti;     
    private Semaphore mutex = new Semaphore(1); //nei casi non-limite entrambi i tipi di thread possono modificare il buffer

    // COSTRUTTORE
    public BufferSem(int dimensione) {
        super(dimensione);
        ciSonoPostiVuoti = new Semaphore(dimensione);
    }

    private class Produttore implements Runnable{
        private static final int MAX_RANDOM = 10;
        private Random random = new Random();
        private Buffer buffer;
        public Produttore(Buffer b){this.buffer = b;}
        public void run(){
            try{
                while(true){
                    int i = produciRandom();
                    buffer.put(i);
                }
            }catch(InterruptedException e){}
        }
        private int produciRandom() throws InterruptedException{
            return random.nextInt(MAX_RANDOM);
        }
    }
    public void put(int i) throws InterruptedException {
        ciSonoPostiVuoti.acquire();
        mutex.acquire();
        buffer[in]=i;
        in = (in + 1)%buffer.length; // incremento la posizione, quando arriviamo alla fine del buffer bisogna tornare a 0;
        mutex.release();
        ciSonoElementi.release();
    }
    private class Consumatore implements Runnable{
        private Buffer buffer;
    
        public Consumatore(Buffer b){this.buffer=b;}
        public void run(){
            try{
            while(true){
                int i=buffer.get();
                consuma(i);
            }
        }catch(InterruptedException e){}
        }
        private void consuma(int i) throws InterruptedException{
            TimeUnit.SECONDS.sleep(i);
        }
    }
    public int get() throws InterruptedException {
        ciSonoElementi.acquire();
        mutex.acquire();
        int i = buffer[out];
        out=(out+1)%buffer.length; // incremento la posizione, quando arriviamo alla fine del buffer bisogna tornare a 0;
        mutex.release();
        ciSonoPostiVuoti.release();
        return i;
    }
    public static void main(String[]args){
        int dimensione = 10;
        Buffer buffer = new BufferSem(dimensione);
        int numProduttori=10;
        int numConsumatori=10;
        buffer.test(numProduttori, numConsumatori);
    }
}
