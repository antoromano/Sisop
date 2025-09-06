package AZIENDAAGRICOLA;

import java.util.Random;

public class Cliente extends Thread{
    Azienda azienda;
    Random random = new Random();
    private String id;
    public Cliente(Azienda a,String id){
        this.azienda = a;
        this.id = id;
    }
    public void run(){
        try{
            int x = random.nextInt(10)+1;
            azienda.paga(x);
            System.out.println("Il cliente "+ id + " ha comprato " + x + " sacchi.");
            for(int i = 0;i<x;i++){
                azienda.ritira();
                Thread.sleep(1000);
            }
            System.out.println("Il cliente "+ id + " ha finito di ritirare i sacchi. ");
        }catch(InterruptedException e){}
    }
}