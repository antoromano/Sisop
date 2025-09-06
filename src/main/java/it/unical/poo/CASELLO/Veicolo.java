package CASELLO;

import java.util.Random;

public class Veicolo extends Thread{
    private Casello casello;
    private int chilometri;
    private Random random = new Random();
    private int scelta;
    public Veicolo(Casello c){
        this.casello = c;
        chilometri = random.nextInt(50)+50;
        scelta = random.nextInt(c.getN());
    }

    public int getK(){return chilometri;}


    @Override
    public void run(){
        try {
            casello.accedi(scelta);
            System.out.println("ACCESSO AL CASELLO :" + scelta);
            Thread.sleep(random.nextInt(3)+3);
            casello.pagaEAbbandona(scelta, chilometri);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}
