package VISUALIZZATORE;

import java.util.LinkedList;

public abstract class Gestore {
    protected LinkedList<String> lista;
    protected int maxSize;

    public Gestore(int maxSize){
       lista = new LinkedList<String>();
       this.maxSize=maxSize;
    }

    public abstract void inserisci(String[] x)throws InterruptedException;

    public abstract String preleva()throws InterruptedException;

    public void esegui()throws InterruptedException{
        Visualizzatore visualizzatore = new Visualizzatore(this);
        Utente[] utenti = new Utente[10];
        for(int i = 0;i<10;i++){
            utenti[i] = new Utente(this);
        }
        visualizzatore.start();
        for(int i = 0;i<10;i++){
            utenti[i].start();
        }
        visualizzatore.join();
        for(int i = 0;i<10;i++){
            utenti[i].join();
        }
    }

}
