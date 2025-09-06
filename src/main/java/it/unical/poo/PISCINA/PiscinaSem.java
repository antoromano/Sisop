package PISCINA;
import java.util.concurrent.Semaphore;

public class PiscinaSem extends Piscina{

    public PiscinaSem(int n) {
        super(n);
        for(int i = 0;i<numCorsie;i++){
            corsie[i] = new Semaphore(4);
        }
    }
    Semaphore[] corsie = new Semaphore[numCorsie];
    Semaphore mutex = new Semaphore(1);

    @Override
    public int scegliCorsia() throws InterruptedException {
        mutex.acquire();
        int min = personeCorsie[0];
        int corsiaMin = 0;
        boolean uguali = true;
        for(int i = 0;i<numCorsie;i++){
            if(personeCorsie[i]<min){
                min = personeCorsie[i];
                corsiaMin = i;
                uguali = false;
            }
        }
        if(uguali){
            int r = random.nextInt(5);
            mutex.release();
            return r;
        }
        else{
            mutex.release();
            return corsiaMin;
        }
    }

    @Override
    public void entraCorsia(int corsia) throws InterruptedException {
        corsie[corsia].acquire();
        mutex.acquire();
        personeCorsie[corsia]++;
        mutex.release();
    }

    @Override
    public void lasciaCorsia(int corsia) throws InterruptedException {
        mutex.acquire();
        personeCorsie[corsia]--;
        mutex.release();
        corsie[corsia].release();
    }

    @Override
    public void ACPiscina(boolean stato) throws InterruptedException {
        if(stato){//sta aprendo
            System.out.println("La piscina Apre");
            mutex.acquire();
            nuo = new Nuotatore[numNuotatori];
            for(int i = 0;i<numNuotatori;i++){
                nuo[i] = new Nuotatore(this, String.valueOf(i));
                nuo[i].start();
            }
            mutex.release();
        }
        else{//sta chiudendo
            System.out.println("La piscina Chiude");
            mutex.acquire();
            for(int i = 0;i<numNuotatori;i++){
                nuo[i].interrupt();
            }
            for(int i = 0;i<numCorsie;i++){
                personeCorsie[i]=0;
                corsie[i] = new Semaphore(4);
            }
            mutex.release();
        }
    }

    public static void main(String[]args) throws InterruptedException{
        PiscinaSem piscina = new PiscinaSem(5);
        piscina.test(100);
    }
    
}
