package PISCINA;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PiscinaLC extends Piscina {

    public PiscinaLC(int n) {
        super(n);
        for(int i = 0;i<n;i++){
            file[i] = new LinkedList<>();
            corsiaOccupata[i] = lock.newCondition();
        }
    }
    @SuppressWarnings("unchecked")
    LinkedList<Thread>[] file = new LinkedList[numCorsie];
    ReentrantLock lock = new ReentrantLock();
    Condition[] corsiaOccupata = new Condition[numCorsie];

    @Override
    public int scegliCorsia() throws InterruptedException {
        lock.lock();
        try {
            int min = personeCorsie[0];
            int corsiaMin = 0;
            boolean uguali = true;
            for (int i = 0; i < numCorsie; i++) {
                if (personeCorsie[i] < min) {
                    min = personeCorsie[i];
                    corsiaMin = i;
                    uguali = false;
                }
            }
            if (uguali) {
                int r = random.nextInt(5);
                return r;
            } else {
                return corsiaMin;
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void entraCorsia(int corsia) throws InterruptedException {
        lock.lock();
        try{
            file[corsia].add(Thread.currentThread());
            while(personeCorsie[corsia]>=4){
                corsiaOccupata[corsia].await();
            }
            file[corsia].remove(Thread.currentThread());
            personeCorsie[corsia]++;
        }finally{lock.unlock();}
    }

    @Override
    public void lasciaCorsia(int corsia) throws InterruptedException {
        lock.lock();
        try{
            personeCorsie[corsia]--;
            corsiaOccupata[corsia].signal();
        }finally{lock.unlock();}
    }

    @Override
    public void ACPiscina(boolean stato) throws InterruptedException {
        if(stato){//sta aprendo
            lock.lock();
            try{
            System.out.println("La piscina Apre");
            nuo = new Nuotatore[numNuotatori];
            for(int i = 0;i<numNuotatori;i++){
                nuo[i] = new Nuotatore(this, String.valueOf(i));
                nuo[i].start();
            }
        }finally{lock.unlock();}
        }
        else{//sta chiudendo
            System.out.println("La piscina Chiude");
            for(int i = 0;i<numNuotatori;i++){
                nuo[i].interrupt();
            }
            for(int i = 0;i<numCorsie;i++){
                personeCorsie[i]=0;
                file[i] = new LinkedList<Thread>();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        PiscinaSem piscina = new PiscinaSem(5);
        piscina.test(100);
    }

}
