package ESERCIZI.Ese43;

import java.util.concurrent.Semaphore;

public class Matrice{

    int[][] matrice;
    int N, M, X;
    public Matrice(int N, int M, int X) {
        this.N = N;
        this.M = M;
        this.X = X;
        matrice = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                matrice[i][j] = 0;
            }
        }
    }
    public int getN() {
        return N;
    }

    public int getM() {
        return M;
    }

    public int getX() {
        return X;
    }

    public int[][] getMatrice() {
        int[][] r = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                r[i][j] = matrice[i][j];
            }
        }
        return r;
    }

    public void stampaMatrice() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append("[");
            for (int j = 0; j < M; j++) {
                sb.append(matrice[i][j] + ",");
            }
            sb.append("]\n");
        }
        System.out.println(sb.toString());
    }

    public void incrementa(int i, int j) {
        matrice[i][j]++;
    }

    public void decrementa(int i, int j) {
        matrice[i][j]--;
    }

    public void esegui() throws InterruptedException {
        for (int i = 0; i < N; i++) {
            Incrementatore in = new Incrementatore();
            Semaphore s = new Semaphore(N);
            
        }
        for (int i = 0; i < M; i++) {
            Semaphore s = new Semaphore(M);
            
        }

    }

    private class Incrementatore extends Thread{

        public Incrementatore(){

        }
        public void run(){

        }
    }

    private class Decrementatore extends Thread{
        public Decrementatore(){

        }
        public void run(){

        }
    }


    public static void main(String[] args) throws InterruptedException {
        Matrice m = new Matrice(5, 5, 10);
        m.stampaMatrice();
        m.esegui();
        m.stampaMatrice();
    }

}
