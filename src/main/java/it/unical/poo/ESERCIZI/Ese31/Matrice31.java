package ESERCIZI.Ese31;
import java.util.concurrent.atomic.AtomicInteger;
public class Matrice31 {
    AtomicInteger[][] matrice;
    int N,M,X;

    public Matrice31(int N,int M,int X){
        this.N=N;
        this.M=M;
        this.X=X;
        matrice = new AtomicInteger[N][M];
        for(int i = 0;i<N;i++){
            for(int j = 0;j<M;j++){
                matrice[i][j]=new AtomicInteger();
            }
        }
    }

    public static void main(String[]args) throws InterruptedException{
        int N=7;
        int M=5;
        int X=1000;
        Matrice31 m = new Matrice31(N, M, X);
        m.esegui();
        stampaMatrice(m.getMatrice());
    }

    public void esegui() throws InterruptedException{
        TN[] righe = new TN[N];
        TM[] colonne = new TM[M];
        for(int i = 0;i<N;i++){
            righe[i]=new TN(this,i);
            righe[i].start();
        }
        for(int j = 0;j<M;j++){
            colonne[j]=new TM(this,j);
            colonne[j].start();
        }
        for(int i=0;i<N;i++){
            righe[i].join();
        }
        for(int j=0;j<M;j++){
            colonne[j].join();
        }
    }
    public void incrementa(int i,int j){
        matrice[i][j].addAndGet(1);
    }
    public void decrementa(int i,int j){
        matrice[i][j].addAndGet(-1);
    }
    public int getX(){return X;}
    public int getN(){return N;}
    public int getM(){return M;}
    
    public int[][] getMatrice(){
        int[][] r= new int[N][M];
        for(int i = 0;i<N;i++){
            for(int j = 0;j<M;j++){
                r[i][j]=matrice[i][j].get();
            }
        }
        return r;
    }
    public static void stampaMatrice(int[][]m){
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<m.length;i++){
            sb.append("[");
            for(int j=0;j<m[0].length;j++){
                sb.append(m[i][j]);
                if(j<m[0].length-1)
                sb.append(", ");
            }
            sb.append("]\n");
        }
        System.out.println(sb.toString());
    }
}

class TN extends Thread{

    int riga;
    Matrice31 matrice;
    
    public TN(Matrice31 m,int r){
        this.matrice=m;
        this.riga=riga;
    }
    @Override
    public void run() {
        for(int cont=0;cont<matrice.getX();cont++){
            for(int j=0;j<matrice.getM();j++){
                matrice.decrementa(riga, j);
            }
        }
    }

}

class TM extends Thread{
    Matrice31 matrice;
    int colonna;
    
    public TM(Matrice31 m,int c){
        this.matrice=m;
        this.colonna=c;
    }
    @Override
    public void run() {
        for(int cont=0;cont<matrice.getX();cont++){
            for(int j=0;j<matrice.getN();j++){
                matrice.decrementa(j, colonna);
            }
        }
    }

}
