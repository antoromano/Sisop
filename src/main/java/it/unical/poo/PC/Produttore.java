package PC;

import java.util.Random;

public class Produttore implements Runnable{
    private static final int MAX_RANDOM = 10;
    private Random random = new Random();
    private Buffer buffer;

    public Produttore(Buffer b){this.buffer = b;}

    public void run(){
        try{
            while(true){
                int i = produciRandom();
                buffer.put(i);
            }
        }catch(InterruptedException e){}
    }
    private int produciRandom() throws InterruptedException{
        return random.nextInt(MAX_RANDOM);
    }
}
