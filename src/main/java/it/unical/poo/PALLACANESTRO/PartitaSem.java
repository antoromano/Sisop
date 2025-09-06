package PALLACANESTRO;

import java.util.concurrent.Semaphore;

public class PartitaSem extends Partita{

    Semaphore mutex = new Semaphore(1);
    Semaphore[] passaggi = new Semaphore[2];

    public PartitaSem() {
        super();
        passaggi[0] = new Semaphore(1);
        passaggi[1] = new Semaphore(0);
    }

    @Override
    public boolean riceviPalla(int s) throws InterruptedException {
        passaggi[s].acquire();
        mutex.acquire();
        return statoPartita;
    }

    @Override
    public boolean lanciaPalla(int s, int p) throws InterruptedException {
        if(passaggiFatti<3){//passala
            if(p==0){//passaggio non riuscito
                passaggiFatti=0;
                passaggi[(-1*s)+1].release();
                System.out.println("La squadra " + s + " perde la palla");
            }
            else{//passaggio riuscito
                passaggiFatti++;
                passaggi[s].release();
                System.out.println("La squadra " + s + " fa un passaggio");
            }
        }
        else{//tira
            if(p==0){//tiro non riuscito
                System.out.println("La squadra "+ s + " sbaglia il tiro");
                passaggiFatti=0;
                passaggi[(-1*s)+1].release();
            }else{//tiro riuscito
                System.out.println("La squadra "+ s + " segna");
                punteggio[s]++;
                passaggiFatti=0;
                passaggi[s].release();
            }
        }
        mutex.release();
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
    
    public static void main(String[]args){
        Partita p = new PartitaSem();
        p.test();
    }
}
