import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Cavallo extends Thread {

    private final String name;
    private int lentezza;
    private File fileOutput; // file scelto dal FileChooserDemo

    public Cavallo(String name, int lentezza, File fileOutput) {
        this.name = name;
        this.lentezza = lentezza;
        this.fileOutput = fileOutput;
    }

    private void scrivi(String testo) {
        try {
            FileWriter fw = new FileWriter(fileOutput, true);
            fw.write(testo + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Errore scrittura file.");
        }
    }

    @Override
    public void run() {

        scrivi("Cavallo " + name + " parte.");

        for (int i = 1; i <= 10; i++) {
            try {
                Thread.sleep(lentezza);
            } catch (InterruptedException e) {
                scrivi("Cavallo " + name + " è stato azzoppato.");
                return;
            }

            scrivi(name + " passo " + i);
        }

        if (GestoreGaraCavalli.getPrimo().equals("")) {
            GestoreGaraCavalli.setPrimo(name);
        }
    }

    public String getNomeCavallo() {
        return name;
    }
}
