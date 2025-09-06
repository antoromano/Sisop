package VISUALIZZATORE;

import java.util.Random;

public class Utente extends Thread{
    protected Gestore gestore;
    Random random = new Random();
    public Utente(Gestore g){
        this.gestore = g;
    }
    public void run(){
        try {
            int stringhe = random.nextInt(5)+1;
            String[] x = new String[stringhe];
            for(int i=0;i<stringhe;i++){
                x[i]= new String("S"+"_"+this.hashCode()+"_"+i);
            }
            gestore.inserisci(x);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
