package PALLACANESTRO;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PartitaLC extends Partita{

    ReentrantLock lock = new ReentrantLock();
    Condition possoLanciare = lock.newCondition();
    Condition possoRicevere = lock.newCondition();
    @Override
    public boolean riceviPalla(int s) throws InterruptedException {
        
        return statoPartita;
    }

    @Override
    public boolean lanciaPalla(int s, int p) throws InterruptedException {
        
        return statoPartita;
    }

    @Override
    public int[] termina() throws InterruptedException {
        for(int i = 0;i<2;i++){
            for(int j = 0;j<NG;j++){
                gioc[i][j].interrupt();
            }
        }
        statoPartita = false;
        return punteggio;
    }
    
}
