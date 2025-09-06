package FILOSOFI;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TavoloLC extends Tavolo{

    ReentrantLock lock = new ReentrantLock();
    Condition[] file = new Condition[NF];
    public TavoloLC(){
        for(int i = 0;i<NF;i++){
            file[i] = lock.newCondition();
            forchette[i] = 2;
        }
    }
    @Override
    public void prendiForchette(int i) throws InterruptedException {
        lock.lock();
        try{
            while(forchette[i]!=2){file[i].await();}
            forchette[sinistra(i)]--;
            forchette[destra(i)]--;
        }finally{lock.unlock();}
    }

    @Override
    public void lasciaForchette(int i) throws InterruptedException {
        lock.lock();
        try{
            forchette[sinistra(i)]++;
            forchette[destra(i)]++;
            if(forchette[sinistra(i)]==2){file[sinistra(i)].signal();}
            if(forchette[destra(i)]==2){file[destra(i)].signal();}
        }finally{lock.unlock();}
    }
    
}
