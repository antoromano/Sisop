package ESERCIZI.Ese31;

public class TDecr extends Thread{
    private MatriceNTS m;
    private int i;
    private int c;

    public TDecr(MatriceNTS m,int i){
        this.m=m;
        this.i=i;
        this.c= m.getM();
    }
    @Override
    public void run(){
        for(int cont = 0;cont<m.getX();cont++){
        for(int j = 0;j<c;j++){
            m.decrementa(i, j);
        }
    }
    }
}
