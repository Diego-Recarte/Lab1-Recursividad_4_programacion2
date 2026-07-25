package lab1_recursividad_e4_programacion2;

import javax.swing.*;
import java.awt. *;

/**
 * Ventana principal (GUI). Esqueleto para que el equipo de GUI lo implemente.
 *
 * Construir: panel de 30 asientos (6x5), campo de texto para el nombre,
 * botones Sell/Cancel/Dispatch/Print/View Income/Search, y area de mensajes.
 * Colores de asiento: verde = libre, rojo = ocupado, azul/dorado = palindromo.
 * La logica vive en PalindromoAir; esta clase solo llama a sus metodos.
 */
public class MainApp extends JFrame {

    private final PalindromoAir air = new PalindromoAir();
    private JPanel panelt;
    private JLabel [][] labels;
    // paneles de la GUI
    private JPanel panelBotones;
    private JPanel panelMensajes;
    //Botones 
    private JButton btnSellTicket;
    private JButton btnCancelTicket;
    private JButton btnDispatch;
    private JButton btnPrintPassengers;
    private JButton btnViewIncome;
    private JButton btnSearchPassenger;
    //Consola
    private JTextArea txtMessages;
    private JScrollPane scrollMessages;
    // TODO (GUI): declarar componentes (botones de asiento, campo de nombre, consola).

    public MainApp() {
        super("Aircraft");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setSize(800, 600); 
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE); 
        setLocationRelativeTo(null); 
        initializeboard();
        initializeButtons();
        initializeMessages();
        refreshSeats();
        setVisible(true);
        };
     
     private void initializeboard(){
            panelt = new JPanel();
            labels = new JLabel[6][5];

            panelt.setLayout(new GridLayout(6, 5, 2, 2));
            panelt.setBackground(Color.BLACK);

            for (int i = 0; i < 6; i++){
                for (int j = 0; j < 5; j++){
                    JPanel celda = new JPanel(new BorderLayout());
                    celda.setBackground(Color.WHITE);
                    celda.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                    labels[i][j] = new JLabel("Free", SwingConstants.CENTER);
                    labels[i][j].setFont(new Font("Arial", Font.BOLD, 14));
                    labels[i][j].setForeground(Color.BLACK);
                    labels[i][j].setOpaque(true);

                    celda.add(labels[i][j], BorderLayout.CENTER);
                    panelt.add(celda);
                }
            }
            
            add(panelt);
        
     }
     private void initializeButtons(){
        panelBotones = new JPanel(); 
        panelBotones.setLayout(new GridLayout(3, 2, 10, 10));
        
        btnSellTicket = new JButton("Sell Ticket");
        panelBotones.add(btnSellTicket);
        
        btnCancelTicket = new JButton("Cancel Ticket");
        panelBotones.add(btnCancelTicket);

        btnDispatch = new JButton("Dispatch");
        panelBotones.add(btnDispatch);

        btnPrintPassengers = new JButton("Print Passengers");
        btnPrintPassengers.addActionListener(e -> onPrintPassengers());
        panelBotones.add(btnPrintPassengers);

        btnViewIncome = new JButton("View Income");
        panelBotones.add(btnViewIncome);

        btnSearchPassenger = new JButton("Search Passenger");
        btnSearchPassenger.addActionListener(e -> onSearchPassenger());
        panelBotones.add(btnSearchPassenger);
        
        add(panelBotones, BorderLayout.EAST);
     }
     
     private void initializeMessages(){
      txtMessages = new JTextArea();
      txtMessages.setEditable(false);
      txtMessages.setLineWrap(true);
      txtMessages.setWrapStyleWord(true);
      txtMessages.setFont(new Font("Arial", Font.PLAIN, 14));
}

    // ---- Handlers de botones: TODO implementar (GUI) ----

    private void onSellTicket() {
        // TODO: air.sellTicket(nombre)
    }

    private void onCancelTicket() {
        // TODO: air.cancelTicket(nombre)
    }

    private void onDispatch() {
        // TODO: air.dispatch()
    }

    private void onPrintPassengers() {
        JDialog dialog = new JDialog(this, "Pasajeros a bordo", true);
        dialog.setContentPane(new PrintPassengersPanel(air));
        dialog.setSize(560, 400);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void onViewIncome() {
        // TODO: air.income(0)
    }

    private void onSearchPassenger() {
        JDialog dialog = new JDialog(this, "Buscar pasajero", true);
        dialog.setContentPane(new SearchPassengerPanel(air));
        dialog.setSize(480, 320);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    // Colores de asiento
    private static final Color COLOR_FREE = new Color(76, 175, 80);   // verde
    private static final Color COLOR_TAKEN = new Color(229, 57, 53);  // rojo
    private static final Color COLOR_PALINDROME = new Color(255, 193, 7); // dorado

    /** Recorre los 30 asientos y actualiza texto y color segun el estado del avion (cargado del CSV). */
    private void refreshSeats() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                int seat = i * 5 + j;
                Ticket t = air.getSeat(seat);
                if (t == null) {
                    labels[i][j].setText("Free");
                    labels[i][j].setBackground(COLOR_FREE);
                } else {
                    labels[i][j].setText(t.getName());
                    labels[i][j].setBackground(t.isPalindrome() ? COLOR_PALINDROME : COLOR_TAKEN);
                }
            }
        }
    }

}


