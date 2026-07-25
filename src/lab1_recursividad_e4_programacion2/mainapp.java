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
    // TODO (GUI): declarar componentes (botones de asiento, campo de nombre, consola).

    public MainApp() {
        super("Aircraft");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setSize(800, 600); 
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(Color.WHITE); 
        setLocationRelativeTo(null); 
         initializeboard();
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

                    celda.add(labels[i][j], BorderLayout.CENTER);
                    panelt.add(celda);
                }
            }
            
            add(panelt);
        
        
        
        
        
        
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
        // TODO: air.printPassengers(0)
    }

    private void onViewIncome() {
        // TODO: air.income(0)
    }

    private void onSearchPassenger() {
        // TODO: air.searchPassenger(nombre, 0)
    }

    private void refreshSeats() {
        // TODO: recorrer asientos y actualizar color de cada boton
    }
}
