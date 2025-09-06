package MURATORI;

public abstract class Casa {
    protected final int N;
    protected int[][] file;
    protected int[] indice = {0,1};
    protected int[] intDep = {0,0};
    protected Thread[] muratori;

    public Casa(int N){
        this.N = N;
        file = new int[2][N]; //riga 0 per cemento , riga 1 per mattoni
    }
    public abstract boolean inizia(int t) throws InterruptedException;
    // 0 per il Cemento , 1 per i Mattoni
    public abstract void termina() throws InterruptedException;
    public void test(int M,int C){
        muratori = new Muratore[M+C];
        for(int i = 0;i<C;i++){
            muratori[i] = new Muratore(this,0,String.valueOf(i));
            muratori[i].start();
        }
        for(int i = C;i<(M+C);i++){
            muratori[i] = new Muratore(this,1,String.valueOf(i));
            muratori[i].start();
        }
    }
}
