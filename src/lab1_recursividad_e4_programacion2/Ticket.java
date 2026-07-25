package lab1_recursividad_e4_programacion2;

/** Boleto emitido para un pasajero. Contenedor de datos, ya esta completo. */
public class Ticket {

    private final Passenger passenger;
    private final double finalAmount;
    private final double originalAmount;
    private final boolean palindrome;

    public Ticket(Passenger passenger, double finalAmount, double originalAmount, boolean palindrome) {
        this.passenger = passenger;
        this.finalAmount = finalAmount;
        this.originalAmount = originalAmount;
        this.palindrome = palindrome;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    /** Atajo al nombre del pasajero (usado por las busquedas). */
    public String getName() {
        return passenger.getName();
    }

    public double getFinalAmount() {
        return finalAmount;
    }

    public double getOriginalAmount() {
        return originalAmount;
    }

    public boolean isPalindrome() {
        return palindrome;
    }

    public String print() {
        return String.format(
                "%s | original: $%.2f | pagado: $%.2f | descuento palindromo: %s",
                passenger, originalAmount, finalAmount, palindrome ? "SI" : "NO");
    }

    @Override
    public String toString() {
        return print();
    }
}
