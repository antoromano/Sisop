package CASELLO;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CaselloLC extends Casello{

    private Lock lock = new ReentrantLock();
    private Condition[] portaC;
    private LinkedList<Thread>[] fila;
    
    @SuppressWarnings("unchecked")
    public CaselloLC(int N,int T) {
        super(N,T);
        portaC = new Condition[N];
        fila = new LinkedList[N];
        for(int i = 0;i<N;i++){
            portaC[i]=lock.newCondition();
            fila[i]=new LinkedList<Thread>();
        }
    }

    @Override
    public void accedi(int porta) throws InterruptedException {
        Thread t = Thread.currentThread();
        lock.lock();
        try{
            fila[porta].add(t);
            while(fila[porta].getFirst()==t){
                portaC[porta].await();
            }
        }finally{lock.unlock();}
    }
    

    @Override
    public void pagaEAbbandona(int porta, int km) throws InterruptedException {
        Thread t = Thread.currentThread();
        lock.lock();
        try{
            incasso = incasso + (km*T);
            fila[porta].remove(t);
            if(!fila[porta].isEmpty()){
                portaC[porta].signalAll();
            }
        }finally{lock.unlock();}
    }

    public static void main(String[]args) throws InterruptedException{
        Casello casello =  new CaselloS(5, 2);
        casello.esegui(80);
    }
}
