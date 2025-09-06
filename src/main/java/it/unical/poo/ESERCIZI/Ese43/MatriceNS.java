package ESERCIZI.Ese43;

public class MatriceNS {
    int[][] matrice;
    int N, M, X;

    public MatriceNS(int N, int M, int X) {
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
            Incrementatore in = new Incrementatore(this, i);
            in.start();
            in.join();
        }
        for (int i = 0; i < M; i++) {
            Decrementatore dec = new Decrementatore(this, i);
            dec.start();
            dec.join();
        }
    }

    private class Incrementatore extends Thread {
        MatriceNS m;
        int riga;

        public Incrementatore(MatriceNS m, int r) {
            this.riga = r;
            this.m = m;
        }

        public void run() {
            for (int cont = 0; cont < m.getX(); cont++) {
                for (int i = 0; i < m.getM(); i++) {
                    m.incrementa(riga, i);
                }
            }
        }
    }

    private class Decrementatore extends Thread {
        MatriceNS m;
        int colonna;

        public Decrementatore(MatriceNS m, int c) {
            this.colonna = c;
            this.m = m;
        }

        public void run() {
            for (int cont = 0; cont < m.getX(); cont++) {
                for (int i = 0; i < m.getN(); i++) {
                    m.decrementa(i, colonna);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MatriceNS m = new MatriceNS(20, 5, 100);
        m.stampaMatrice();
        m.esegui();
        m.stampaMatrice();
    }

}
