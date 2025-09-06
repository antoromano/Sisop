package PALLACANESTRO;

public abstract class Partita {

    public abstract boolean riceviPalla(int s) throws InterruptedException;
    public abstract boolean lanciaPalla(int s,int p) throws InterruptedException;
    public abstract int[] termina()throws InterruptedException;

    protected int passaggiFatti; //variabile condivisa
    protected int[] punteggio;
    protected int NG;
    protected boolean statoPartita;
    protected Giocatore[][] gioc;
    protected int squadraPoss;


    public Partita(){
        this.NG = 5;
        this.passaggiFatti = 0;
        this.punteggio = new int[2];
        this.statoPartita = true;
        squadraPoss = 0;
    }

    public void test(){
        Arbitro a = new Arbitro(this, "Arbitro");
        gioc = new Giocatore[2][NG];
        a.start();
        for(int i = 0;i<2;i++){
            for(int j = 0;j<NG;j++){
                gioc[i][j] = new Giocatore(this, i, j);
                System.out.println("Giocatore S " + i + j + " Creato");
                gioc[i][j].start();
            }
        }
    }
}
