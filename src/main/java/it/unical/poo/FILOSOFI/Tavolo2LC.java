package FILOSOFI;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Tavolo2LC extends Tavolo{

    ReentrantLock lock = new ReentrantLock();
    Condition[] cond = new Condition[NF];
    public Tavolo2LC(){
        super();
        for(int i = 0;i<NF;i++){
            cond[i] = lock.newCondition();
        }
    } 
    @Override
    public void prendiForchette(int i) throws InterruptedException {
        lock.lock();
        try{
            while(forchette[i]<2){
                cond[i].await();
            }
            forchette[i]--;
            forchette[destra(i)]--;
        }finally{lock.unlock();}
    }

    @Override
    public void lasciaForchette(int i) throws InterruptedException {
        lock.lock();
        try{
            forchette[i]++;
            forchette[destra(i)]++;
            if(forchette[sinistra(i)]==2){cond[sinistra(i)].signal();}
            if(forchette[destra(i)]==2){cond[destra(i)].signal();}
        }finally{lock.unlock();}
    }
    
}
