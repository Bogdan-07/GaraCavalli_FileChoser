/**
 * Classe GaraCavalliAzzoppati_Crisan.Cavallo
 * @version 1.0
 * @author Crisan Bogdan
 * <p>
 * Rappresenta un cavallo che partecipa a una gara. 
 * Ogni cavallo è gestito come un thread indipendente e ha un nome e una certa lentezza.
 */
public class Cavallo extends Thread{

    /** Nome del cavallo */
    private final String name;
    /** Valore che rappresenta la lentezza del cavallo */
    private int lentezza;
    /** Variabile utilizzata per salvare il nome del primo cavallo */
    private String primo;

    /**
     * Costruttore che inizializza il nome e la lentezza del cavallo.
     */
    public Cavallo(String name, int lentezza) {
        super();
        this.name = name;
        this.lentezza = lentezza;
    }

    /**
     * Costruttore che inizializza solo il nome del cavallo.
     */
    public Cavallo(String name){
        super();
        this.name = name;
    }

    /**
     * Metodo eseguito all'avvio del thread.
     * Simula la corsa del cavallo, che avanza per 10 passi 
     * dormendo tra un passo e l'altro in base alla lentezza.
     * Alla fine, se è il primo a terminare, viene registrato come vincitore.
     */
    @Override
    public void run(){
        System.out.println("GaraCavalliAzzoppati_Crisan.Cavallo " +
                name + " comincia il suo galoppo");
        for (int i = 1; i <= 10 && !Thread.currentThread().isInterrupted(); i++) {
            try {
                Thread.sleep(lentezza);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(name +" cavalca - passo: " + i);
        }
        if(GestoreGaraCavalli.getPrimo().equals("") && !Thread.currentThread().isInterrupted()){
            GestoreGaraCavalli.setPrimo(this.name);
        }
    }

    /**
     * Restituisce la lentezza del cavallo.
     */
    public int getLentezza(){
        return lentezza;
    }

    /**
     * Imposta la lentezza del cavallo.
     */
    public void setLentezza(int lentezza){
        this.lentezza=lentezza;
    }

    /**
     * Restituisce il nome del cavallo.
     */
    public String getNomeCavallo() {
        return name;
    }
}
