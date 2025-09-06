package MURATORI;

import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class CasaSem extends Casa{

    public CasaSem(int N) {
        super(N);
        sM = new Semaphore(0);
        sC = new Semaphore(N);
        mutex = new Semaphore(1);
    }
    Semaphore mutex;
    Semaphore sM;
    Semaphore sC;

    @Override
    public boolean inizia(int t) throws InterruptedException {
        if(t==0){//Cemento
            sC.acquire();
            mutex.acquire();
            file[t][indice[t]]++;
            //System.out.println("Messi " + file[t][indice[t]] + " cemento in fila " +indice[t]);
            if(file[t][indice[t]]>=N){
                intDep[t]++;
                indice[t] = indice[t] + 2;
                sM.release(N);
            }
            mutex.release();
        }
        else{//MATTONE
            sM.acquire();
            mutex.acquire();
            file[t][indice[t]]++;
            //System.out.println("Messi " + file[t][indice[t]] + " mattoni in fila " +indice[t]);
            if(file[t][indice[t]]>=N){
                intDep[t]++;
                indice[t] = indice[t] + 2;
                sC.release(N);
            }
            mutex.release();
        }
        return !(indice[t]>=N);
    }

    @Override
    public void termina() throws InterruptedException {
        for(int i = 0;i<2;i++){
            if(intDep[1]>=N){
                System.out.println("La parete è stata completata");
                System.out.println(Arrays.toString(file));
                for(Thread m : muratori){
                    m.interrupt();
                }
            }
        }
    }
    public static void main(String[]args){
        Casa casa = new CasaSem(4);
        casa.test(2, 2);
    }
    
}
