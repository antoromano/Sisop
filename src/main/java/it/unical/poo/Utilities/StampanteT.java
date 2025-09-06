package Utilities;

public class StampanteT extends Thread{
    private int da;
    private int a;
    public StampanteT(int da,int a){
        this.da=da;
        this.a=a;
    }
    public void run(){
        for(int i = da;i<=a;i++){
            System.out.println(i+" ");
        }
        System.out.println();
    }
}
class StampanteTTest{
    public static void main(String[]args){
        StampanteT s1 = new StampanteT(1,10);
        StampanteT s2 = new StampanteT(11,20);
        s1.start();
        s2.start();
        System.out.println("Fine");
    }
}

