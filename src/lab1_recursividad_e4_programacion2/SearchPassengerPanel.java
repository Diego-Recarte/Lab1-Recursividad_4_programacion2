package lab1_recursividad_e4_programacion2;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * Vista para buscar un pasajero por nombre.
 * Se apoya en el metodo recursivo air.searchPassenger(name, 0).
 */
public class SearchPassengerPanel extends JPanel {

    private final PalindromoAir air;
    private final JTextField nameField = new JTextField(18);
    private final JTextArea result = new JTextArea();

    public SearchPassengerPanel(PalindromoAir air) {
        this.air = air;
        setLayout(new BorderLayout(8, 8));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(new JLabel("Nombre:"));
        top.add(nameField);
        JButton searchButton = new JButton("Buscar");
        top.add(searchButton);
        add(top, BorderLayout.NORTH);

        result.setEditable(false);
        result.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        add(new JScrollPane(result), BorderLayout.CENTER);

        searchButton.addActionListener(e -> search());
        nameField.addActionListener(e -> search()); // buscar al presionar Enter
    }

    private void search() {
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            result.setText("Escribe un nombre para buscar.");
            return;
        }
        int index = air.searchPassenger(name, 0);
        if (index == -1) {
            result.setText("No se encontro ningun pasajero con el nombre \"" + name + "\".");
            return;
        }
        Ticket t = air.getSeat(index);
        Passenger p = t.getPassenger();
        result.setText(String.format(
                "Pasajero encontrado%n"
                + "-------------------%n"
                + "Asiento   : %d%n"
                + "Nombre    : %s%n"
                + "Id        : %s%n"
                + "Edad      : %d%n"
                + "Original  : $%.2f%n"
                + "Pagado    : $%.2f%n"
                + "Palindromo: %s",
                index + 1, p.getName(), p.getId(), p.getAge(),
                t.getOriginalAmount(), t.getFinalAmount(),
                t.isPalindrome() ? "SI (descuento aplicado)" : "NO"));
    }

    /** Demo para ver el panel de forma independiente. */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PalindromoAir air = new PalindromoAir(); // carga los pasajeros desde tickets.csv

            JFrame f = new JFrame("Demo - Search Passenger");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setContentPane(new SearchPassengerPanel(air));
            f.setSize(480, 320);
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        });
    }
}
