package VISUALIZZATORE;

import java.util.concurrent.Semaphore;

public class GestoreSem extends Gestore{
    private Semaphore ciSonoPostiVuoti;
    private Semaphore ciSonoElementi;
    private Semaphore mutex;
    
    public GestoreSem(int maxSize){
        super(maxSize);
        ciSonoPostiVuoti = new Semaphore(maxSize);
        ciSonoElementi = new Semaphore(0);
        mutex = new Semaphore(1);
    }

    @Override
    public void inserisci(String[] stringhe) {
        try {
            ciSonoPostiVuoti.acquire(stringhe.length);
            mutex.acquire();
            for(int i = 0;i<stringhe.length;i++){
                lista.addLast(stringhe[i]);
            }
            mutex.release();
            ciSonoElementi.release(stringhe.length);

        } catch (InterruptedException e) {
        }
    }

    @Override
    public String preleva(){
        String r=null;
        try{
            ciSonoElementi.acquire();
            mutex.acquire();
            r = lista.removeFirst();
            mutex.release();
            ciSonoPostiVuoti.release();
        }catch(InterruptedException e){}
        return r;
    }
    public static void main(String[]args){

    }
}
