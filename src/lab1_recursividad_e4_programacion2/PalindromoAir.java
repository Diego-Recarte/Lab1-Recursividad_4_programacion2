package lab1_recursividad_e4_programacion2;

public class PalindromoAir {

    public static final int CAPACITY = 30;
    public static final double BASE_PRICE = 100.0;
    public static final double PALINDROME_DISCOUNT = 0.20;

    /** Codigos que puede devolver sellTicket cuando no se vende. */
    public static final int SEAT_FULL = -1;   // avion lleno
    public static final int NAME_TAKEN = -2;  // ya hay un pasajero con ese nombre

    private final Ticket[] seats = new Ticket[CAPACITY];
    private final TicketStorage storage = new TicketStorage();

    /** Al crear el avion carga los tickets guardados (si el archivo existe). */
    public PalindromoAir() {
        storage.load(this);
    }

    public Ticket getSeat(int index) {
        return seats[index];
    }

    public int getCapacity() {
        return CAPACITY;
    }

    /** Coloca un ticket directamente en un asiento; solo para la carga desde archivo. */
    void restoreSeat(int index, Ticket ticket) {
        seats[index] = ticket;
    }

    /** Guarda el estado actual del avion en el archivo. */
    private void persist() {
        storage.save(this);
    }

    // ---- Metodos recursivos: TODO implementar ----

    public int firstAvailable(int index) {
        if (index >= CAPACITY) {
            return -1;
        }
        if (seats[index] == null) {
            return index;
        }
        return firstAvailable(index + 1);
    }

    /** Indice del pasajero con ese nombre, o -1 si no existe. */
    public int searchPassenger(String name, int index) {
        if (index >= CAPACITY) {
            return -1;
        }
        if (seats[index] != null && seats[index].getName().equalsIgnoreCase(name)) {
            return index;
        }
        return searchPassenger(name, index + 1);
    }

    /** true si name es palindromo (ignora mayusculas). */
    public boolean isPalindromo(String name) {
        String clean = name.toLowerCase();
        return isPalindromo(clean, 0, clean.length() - 1);
    }

    /** Compara los extremos avanzando hacia el centro. */
    private boolean isPalindromo(String s, int left, int right) {
        if (left >= right) {
            return true;
        }
        if (s.charAt(left) != s.charAt(right)) {
            return false;
        }
        return isPalindromo(s, left + 1, right - 1);
    }

    /** Texto con todos los pasajeros (usar Ticket.print()). */
    public String printPassengers(int index) {
        if (index >= CAPACITY) {
            return "";
        }
        String current = seats[index] != null
                ? "Asiento " + (index + 1) + ": " + seats[index].print() + "\n"
                : "";
        return current + printPassengers(index + 1);
    }

    /** Suma de los montos finales vendidos. */
    public double income(int index) {
        if (index >= CAPACITY) {
            return 0.0;
        }
        double current = seats[index] != null ? seats[index].getFinalAmount() : 0.0;
        return current + income(index + 1);
    }

    /** Libera todos los asientos. */
    public void reset(int index) {
        if (index >= CAPACITY) {
            return;
        }
        seats[index] = null;
        reset(index + 1);
    }

    /**
     * Vende un boleto al pasajero dado. Retorna el asiento asignado,
     * SEAT_FULL (-1) si el avion esta lleno, o NAME_TAKEN (-2) si ya existe
     * un pasajero con ese nombre.
     */
    public int sellTicket(Passenger passenger) {
        if (searchPassenger(passenger.getName(), 0) != -1) {
            return NAME_TAKEN;
        }
        int seat = firstAvailable(0);
        if (seat == -1) {
            return SEAT_FULL;
        }
        boolean palindrome = isPalindromo(passenger.getName());
        double original = BASE_PRICE;
        double finalAmount = palindrome ? original * (1 - PALINDROME_DISCOUNT) : original;
        seats[seat] = new Ticket(passenger, finalAmount, original, palindrome);
        persist();
        return seat;
    }

    /** Cancela el boleto de un pasajero; true si se encontro y libero. */
    public boolean cancelTicket(String name) {
        int seat = searchPassenger(name, 0);
        if (seat == -1) {
            return false;
        }
        seats[seat] = null;
        persist();
        return true;
    }

    /** Retorna los ingresos totales y reinicia el avion. */
    public double dispatch() {
        double total = income(0);
        reset(0);
        persist();
        return total;
    }
}
