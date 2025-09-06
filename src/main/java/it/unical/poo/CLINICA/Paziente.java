package CLINICA;

public class Paziente extends Thread{
    public Paziente(Clinica c,int id){
        this.id=id;
        this.clinica=c;
    }
    Clinica clinica;
    int id;
    public void run(){
        try{
            
        }catch(InterruptedException e){}
    }
}
