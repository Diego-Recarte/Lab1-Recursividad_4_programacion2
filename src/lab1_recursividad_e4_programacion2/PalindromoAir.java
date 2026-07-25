package lab1_recursividad_e4_programacion2;

/**
 * Gestiona los 30 asientos del avion. Cada posicion es null (disponible) o
 * un Ticket (ocupado). Los metodos marcados con TODO se implementan de forma
 * recursiva (parte del equipo); la orquestacion de abajo ya esta lista.
 */
public class PalindromoAir {

    public static final int CAPACITY = 30;
    public static final double BASE_PRICE = 100.0;
    public static final double PALINDROME_DISCOUNT = 0.20;

    private final Ticket[] seats = new Ticket[CAPACITY];

    public Ticket getSeat(int index) {
        return seats[index];
    }

    public int getCapacity() {
        return CAPACITY;
    }

    // ---- Metodos recursivos: TODO implementar ----

    /** Primer asiento libre desde index, o -1 si no hay. */
    public int firstAvailable(int index) {
        return -1; // TODO recursivo
    }

    /** Indice del pasajero con ese nombre, o -1 si no existe. */
    public int searchPassenger(String name, int index) {
        return -1; // TODO recursivo
    }

    /** true si name es palindromo. */
    public boolean isPalindromo(String name) {
        return false; // TODO recursivo
    }

    /** Texto con todos los pasajeros (usar Ticket.print()). */
    public String printPassengers(int index) {
        return ""; // TODO recursivo
    }

    /** Suma de los montos finales vendidos. */
    public double income(int index) {
        return 0.0; // TODO recursivo
    }

    /** Libera todos los asientos. */
    public void reset(int index) {
        // TODO recursivo
    }

    // ---- Orquestacion (ya implementada) ----

    /** Vende un boleto; retorna el asiento asignado o -1 si el avion esta lleno. */
    public int sellTicket(String name) {
        int seat = firstAvailable(0);
        if (seat == -1) {
            return -1;
        }
        boolean palindrome = isPalindromo(name);
        double original = BASE_PRICE;
        double finalAmount = palindrome ? original * (1 - PALINDROME_DISCOUNT) : original;
        seats[seat] = new Ticket(name, finalAmount, original, palindrome);
        return seat;
    }

    /** Cancela el boleto de un pasajero; true si se encontro y libero. */
    public boolean cancelTicket(String name) {
        int seat = searchPassenger(name, 0);
        if (seat == -1) {
            return false;
        }
        seats[seat] = null;
        return true;
    }

    /** Retorna los ingresos totales y reinicia el avion. */
    public double dispatch() {
        double total = income(0);
        reset(0);
        return total;
    }
}
