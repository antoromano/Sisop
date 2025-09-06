package LS;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Lettore implements Runnable{
    private MemoriaCondivisa memoria;
    public Lettore(MemoriaCondivisa mem){
        this.memoria = mem;
    }
    public void run(){
        try{
            while(true){
                memoria.inizioLettura();
                leggi();
                memoria.fineLettura();
                faiAltro();
            }
        }catch(InterruptedException e){}
    }
    private final static int MINLETTURA = 1;
    private final static int MAXLETTURA = 4;
    private final static int MINALTRO = 6;
    private final static int MAXALTRO = 10;

    private Random random =  new Random();
    private void leggi()throws InterruptedException{
        attendi(MINLETTURA,MAXLETTURA);
    }
    private void faiAltro()throws InterruptedException{
        attendi(MINALTRO,MAXALTRO);
    }
    private void attendi(int min,int max)throws InterruptedException{
        TimeUnit.SECONDS.sleep(random.nextInt(max-min+1)+min);
    }

}
