package Utilities;

import java.util.concurrent.Semaphore;

public class ContoCorrenteSem extends ContoCorrente{
    private Semaphore mutex = new Semaphore(1);
    public ContoCorrenteSem(int depositoIniziale){
        super(depositoIniziale);
    }
    public void deposita(int importo){
        try{
            mutex.acquire();
            deposito= deposito+importo;
            mutex.release();
        }catch(InterruptedException e){}
    }
    @Override
    public void preleva(int importo) {
        try{
            mutex.acquire();
            deposito= deposito-importo;
            mutex.release();
        }catch(InterruptedException e){}
    }
}
