package lab1_recursividad_e4_programacion2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Pseudo base de datos: guarda y carga los tickets en un archivo CSV.
 * Toda la logica de archivos vive aqui; PalindromoAir solo la invoca.
 *
 * Formato (una fila por asiento ocupado):
 *   seat,id,name,age,original,final,palindrome
 *
 * Nota: se asume que id y name NO contienen comas.
 */
public class TicketStorage {

    public static final String FILE_NAME = "tickets.csv";
    private static final String HEADER = "seat,id,name,age,original,final,palindrome";

    /** Escribe el estado actual del avion al archivo (sobreescribe). */
    public void save(PalindromoAir air) {
        try (PrintWriter out = new PrintWriter(new FileWriter(FILE_NAME))) {
            out.println(HEADER);
            for (int i = 0; i < air.getCapacity(); i++) {
                Ticket t = air.getSeat(i);
                if (t == null) {
                    continue;
                }
                Passenger p = t.getPassenger();
                out.println(String.join(",",
                        String.valueOf(i),
                        p.getId(),
                        p.getName(),
                        String.valueOf(p.getAge()),
                        String.valueOf(t.getOriginalAmount()),
                        String.valueOf(t.getFinalAmount()),
                        String.valueOf(t.isPalindrome())));
            }
        } catch (IOException e) {
            System.err.println("No se pudo guardar " + FILE_NAME + ": " + e.getMessage());
        }
    }

    /** Carga los tickets del archivo al avion. Si no existe, no hace nada. */
    public void load(PalindromoAir air) {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return;
        }
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            in.readLine(); // descartar cabecera
            String line;
            while ((line = in.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }
                String[] f = line.split(",", 7);
                if (f.length < 7) {
                    continue;
                }
                int seat = Integer.parseInt(f[0].trim());
                Passenger p = new Passenger(f[1].trim(), f[2].trim(), Integer.parseInt(f[3].trim()));
                double original = Double.parseDouble(f[4].trim());
                double finalAmount = Double.parseDouble(f[5].trim());
                boolean palindrome = Boolean.parseBoolean(f[6].trim());
                if (seat >= 0 && seat < air.getCapacity()) {
                    air.restoreSeat(seat, new Ticket(p, finalAmount, original, palindrome));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("No se pudo leer " + FILE_NAME + ": " + e.getMessage());
        }
    }
}
