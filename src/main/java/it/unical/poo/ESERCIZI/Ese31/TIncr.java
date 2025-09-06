package ESERCIZI.Ese31;

public class TIncr extends Thread{
    private MatriceNTS m;
    private int i;
    private int r;

    public TIncr(MatriceNTS m,int i){
        this.m=m;
        this.i=i;
        this.r= m.getN();
    }
    @Override
    public void run(){
        for(int cont = 0;cont<m.getX();cont++){
        for(int j = 0;j<r;j++){
            m.incrementa(j, i);
        }
    }
    }
}
