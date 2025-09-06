package PC;

import java.util.concurrent.TimeUnit;

public class Consumatore implements Runnable{
    private Buffer buffer;

    public Consumatore(Buffer b){this.buffer=b;}
    public void run(){
        try{
        while(true){
            int i=buffer.get();
            consuma(i);
        }
    }catch(InterruptedException e){}
    }
    private void consuma(int i) throws InterruptedException{
        TimeUnit.SECONDS.sleep(i);
    }
}
