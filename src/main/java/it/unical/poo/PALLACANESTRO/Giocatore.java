package PALLACANESTRO;

import java.util.Random;

public class Giocatore extends Thread{
    Partita partita;
    private int squadra;
    private int id;
    Random r = new Random();
    protected boolean state;
    private int probabilità;

    public Giocatore(Partita p,int s,int id){
        this.partita = p;
        this.id = id;
        this.squadra = s;
        this.state = true;
        this.probabilità = r.nextInt(4)+3;
    }

    public void run(){
        try{
            while(state){
                partita.riceviPalla(squadra);
                System.out.println("Il giocatore " + id + " ,S " + squadra +" ha ricevuto la palla.");
                attendi(10, 50);
                partita.lanciaPalla(squadra, lancia());  
            }
        }catch(InterruptedException e){
            state=false;
        }
    }

    public void attendi(int min,int max) throws InterruptedException{
        int x = r.nextInt(max-min+1) + min;
        Thread.sleep(x*100);
    }
    public int lancia(){
        int s = r.nextInt(11);
        if(probabilità<s){return 0;}
        else{return 1;}
    }
}
