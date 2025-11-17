import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe GaraCavalliAzzoppati_Crisan.GestoreGaraCavalli
 * @version 1.0
 * @author Crisan Bogdan
 * <p>
 * Gestisce la gara tra più cavalli.
 * Permette di inserire i cavalli, avviare la corsa
 * e determinare il vincitore.
 */
public class GestoreGaraCavalli {

    /** Nome del primo cavallo che termina la corsa */
    static String primo="";

    /**
     * Metodo principale: gestisce input da tastiera,
     * avvia la gara e visualizza il cavallo vincitore.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String tmpS;
        int tmp;
        ArrayList<Cavallo> listaCavallo = new ArrayList<Cavallo>();

        for (int i = 1; i <= 5; i++) {
            System.out.println("Inserisci il nome del cavallo " + i);
            tmpS =  input.nextLine();
            System.out.println("Inserisci la lentezza del cavallo " + i);
            tmp = input.nextInt();
            String v = input.nextLine(); // prende il \n
            Cavallo c=new Cavallo(tmpS, tmp);
            listaCavallo.add(c);
        }

        // Selezione casuale di un cavallo che verrà interrotto (azzoppato)
        int a = (int)(Math.random() / 0.2);
        Cavallo x = listaCavallo.get(a);
        listaCavallo.remove(a);
        x.interrupt();
        System.out.println("GaraCavalliAzzoppati_Crisan.Cavallo azzoppato: " + x.getNomeCavallo());

        // Avvio di tutti i cavalli restanti
        for(Cavallo c: listaCavallo){
            c.start();
        }

        // Attesa della fine di tutti i thread
        for(Cavallo c: listaCavallo){
            try {
                c.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Il primo cavallo: " + primo);
    }

    /**
     * Restituisce il nome del cavallo vincitore.
     */
    public static String getPrimo() {
        return primo;
    }

    /**
     * Imposta il nome del cavallo vincitore.
     */
    public static void setPrimo(String primo) {
        GestoreGaraCavalli.primo = primo;
    }
}
