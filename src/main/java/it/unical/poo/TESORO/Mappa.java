package TESORO;

import java.util.Random;

public abstract class Mappa {
    public abstract boolean iniziaRicerca(int x,int y) throws InterruptedException;
    public abstract boolean fineRicerca(int x,int y)throws InterruptedException;
    
    public Mappa(int N,int M,int X,int Y,int C){
        this.N=N;
        this.M=M;
        this.X=X;
        this.Y=Y;
        this.C=C;
        matrice = new int[N][M];
        cercatori = new Cercatore[C];
        statoGioco = true;
        for(int i = 0;i<N;i++){
            for(int j = 0;j<M;j++){
                matrice[i][j] = 0;
            }
        }
        matrice[X][Y] = 1; //posiziono il tesoro
    }
    protected int[][] matrice; //true tesoro,false libera
    Random r = new Random();
    protected int N,M,X,Y,C;
    Cercatore[] cercatori;
    protected boolean statoGioco;

    public void test(){
        for(int i = 0;i<C;i++){
            cercatori[i] = new Cercatore(this, i);
            cercatori[i].start();
        }
    }
}
