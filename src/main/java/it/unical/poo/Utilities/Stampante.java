package Utilities;

public class Stampante {
        private int da;
        private int a;
    
        public Stampante(int da,int a){
            this.da=da;
            this.a=a;
        }
        public void print(){
            for(int i = da;i<=a;i++){
                System.out.println(i+" ");
            }
            System.out.println();
        }
    }
    
class StampanteTest{
        public static void main(String[]args){
            Thread t = Thread.currentThread();
            System.out.println(t.getId()+" "+t.getName());
            Stampante s1 = new Stampante(1,10);
            s1.print();
            Thread t2 = Thread.currentThread();
            System.out.println(t2.getId()+" "+t2.getName());
            Stampante s2 = new Stampante(11,20);
            s2.print();
        }
    }
