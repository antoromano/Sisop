package ESERCIZI.Ese31;

public class MatriceNTS {
    private int N, M, X;
    private int[][] matrice;

    public MatriceNTS(int N, int M, int X) {
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
            TDecr td = new TDecr(this, i);
            td.start();
            td.join();
        }
        for (int i = 0; i < M; i++) {
            TIncr ti = new TIncr(this, i);
            ti.start();
            ti.join();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MatriceNTS m = new MatriceNTS(40, 40, 100);
        m.stampaMatrice();
        m.esegui();
        m.stampaMatrice();

    }
}
