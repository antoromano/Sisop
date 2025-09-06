package PISCINA;

import java.util.Random;

public class Nuotatore extends Thread{
    protected Piscina piscina;
    private Random random;
    private String id;
    public Nuotatore(Piscina p,String id){
        this.piscina = p;
        random = new Random();
        this.id = id;
    }
    public void run(){
        try{
            int corsia = piscina.scegliCorsia();
            piscina.entraCorsia(corsia);
            nuota(corsia);
            piscina.lasciaCorsia(corsia);
            doccia(); 
        }catch(InterruptedException e){}

    }
    private void nuota(int c)throws InterruptedException{
        int x = random.nextInt(31)+30;
        x = x*10;
        System.out.println("Il nuotatore " + id + " ha nuotato. In corsia " + c);
        Thread.sleep(x);
    }
    private void doccia()throws InterruptedException{
        int x = 200;
        System.out.println("Il nuotatore " + id + " ha fatto la doccia e se ne va.");
        Thread.sleep(x);
    }

}
