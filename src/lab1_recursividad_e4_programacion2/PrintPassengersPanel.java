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
import javax.swing.SwingUtilities;

/**
 * Vista que lista todos los pasajeros a bordo.
 * Se apoya en el metodo recursivo air.printPassengers(0).
 */
public class PrintPassengersPanel extends JPanel {

    private final PalindromoAir air;
    private final JTextArea area = new JTextArea();

    public PrintPassengersPanel(PalindromoAir air) {
        this.air = air;
        setLayout(new BorderLayout(8, 8));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel title = new JLabel("Pasajeros a bordo");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 16f));
        add(title, BorderLayout.NORTH);

        area.setEditable(false);
        area.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        add(new JScrollPane(area), BorderLayout.CENTER);

        JButton refreshButton = new JButton("Refrescar");
        refreshButton.addActionListener(e -> refresh());
        JPanel south = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        south.add(refreshButton);
        add(south, BorderLayout.SOUTH);

        refresh();
    }

    /** Recarga la lista desde la logica del avion. */
    public final void refresh() {
        String text = air.printPassengers(0);
        area.setText(text.isEmpty() ? "No hay pasajeros a bordo." : text);
        area.setCaretPosition(0);
    }

    /** Demo para ver el panel de forma independiente. */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PalindromoAir air = new PalindromoAir();
            air.sellTicket(new Passenger("001", "Ana", 30));
            air.sellTicket(new Passenger("002", "Marcelo", 25));
            air.sellTicket(new Passenger("003", "Bob", 40));

            JFrame f = new JFrame("Demo - Print Passengers");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setContentPane(new PrintPassengersPanel(air));
            f.setSize(560, 400);
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        });
    }
}
