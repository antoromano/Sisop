package LS;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class MemoriaCondivisaLC extends MemoriaCondivisa{
    private Lock l = new ReentrantLock();
    private Condition possoLeggere = l.newCondition();
    private Condition possoScrivere = l.newCondition();
    private int lettoriInLettura = 0;
    private boolean scrittoreInScrittura = false;


    public void inizioScrittura() throws InterruptedException{
        l.lock();
        try{
            while(lettoriInLettura>0 && scrittoreInScrittura){
                possoScrivere.await();
            }
            scrittoreInScrittura = true;
        }finally{l.unlock();}
    }
    public void fineScrittura() throws InterruptedException{
        l.lock();
        try{
            possoLeggere.signalAll();
            possoScrivere.signal();
            scrittoreInScrittura = false;
        }finally{l.unlock();}
    }
    public  void inizioLettura() throws InterruptedException{
        l.lock();
        try{
            while(scrittoreInScrittura){
                possoLeggere.await();
            }
            lettoriInLettura++;
        }finally{l.unlock();}
    }
    public  void fineLettura() throws InterruptedException{
        l.lock();
        try{
            lettoriInLettura--;
            if(lettoriInLettura==0){
                possoScrivere.signal();
            }
        }finally{l.unlock();}
    }
}
