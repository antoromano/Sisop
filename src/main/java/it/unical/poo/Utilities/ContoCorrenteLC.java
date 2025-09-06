package Utilities;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ContoCorrenteLC extends ContoCorrente{
    public ContoCorrenteLC(int D) {
        super(D);
    }
    private Lock l = new ReentrantLock();
    public void deposita(int importo){
        l.lock();
        try{
            deposito+=importo;
        }finally{l.unlock();}
    }
    public void preleva(int importo){
        l.lock();
        try{
            deposito-=importo;
        }finally{l.unlock();}
    }
}
