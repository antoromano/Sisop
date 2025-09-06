package PALLACANESTRO;

import java.util.Arrays;

public class Arbitro extends Thread{
    Partita partita;
    String id;
    public Arbitro(Partita p,String id){
        this.partita = p;
        this.id = id;
    }

    public void run(){
        try{
                System.out.println("LA PARTITA INIZIA");
                Thread.sleep(40000);
                System.out.println("LA PARTITA FINISCE, ecco il risultato");
                System.out.println(Arrays.toString(partita.termina()));
        }catch(InterruptedException e){}
    }
}
