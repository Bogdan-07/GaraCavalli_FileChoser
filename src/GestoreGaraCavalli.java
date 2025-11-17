import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class GestoreGaraCavalli {

    static String primo = "";

    public static void main(String[] args) {

        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(null);

        if (result != JFileChooser.APPROVE_OPTION) {
            System.out.println("Nessun file selezionato. Uscita.");
            System.exit(-1);
        }

        File fileOutput = fileChooser.getSelectedFile();

        Scanner input = new Scanner(System.in);
        ArrayList<Cavallo> cavalli = new ArrayList<>();


        for (int i = 1; i <= 5; i++) {
            System.out.println("Inserisci il nome del cavallo " + i);
            String nome = input.nextLine();

            System.out.println("Inserisci la lentezza del cavallo " + i);
            int lentezza = input.nextInt();
            input.nextLine();

            cavalli.add(new Cavallo(nome, lentezza, fileOutput));
        }

        int a = (int)(Math.random() * cavalli.size());
        Cavallo azz = cavalli.get(a);
        cavalli.remove(a);
        azz.interrupt();

        try {
            FileWriter fw = new FileWriter(fileOutput, true);
            fw.write("Cavallo azzoppato: " + azz.getNomeCavallo() + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (Cavallo c : cavalli) {
            c.start();
        }

        for (Cavallo c : cavalli) {
            try {
                c.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            FileWriter fw = new FileWriter(fileOutput, true);
            fw.write("\nVincitore: " + primo + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Risultati salvati in: " + fileOutput.getAbsolutePath());
    }

    public static String getPrimo() { return primo; }
    public static void setPrimo(String p) { primo = p; }
}
