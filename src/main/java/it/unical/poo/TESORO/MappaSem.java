package TESORO;

import java.util.concurrent.Semaphore;

public class MappaSem extends Mappa{

    public MappaSem(int N, int M, int X, int Y, int C) {
        super(N, M, X, Y, C);
        caselle = new Semaphore[N][M];
        for(int i = 0;i<N;i++){
            for(int j = 0;j<M;j++){
                caselle[i][j] = new Semaphore(1);
            }
        }
    }
    Semaphore[][] caselle; 

    @Override
    public boolean iniziaRicerca(int x, int y)throws InterruptedException {
        caselle[x][y].acquire();
        return !statoGioco;
    }

    @Override
    public boolean fineRicerca(int x, int y) {
        if(matrice[x][y]==1){
            statoGioco=false;
            for(int i = 0;i<C;i++){
                cercatori[i].interrupt();
            }
        }else{
            caselle[x][y].release();
        }
        return !statoGioco;
    }

    public static void main(String[]args){
        Mappa m = new MappaSem(10, 10, 8, 8, 10);
        m.test();
    }
    
}
