package CLINICA;

import java.util.Random;

public class Dottore extends Thread{
    public Dottore(Clinica c,int id){
        this.id=id;
        this.clinica=c;
    }
    Clinica clinica;
    int id;
    Random r = new Random();
    public void run(){
        try{
            while(true){
                clinica.chiamaEIniziaOperazione();
                System.out.println("Il medico inizia l'operazione.");
                attendi(15,30);
                clinica.operazioneFinita();
                System.out.println("Il medico ha finito l'operazione.");
                Thread.sleep(10*60*15);
            }
        }catch(InterruptedException e){}
    }
    public void attendi(int min,int max)throws InterruptedException{
        int x = r.nextInt(max-min+1)+min;
        Thread.sleep(10*60*x);
    }
}
