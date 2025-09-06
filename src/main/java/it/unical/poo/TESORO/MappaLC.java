package TESORO;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MappaLC extends Mappa{
    ReentrantLock[][] lock;
    ReentrantLock l;
    Condition[][] possoCercare;

    public MappaLC(int N, int M, int X, int Y, int C) {
        super(N, M, X, Y, C);
        lock = new ReentrantLock[N][M];
        possoCercare = new Condition[N][M];
        for(int i = 0;i<N;i++){
            for(int j = 0;j<M;j++){
                lock[i][j] = new ReentrantLock();
                possoCercare[i][j] = lock[i][j].newCondition();
            }
        }
    }

    @Override
    public boolean iniziaRicerca(int x, int y) throws InterruptedException {
        while(matrice[x][y]==-1){possoCercare[x][y].await();}
        lock[x][y].lock();
        matrice[x][y]=-1;
        return !statoGioco;
    }

    @Override
    public boolean fineRicerca(int x, int y) throws InterruptedException {
        if(matrice[x][y]==1){
            statoGioco = false;
            for(int i = 0;i<C;i++){
                cercatori[i].interrupt();
            }
        }else{//tesoro non trovato
            matrice[x][y]=0;
            possoCercare[x][y].signalAll();
            lock[x][y].unlock();
        }
        return !statoGioco;
    }
    public static void main(String[]args){
        Mappa m = new MappaSem(10, 10, 8, 8, 10);
        m.test();
    }
}
