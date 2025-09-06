package TESORO;

import java.util.Random;

public class Cercatore extends Thread {
    Mappa mappa;
    int id;
    Random r = new Random();
    private boolean[][] matricePersonale;
    private int celleVis;

    public Cercatore(Mappa m, int id) {
        this.mappa = m;
        this.id = id;
        matricePersonale = new boolean[m.N][m.M];
        for (int i = 0; i < m.N; i++) {
            for (int j = 0; j < m.M; j++) {
                matricePersonale[i][j] = false;
            }
        }
        celleVis = 0;
    }

    public void run() {
        try {
            while (mappa.statoGioco) {
                int x, y;
                do {
                    x = r.nextInt(mappa.N);
                    y = r.nextInt(mappa.M);
                } while (!visitaPer(x, y));
                mappa.iniziaRicerca(x, y);
                System.out.println("Il cercatore " + id + " sta cercando in " + x + "," + y + ". Celle visitate = "+celleVis);
                attendi(2, 5);
                celleVis++;
                mappa.fineRicerca(x, y);
                if (mappa.matrice[x][y] == 1) {
                    System.out.println("Il cercatore " + id + " ha vinto.");
                }

            }
        } catch (InterruptedException e) {
        }
    }

    public void attendi(int min, int max) throws InterruptedException {
        int x = r.nextInt(max - min + 1) + min;
        Thread.sleep(x * 100);
    }

    public boolean visitaPer(int x, int y) {
        if (matricePersonale[x][y]) {// ci sono già stato = -1
            return false;
        } else {
            matricePersonale[x][y] = true; // segno che ci sono passato
            return true; // avviso che è da controllare
        }
    }
}
