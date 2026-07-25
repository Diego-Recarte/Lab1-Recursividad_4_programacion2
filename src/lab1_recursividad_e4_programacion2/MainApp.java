package lab1_recursividad_e4_programacion2;

import javax.swing.JFrame;

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

    // TODO (GUI): declarar componentes (botones de asiento, campo de nombre, consola).

    public MainApp() {
        setTitle("PalindromoAir - Venta de Boletos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        // TODO (GUI): construir paneles, agregar componentes y listeners, pintar asientos.
    }

    // ---- Handlers de botones: TODO implementar (GUI) ----

    private void onSellTicket() {
        // TODO: construir new Passenger(id, nombre, edad) desde los campos y
        //       llamar air.sellTicket(passenger); revisar SEAT_FULL / NAME_TAKEN.
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
